#include "settings-line.h"

uint8_t currentSetLineIdx;

void settingsLineOutputSetValue(uint8_t outputIdx, float value) {
    eeprom_write_dword((uint32_t *) &lineOutputSettings[outputIdx].lastValue, *((unsigned long *)&value));
}

float settingsLineOutputGetValue(uint8_t outputIdx) {
	uint32_t val = eeprom_read_dword((uint32_t *) &lineOutputSettings[outputIdx].lastValue);
    return *((float*)&val);
}

t_notify *settingsLineGetNotify(uint8_t lineIdx, uint8_t notifyId) {
    return &lineNotifies[lineIdx][notifyId];
}

eeprom_char *settingsLineGetName_E(uint8_t lineIdx) {
    if (lineIdx < lineInputSize) {
    	return (eeprom_char *) &lineInputSettings[lineIdx].name;
    }
	return (eeprom_char *) &lineOutputSettings[lineIdx - lineInputSize].name;
}

/////////////////

const prog_char *settingsLineSetName(char *buf, uint16_t len, uint8_t index) {
    if (len >  CONFIG_LINE_NAME_SIZE - 1) {
        return NAME_TOO_LONG;
    }
    uint16_t linePos;
    if (currentSetLineIdx < lineInputSize) {
        linePos = (uint16_t) &lineInputSettings[currentSetLineIdx].name;
    } else {
    	linePos = (uint16_t) &lineOutputSettings[currentSetLineIdx - lineInputSize].name;
    }
    eeprom_write_block(buf, (uint8_t *)linePos, len);
    eeprom_write_byte((uint8_t *) (linePos + len), 0);
    return 0;
}

const prog_char *settingsLineHandleLineNotifyArray(uint8_t index) {
    for (uint8_t i = index; i < 4; i++) {
        eeprom_write_byte((uint8_t *) &lineInputSettings[currentSetLineIdx].notifies[i].condition, 0);
    }
    settingsReload();
    return 0;
}

const prog_char *settingsLineSetNotifyCond(char *buf, uint16_t len, uint8_t index) {
    if (index > 3) {
        return TOO_MANY_NOTIFY;
    }
    if (currentSetLineIdx >= lineInputSize){
        return NO_NOTIFY_OUTPUT;
    }

    uint8_t notif;
    if (!strncmp_P(buf, LINE_NOTIFICATION_SUP, len)) {
        notif = OVER_EQ;
    } else if (!strncmp_P(buf, LINE_NOTIFICATION_INF, len)) {
        notif = UNDER_EQ;
    } else {
        return PSTR("invalid notify condition");
    }
    eeprom_write_byte((uint8_t *) &lineInputSettings[currentSetLineIdx].notifies[index].condition, notif);
    return 0;
}
const prog_char *settingsLineSetNotifyValue(char *buf, uint16_t len, uint8_t index) {
    if (index > 3) {
        return TOO_MANY_NOTIFY;
    }
    if (currentSetLineIdx >= lineInputSize){
        return NO_NOTIFY_OUTPUT;
    }
    float value = atof(buf);
    uint8_t type = pgm_read_byte(&lineInputDescription[currentSetLineIdx].type);
    LineInputConversion conversion = (LineInputConversion) pgm_read_word(&(lineInputDescription[currentSetLineIdx].convertValue));
    if (value > conversion(type == ANALOG ? 1023 : 1) || value < conversion(0)) {
        return PSTR("notify value overflow");
    }
    eeprom_write_dword((uint32_t *) &lineInputSettings[currentSetLineIdx].notifies[index].value, *((unsigned long *)&value));
    return 0;
}
