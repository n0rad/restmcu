#ifndef SETTINGS_H
#define SETTINGS_H


#include <stdlib.h>
#include <stdint.h>
#include <avr/pgmspace.h>
#include <avr/eeprom.h>

#include <restmcu-config.h>
#include "../restmcu.h"
#include "../pin/pin-manager.h"
#include "../util/mylibc.h"


const prog_char IP_CHARACTERS[] PROGMEM = "0123456789.";

const prog_char TOO_MANY_NOTIFY[] PROGMEM = "Too many notify";
const prog_char NO_NOTIFY_OUTPUT[] PROGMEM = "No notify on output";
const prog_char CANNOT_SET_MIN_VAL[] PROGMEM = "valueMin cannot be set";
const prog_char CANNOT_SET_MAX_VAL[] PROGMEM = "valueMax cannot be set";
const prog_char DESCRIPTION_CANNOT_BE_SET[] PROGMEM = "description cannot be set";
const prog_char NAME_TOO_LONG[] PROGMEM = "name is too long";

void settingsLoad();
void settingsReload();
void settingsSave();

const prog_char CONFIG_VERSION[] PROGMEM = "hc1";
const prog_char STR_INPUT[] PROGMEM = "INPUT";
const prog_char STR_OUTPUT[] PROGMEM = "OUTPUT";
const prog_char PIN_TYPE_ANALOG[] PROGMEM = "ANALOG";
const prog_char PIN_TYPE_DIGITAL[] PROGMEM = "DIGITAL";
const prog_char PIN_NOTIFICATION_SUP[] PROGMEM = "SUP_OR_EQUAL";
const prog_char PIN_NOTIFICATION_INF[] PROGMEM = "INF_OR_EQUAL";

extern uint8_t pinInputSize;
extern uint8_t pinOutputSize;
extern const char *pinDirection[];
extern const char *pinType[];
extern const char *pinNotification[];

extern uint8_t NotifyDstIp[4];
extern uint16_t notifyDstPort;
extern char notifyUrlPrefix[36];
extern t_notify **pinNotifies;


#endif
