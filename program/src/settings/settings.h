#ifndef SETTINGS_H
#define SETTINGS_H


#include <stdlib.h>
#include <stdint.h>
#include <avr/pgmspace.h>
#include <avr/eeprom.h>

#include <restmcu-config.h>
#include "../restmcu.h"
#include "../line/line-manager.h"
#include "../util/mylibc.h"


const char IP_CHARACTERS[] PROGMEM = "0123456789.";

const char TOO_MANY_NOTIFY[] PROGMEM = "Too many notify";
const char NO_NOTIFY_OUTPUT[] PROGMEM = "No notify on output";
const char CANNOT_SET_MIN_VAL[] PROGMEM = "valueMin cannot be set";
const char CANNOT_SET_MAX_VAL[] PROGMEM = "valueMax cannot be set";
const char DESCRIPTION_CANNOT_BE_SET[] PROGMEM = "description cannot be set";
const char NAME_TOO_LONG[] PROGMEM = "name is too long";

void settingsLoad();
void settingsReload();
void settingsSave();

const char CONFIG_VERSION[] PROGMEM = "hc1";
const char STR_INPUT[] PROGMEM = "INPUT";
const char STR_OUTPUT[] PROGMEM = "OUTPUT";
const char LINE_TYPE_ANALOG[] PROGMEM = "ANALOG";
const char LINE_TYPE_DIGITAL[] PROGMEM = "DIGITAL";
const char LINE_NOTIFICATION_SUP[] PROGMEM = "SUP_OR_EQUAL";
const char LINE_NOTIFICATION_INF[] PROGMEM = "INF_OR_EQUAL";

extern uint8_t lineInputSize;
extern uint8_t lineOutputSize;
extern const char *lineDirection[];
extern const char *lineType[];
extern const char *lineNotification[];

extern uint8_t NotifyDstIp[4];
extern uint16_t notifyDstPort;
extern char notifyUrlPrefix[36];
extern t_notify **lineNotifies;


#endif
