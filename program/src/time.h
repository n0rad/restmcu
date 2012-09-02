#ifndef __TIME_H__
#define __TIME_H__

#include <stdint.h>
#include <Arduino.h>
#include "util/buffer.h"


unsigned long getCurrentPosixTimestamp();
//uint16_t fillWithTimestamp(char *buf);
boolean isTimeReady();
uint16_t receiveTime(char *time);

uint16_t addSecurityToBuffer(char *buf, uint16_t plen);

extern uint8_t hmacKey[CONFIG_BOARD_KEY_SIZE];
extern uint8_t hmacKeySize;


#endif
