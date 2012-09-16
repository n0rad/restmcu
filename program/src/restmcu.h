#ifndef HCC_H
#define HCC_H

#include <stdint.h>

#define HARDWARE "Arduino Duemilanove / Nuelectronics enc28j60 Ethernet Shield V1.1"

const long notifFailRetryWait = 60000; // 60s
const long dateFailRetryWait = 1000; // 1s


#include "debug.h"
#include "restmcu-config.h"

const prog_char HMAC_TIME[] PROGMEM = "Hmac-Time: ";
const prog_char HMAC_HASH[] PROGMEM = "Hmac-Hash: ";

const prog_char GET[] PROGMEM = "GET ";
const prog_char PUT[] PROGMEM = "PUT ";
const prog_char JSON_STR_END[] PROGMEM = "\"}";

typedef struct s_webRequest {
    const struct s_resource *resource;
    uint8_t lineIdx;
} t_webRequest;


extern char *criticalProblem_p;
extern char *definitionError;
extern uint8_t needReboot;

extern uint8_t srvIp[4];
extern uint8_t srvMac[6];
extern uint16_t srvPort;


int getFreeMemory();

#endif
