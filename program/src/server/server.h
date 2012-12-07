#ifndef SERVER_H_
#define SERVER_H_

#include <avr/eeprom.h>
#include <string.h>

#include "../config/config-manager.h"
#include "../settings/settings.h"
#include "../util/buffer.h"
#include "../restmcu.h"
#include "../util/json.h"

const char DOUBLE_ENDL[] PROGMEM = "\r\n\r\n";
const char RESOURCE_LINE[] PROGMEM = "/line/";
const char RESOURCE_LINE_SUFFIX[] PROGMEM = "/value ";
const char RESOURCE_BOARD[] PROGMEM = "/ ";
const char RESOURCE_SETTINGS[] PROGMEM = "/settings ";
const char RESOURCE_RESET[] PROGMEM = "/reset ";
const char RESOURCE_NOTIFY[] PROGMEM = "/notify ";

const char HEADER_HTTP[] PROGMEM = "HTTP/1.0 ";
const char HEADER_CONTENT[] PROGMEM = "Content-Type: application/json";
const char HEADER_200[] PROGMEM = "200 OK";
const char HEADER_404[] PROGMEM = "404 Not Found";
const char HEADER_400[] PROGMEM = "400 Bad Request";
const char HEADER_413[] PROGMEM = "413 Request Entity Too Large";
const char HEADER_403[] PROGMEM = "403 Forbidden";

const char JSON_DESCRIPTION[] PROGMEM = "\",\"description\":\"";
const char ERROR_MSG_UPDATE[] PROGMEM = "UpdateException";
const char ERROR_MSG_SECURITY[] PROGMEM = "SecurityException";
const char ERROR_MSG_NOT_FOUND[] PROGMEM = "NotFoundException";

const char PARAM_NAME[] PROGMEM = "name";

uint16_t startResponseHeader(char **buf, const char PROGMEM *codeMsg);
uint16_t appendErrorMsg_P(char *buf, uint16_t plen, const char PROGMEM *type, const char PROGMEM *msg);


#include "server-board.h"
#include "server-line.h"


uint16_t handleWebRequest(char *buf, uint16_t dataPointer, uint16_t dataLen);


typedef uint16_t (*ResourceFunc)(char *buf, uint16_t dat_p, uint16_t plen, t_webRequest *lineId);

struct s_resource {
  const char PROGMEM *method;
  const char PROGMEM *query;
  const char PROGMEM *suffix;
  ResourceFunc resourceFunc;
} const resources[] PROGMEM = {
  {PUT, RESOURCE_LINE, RESOURCE_LINE_SUFFIX, linePutValue},
  {GET, RESOURCE_LINE, RESOURCE_LINE_SUFFIX, lineGetValue},
  {GET, RESOURCE_LINE, 0, lineGet},
  {GET, RESOURCE_LINE, RESOURCE_SETTINGS, lineGetSettings},
  {PUT, RESOURCE_LINE,  RESOURCE_SETTINGS, linePutSettings},
  {GET, RESOURCE_BOARD, 0, boardGet},
  {GET, RESOURCE_SETTINGS, 0, boardGetSettings},
  {PUT, RESOURCE_SETTINGS, 0, boardPutSettings},
  {PUT, RESOURCE_RESET, 0, boardReset},
  {PUT, RESOURCE_NOTIFY, 0, boardNotify},
  {0, 0, 0, 0}
};

#endif
