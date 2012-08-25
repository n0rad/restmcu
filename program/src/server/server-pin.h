#ifndef SERVER_PIN_H
#define SERVER_PIN_H

#include <avr/pgmspace.h>
#include <stdio.h>

#include "../restmcu.h"
#include "../settings/settings-pin.h"

uint16_t pinGet(char *buf, uint16_t dat_p, uint16_t plen, t_webRequest *webResource);
uint16_t pinGetSettings(char *buf, uint16_t dat_p, uint16_t plen, t_webRequest *webResource);
uint16_t pinPutSettings(char *buf, uint16_t dat_p, uint16_t plen, t_webRequest *webResource);
uint16_t pinPutValue(char *buf, uint16_t dat_p, uint16_t plen, t_webRequest *webResource);
uint16_t pinGetValue(char *buf, uint16_t dat_p, uint16_t plen, t_webRequest *webResource);

#include "server.h"

const prog_char PIN_MIN[] PROGMEM = "\",\"valueMin\":";
const prog_char PIN_MAX[] PROGMEM = ",\"valueMax\":";

const prog_char PIN_PARAM_NOTIFIES[] PROGMEM = "notifies";
const prog_char PIN_PARAM_NOTIFY_COND[] PROGMEM = "notifyCondition";
const prog_char PIN_PARAM_NOTIFY_VALUE[] PROGMEM = "notifyValue";
const prog_char PIN_PARAM_VALUE[] PROGMEM = "value";
const prog_char PIN_PARAM_STARTVALUE[] PROGMEM = "startValue";

const t_json pinPutNotifiesElements[] PROGMEM = {
        {PIN_PARAM_NOTIFY_COND, settingsPinSetNotifyCond},
        {PIN_PARAM_NOTIFY_VALUE, settingsPinSetNotifyValue},
        {0, 0}
};

const t_json pinPutSettingsElements[] PROGMEM = {
        {PARAM_NAME, settingsPinSetName},
        {PIN_PARAM_NOTIFIES, 0, (t_json *) pinPutNotifiesElements, settingsPinHandlePinNotifyArray}, // array of objects
        {0, 0}
};

const t_json pinPutSettingsObj PROGMEM = {0, 0, (t_json *)pinPutSettingsElements, 0};

#endif
