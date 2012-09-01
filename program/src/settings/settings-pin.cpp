#include "settings-pin.h"

uint8_t currentSetPinIdx;

void settingsPinOutputSetValue(uint8_t outputIdx, float value) {
    eeprom_write_dword((uint32_t *) &pinOutputSettings[outputIdx].lastValue, *((unsigned long *)&value));
}

float settingsPinOutputGetValue(uint8_t outputIdx) {
	uint32_t val = eeprom_read_dword((uint32_t *) &pinOutputSettings[outputIdx].lastValue);
    return *((float*)&val);
}

t_notify *settingsPinGetNotify(uint8_t pinIdx, uint8_t notifyId) {
    return &pinNotifies[pinIdx][notifyId];
}

uint8_t *settingsPinGetName_E(uint8_t pinIdx) {
    if (pinIdx < pinInputSize) {
    	return (uint8_t *) &pinInputSettings[pinIdx].name;
    }
	return (uint8_t *) &pinOutputSettings[pinIdx - pinInputSize].name;
}

/////////////////

const prog_char *settingsPinSetName(char *buf, uint16_t len, uint8_t index) {
    if (len >  CONFIG_PIN_NAME_SIZE - 1) {
        return NAME_TOO_LONG;
    }
    uint16_t pinPos;
    if (currentSetPinIdx < pinInputSize) {
        pinPos = (uint16_t) &pinInputSettings[currentSetPinIdx].name;
    } else {
    	pinPos = (uint16_t) &pinOutputSettings[currentSetPinIdx - pinInputSize].name;
    }
    eeprom_write_block(buf, (uint8_t *)pinPos, len);
    eeprom_write_byte((uint8_t *) (pinPos + len), 0);
    return 0;
}

const prog_char *settingsPinHandlePinNotifyArray(uint8_t index) {
    for (uint8_t i = index; i < 4; i++) {
        eeprom_write_byte((uint8_t *) &pinInputSettings[currentSetPinIdx].notifies[i].condition, 0);
    }
    settingsReload();
    return 0;
}

const prog_char *settingsPinSetNotifyCond(char *buf, uint16_t len, uint8_t index) {
    if (index > 3) {
        return TOO_MANY_NOTIFY;
    }
    if (currentSetPinIdx >= pinInputSize){
        return NO_NOTIFY_OUTPUT;
    }

    uint8_t notif;
    if (!strncmp_P(buf, PIN_NOTIFICATION_SUP, len)) {
        notif = OVER_EQ;
    } else if (!strncmp_P(buf, PIN_NOTIFICATION_INF, len)) {
        notif = UNDER_EQ;
    } else {
        return PSTR("invalid notify condition");
    }
    eeprom_write_byte((uint8_t *) &pinInputSettings[currentSetPinIdx].notifies[index].condition, notif);
    return 0;
}
const prog_char *settingsPinSetNotifyValue(char *buf, uint16_t len, uint8_t index) {
    if (index > 3) {
        return TOO_MANY_NOTIFY;
    }
    if (currentSetPinIdx >= pinInputSize){
        return NO_NOTIFY_OUTPUT;
    }
    float value = atof(buf);
    uint8_t type = pgm_read_byte(&pinInputDescription[currentSetPinIdx].type);
    PinInputConversion conversion = (PinInputConversion) pgm_read_word(&(pinInputDescription[currentSetPinIdx].convertValue));
    if (value > conversion(type == ANALOG ? 1023 : 1) || value < conversion(0)) {
        return PSTR("notify value overflow");
    }
    eeprom_write_dword((uint32_t *) &pinInputSettings[currentSetPinIdx].notifies[index].value, *((unsigned long *)&value));
    return 0;
}
