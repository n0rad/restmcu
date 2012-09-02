#ifndef SETTINGS_BOARD_H
#define SETTINGS_BOARD_H

#include <avr/pgmspace.h>
#include <avr/eeprom.h>
#include <stdlib.h>
#include <string.h>
#include <stdio.h>

#include "../util/buffer.h"
#include "settings.h"
#include "../util/mylibc.h"

const char restmcu_version[] PROGMEM = "0.1";

const char NOT_VALID_IP[] PROGMEM = "not valid ip";
const char NOT_VALID_PORT[] PROGMEM = "not valid port";
const char CANNOT_SET_MAC[] PROGMEM = "mac cannot be set";


void configBoardGetMac(uint8_t ip[6]);

void settingsBoardGetIP(uint8_t ip[4]);
uint16_t settingsBoardGetPort();
eeprom_char *settingsBoardGetName_E();
eeprom_char *settingsBoardGetNotifyUrl_E();

const prog_char *settingsBoardSetName(char *buf, uint16_t len, uint8_t index);
const prog_char *settingsBoardSetNotifyUrl(char *buf, uint16_t len, uint8_t index);
const prog_char *settingsBoardSetIP(char *buf, uint16_t len, uint8_t index);
const prog_char *settingsBoardSetPort(char *buf, uint16_t len, uint8_t index);

#endif
