#ifndef SERVER_LINE_H
#define SERVER_LINE_H

#include <avr/pgmspace.h>
#include <stdio.h>

#include "../restmcu.h"
#include "../settings/settings-line.h"

uint16_t lineGet(char *buf, uint16_t dat_p, uint16_t plen, t_webRequest *webResource);
uint16_t lineGetSettings(char *buf, uint16_t dat_p, uint16_t plen, t_webRequest *webResource);
uint16_t linePutSettings(char *buf, uint16_t dat_p, uint16_t plen, t_webRequest *webResource);
uint16_t linePutValue(char *buf, uint16_t dat_p, uint16_t plen, t_webRequest *webResource);
uint16_t lineGetValue(char *buf, uint16_t dat_p, uint16_t plen, t_webRequest *webResource);

#include "server.h"

const prog_char LINE_MIN[] PROGMEM = "\",\"valueMin\":";
const prog_char LINE_MAX[] PROGMEM = ",\"valueMax\":";

const prog_char LINE_PARAM_NOTIFIES[] PROGMEM = "notifies";
const prog_char LINE_PARAM_NOTIFY_COND[] PROGMEM = "notifyCondition";
const prog_char LINE_PARAM_NOTIFY_VALUE[] PROGMEM = "notifyValue";
const prog_char LINE_PARAM_VALUE[] PROGMEM = "value";
const prog_char LINE_PARAM_STARTVALUE[] PROGMEM = "startValue";

const t_json linePutNotifiesElements[] PROGMEM = {
        {LINE_PARAM_NOTIFY_COND, settingsLineSetNotifyCond},
        {LINE_PARAM_NOTIFY_VALUE, settingsLineSetNotifyValue},
        {0, 0}
};

const t_json linePutSettingsElements[] PROGMEM = {
        {PARAM_NAME, settingsLineSetName},
        {LINE_PARAM_NOTIFIES, 0, (t_json *) linePutNotifiesElements, settingsLineHandleLineNotifyArray}, // array of objects
        {0, 0}
};

const t_json linePutSettingsObj PROGMEM = {0, 0, (t_json *)linePutSettingsElements, 0};

#endif
