#ifndef SETTINGS_PIN_H
#define SETTINGS_PIN_H

#include <stdint.h>

#include <restmcu-config.h>
#include "settings-board.h"

extern uint8_t currentSetPinIdx;

void settingsPinOutputSetValue(uint8_t outputIdx, float value);
float settingsPinOutputGetValue(uint8_t outputIdx);
t_notify *settingsPinGetNotify(uint8_t pinIdx, uint8_t notifyId);
eeprom_char *settingsPinGetName_E(uint8_t pinIdx);

const prog_char *settingsPinHandlePinNotifyArray(uint8_t index);
const prog_char *settingsPinSetId(char *buf, uint16_t len, uint8_t index);
const prog_char *settingsPinSetName(char *buf, uint16_t len, uint8_t index);
const prog_char *settingsPinSetNotifyCond(char *buf, uint16_t len, uint8_t index);
const prog_char *settingsPinSetNotifyValue(char *buf, uint16_t len, uint8_t index);

#endif
