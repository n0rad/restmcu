#include "line-manager.h"
#include "../client/client-notify.h"
#include "../restmcu.h"
#include "../settings/settings-line.h"
#include "../driver/line.h"

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
    writefunc(lineId, type, lowLvlVal);
    return 0;
}
float getLineValue(uint8_t lineIdx) {
    if (lineIdx < lineInputSize) {
        uint8_t type = pgm_read_byte(&lineInputDescription[lineIdx].type);
        uint8_t lineId = pgm_read_byte(&lineInputDescription[lineIdx].lineId);
        LineRead readfunc = (LineRead) pgm_read_word(&lineInputDescription[lineIdx].read);
        LineInputConversion converter = (LineInputConversion) pgm_read_word(&lineInputDescription[lineIdx].convertValue);
        return converter(readfunc(lineId, type));
    } else {
        return settingsLineOutputGetValue(lineIdx - lineInputSize);
    }
}

////////////////////////////////////////////////////////
// Low level line access
////////////////////////////////////////////////////////
uint16_t defaultLineRead(uint8_t lineId, uint8_t type) {
    if (type == ANALOG) {
        return analogRead(lineId);
    } else {
        return digitalRead(lineId);
    }
}
void defaultLineWrite(uint8_t lineId, uint8_t type, uint16_t value) {
    if (type == ANALOG) {
        analogWrite(lineId, value);
    } else {
        digitalWrite(lineId, value);
    }
}

static uint16_t *previousInputValues;

void lineInit() {
    int8_t lineId;
    previousInputValues = (uint16_t *) malloc(lineInputSize * sizeof(uint16_t));
    for (uint8_t i = 0; -1 != (lineId = (int8_t) pgm_read_byte(&lineInputDescription[i].lineId)); i++) {
        pinMode(lineId, INPUT);

        if (pgm_read_byte(&lineInputDescription[i].pullup)) {
            digitalWrite(lineId, HIGH);
        }
        delay(10);
        LineRead readfunc = (LineRead) pgm_read_word(&lineInputDescription[i].read);
        uint8_t type = pgm_read_byte(&lineInputDescription[i].type);
        previousInputValues[i] = readfunc(lineId, type);
    }
    for (uint8_t i = 0; -1 != (lineId = (int8_t) pgm_read_byte(&lineOutputDescription[i].lineId)); i++) {
        pinMode(lineId, OUTPUT);
        float val = settingsLineOutputGetValue(i);
        setLineValue(i, settingsLineOutputGetValue(i));
    }
}

void lineCheckChange() {
    int8_t lineId;
    for (uint8_t i = 0; -1 != (lineId = (int8_t) pgm_read_byte(&lineInputDescription[i].lineId)); i++) {
        LineRead readfunc = (LineRead) pgm_read_word(&lineInputDescription[i].read);
        uint8_t type = pgm_read_byte(&lineInputDescription[i].type);

        uint16_t oldValue = previousInputValues[i];
        uint16_t value = readfunc(lineId, type);

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
