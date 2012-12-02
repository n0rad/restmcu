#ifndef SETTINGS_LINE_H
#define SETTINGS_LINE_H

#include <stdint.h>

#include <restmcu-config.h>
#include "settings-board.h"

extern uint8_t currentSetLineIdx;

void settingsLineOutputSetValue(uint8_t outputIdx, float value);
float settingsLineOutputGetValue(uint8_t outputIdx);
t_notify *settingsLineGetNotify(uint8_t lineIdx, uint8_t notifyId);
eeprom_char *settingsLineGetName_E(uint8_t lineIdx);

const char PROGMEM *settingsLineHandleLineNotifyArray(uint8_t index);
const char PROGMEM *settingsLineSetId(char *buf, uint16_t len, uint8_t index);
const char PROGMEM *settingsLineSetName(char *buf, uint16_t len, uint8_t index);
const char PROGMEM *settingsLineSetNotifyCond(char *buf, uint16_t len, uint8_t index);
const char PROGMEM *settingsLineSetNotifyValue(char *buf, uint16_t len, uint8_t index);

#endif
