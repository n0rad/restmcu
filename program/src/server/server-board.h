#ifndef SERVER_BOARD_H
#define SERVER_BOARD_H

#include <string.h>

#include "../restmcu.h"
#include "../settings/settings-board.h"
#include "../util/buffer.h"
#include "../client/client.h"

uint16_t boardGet(char *buf, uint16_t dat_p, uint16_t plen, t_webRequest *webResource);
uint16_t boardGetSettings(char *buf, uint16_t dat_p, uint16_t plen, t_webRequest *webResource);
uint16_t boardPutSettings(char *buf, uint16_t dat_p, uint16_t plen, t_webRequest *webResource);
uint16_t boardReset(char *buf, uint16_t dat_p, uint16_t plen, t_webRequest *webResource);
uint16_t boardReInit(char *buf, uint16_t dat_p, uint16_t plen, t_webRequest *webResource);
uint16_t boardNotify(char *buf, uint16_t dat_p, uint16_t plen, t_webRequest *webResource);

#include "server.h"

const prog_char BOARD_PARAM_NOTIFYURL[] PROGMEM = "notifyUrl";
const prog_char BOARD_PARAM_IP[] PROGMEM = "ip";
const prog_char BOARD_PARAM_PORT[] PROGMEM = "port";

const t_json boardPutSettingsElements[] PROGMEM = {
        {PARAM_NAME, settingsBoardSetName, 0, 0},
        {BOARD_PARAM_NOTIFYURL, settingsBoardSetNotifyUrl, 0, 0},
        {BOARD_PARAM_IP, settingsBoardSetIP, 0, 0},
        {BOARD_PARAM_PORT, settingsBoardSetPort, 0, 0},
        {0, 0, 0, 0}
};

const t_json boardPutSettingsObj PROGMEM = {0, 0, (t_json *)boardPutSettingsElements, 0};

#endif
