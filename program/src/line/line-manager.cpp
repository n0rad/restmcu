#include "line-manager.h"
#include "../client/client-notify.h"
#include "../restmcu.h"
#include "../settings/settings-line.h"
#include "../driver/line.h"

static uint16_t *previousInputValues;


float noInputConversion(uint16_t lineValue) {
    return lineValue;
}
int16_t noOutputConversion(float lineValue) {
    return lineValue;
}

////////////////////////////////////////////////////////
// High level line access (read line or settings)
////////////////////////////////////////////////////////
const prog_char *setLineValue(uint8_t lineOutputIdx, float value) {
    uint8_t type = pgm_read_byte(&lineOutputDescription[lineOutputIdx].type);
    uint8_t lineId = pgm_read_byte(&lineOutputDescription[lineOutputIdx].lineId);
    LineWrite writefunc = (LineWrite) pgm_read_word(&lineOutputDescription[lineOutputIdx].write);
    LineOutputConversion converter = (LineOutputConversion) pgm_read_word(&lineOutputDescription[lineOutputIdx].convertValue);
    uint16_t lowLvlVal = converter(value);
    if (lowLvlVal > converter(type == ANALOG ? 255 : 1) || value < converter(0)) {
        return PSTR("value overflow");
    }
    settingsLineOutputSetValue(lineOutputIdx, value);
    writefunc(lineId, type, lowLvlVal, (prog_int8_t *) pgm_read_word(&lineOutputDescription[lineOutputIdx].params));
    return 0;
}
float getLineValue(uint8_t lineIdx) {
    if (lineIdx < lineInputSize) {
        uint8_t type = pgm_read_byte(&lineInputDescription[lineIdx].type);
        uint8_t lineId = pgm_read_byte(&lineInputDescription[lineIdx].lineId);
        LineRead readfunc = (LineRead) pgm_read_word(&lineInputDescription[lineIdx].read);
        LineInputConversion converter = (LineInputConversion) pgm_read_word(&lineInputDescription[lineIdx].convertValue);
        return converter(readfunc(lineId, type, (prog_int8_t *) pgm_read_word(&lineInputDescription[lineIdx].params)));
    } else {
        return settingsLineOutputGetValue(lineIdx - lineInputSize);
    }
}

////////////////////////////////////////////////////////
// Low level line access
////////////////////////////////////////////////////////
uint16_t defaultLineRead(uint8_t lineId, uint8_t type, prog_int8_t params[]) {
    if (type == ANALOG) {
        return analogRead(lineId);
    } else {
        return digitalRead(lineId);
    }
}
void defaultLineWrite(uint8_t lineId, uint8_t type, uint16_t value, prog_int8_t params[]) {
    if (type == ANALOG) {
        analogWrite(lineId, value);
    } else {
        digitalWrite(lineId, value);
    }
}

void defaultInputLineInit(int8_t lineId, const t_lineInputDescription *description) {
    pinMode(lineId, INPUT);

    if (pgm_read_byte(&description->pullup)) { // TODO move out of here ?
        digitalWrite(lineId, HIGH);
    }
    delay(10);
}

void defaultOutputLineInit(int8_t lineId, const t_lineOutputDescription *description) {
    pinMode(lineId, OUTPUT);
}

void lineInit() {
    int8_t lineId;
    previousInputValues = (uint16_t *) malloc(lineInputSize * sizeof(uint16_t));
    for (uint8_t i = 0; -1 != (lineId = (int8_t) pgm_read_byte(&lineInputDescription[i].lineId)); i++) {
    	LineInputInit initFunc = (LineInputInit) pgm_read_word(&lineInputDescription[i].init);
        initFunc(lineId, &lineInputDescription[i]);

        LineRead readfunc = (LineRead) pgm_read_word(&lineInputDescription[i].read);
        uint8_t type = pgm_read_byte(&lineInputDescription[i].type);
        previousInputValues[i] = readfunc(lineId, type, (prog_int8_t *) pgm_read_word(&lineInputDescription[i].params));
    }
    for (uint8_t i = 0; -1 != (lineId = (int8_t) pgm_read_byte(&lineOutputDescription[i].lineId)); i++) {
    	LineOutputInit initFunc = (LineOutputInit) pgm_read_word(&lineOutputDescription[i].init);
    	initFunc(lineId, &lineOutputDescription[i]);

    	setLineValue(i, settingsLineOutputGetValue(i));
    }
}

void lineCheckChange() {
    int8_t lineId;
    for (uint8_t i = 0; -1 != (lineId = (int8_t) pgm_read_byte(&lineInputDescription[i].lineId)); i++) {
        LineRead readfunc = (LineRead) pgm_read_word(&lineInputDescription[i].read);
        uint8_t type = pgm_read_byte(&lineInputDescription[i].type);

        uint16_t oldValue = previousInputValues[i];
        uint16_t value = readfunc(lineId, type, (prog_int8_t *) pgm_read_word(&lineInputDescription[i].params));

        for (uint8_t j = 0; j < LINE_NUMBER_OF_NOTIFY; j++) {
            t_notify *notify = settingsLineGetNotify(i, j);
            if (notify->condition != 0) {
                if ((notify->condition == UNDER_EQ && oldValue > notify->value && value <= notify->value)
                        || (notify->condition == OVER_EQ && oldValue < notify->value && value >= notify->value)) {
                    clientLineNotify(lineId, oldValue, value, notify);
                }
            }
        }
        previousInputValues[i] = value;
    }
}
