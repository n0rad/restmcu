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

const prog_char *settingsLineHandleLineNotifyArray(uint8_t index);
const prog_char *settingsLineSetId(char *buf, uint16_t len, uint8_t index);
const prog_char *settingsLineSetName(char *buf, uint16_t len, uint8_t index);
const prog_char *settingsLineSetNotifyCond(char *buf, uint16_t len, uint8_t index);
const prog_char *settingsLineSetNotifyValue(char *buf, uint16_t len, uint8_t index);

#endif
