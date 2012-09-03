#ifndef __TIME_H__
#define __TIME_H__

#include <stdint.h>
#include <Arduino.h>
#include "util/buffer.h"

const long maxValidWindowSecondOneSide = 60; // 60s

unsigned long getCurrentPosixTimestamp();
boolean isTimeReady();
uint16_t receiveTime(char *time);
boolean isValidWindow(unsigned long time);

uint16_t addSecurityToBuffer(char *buf, uint16_t plen);

extern uint8_t hmacKey[CONFIG_BOARD_KEY_SIZE];
extern uint8_t hmacKeySize;


#endif
