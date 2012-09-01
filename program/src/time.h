#ifndef __TIME_H__
#define __TIME_H__

#include <stdint.h>
#include <Arduino.h>


unsigned long getCurrentPosixTimestamp();
//uint16_t fillWithTimestamp(char *buf);
boolean isTimeReady();
uint16_t receiveTime(char *time);


#endif
