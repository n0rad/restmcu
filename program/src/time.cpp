#include "time.h"
#include "restmcu.h"

unsigned long receiveBootTime = 0;
unsigned long receiveTimestamp = 0;

uint16_t receiveTime(char *time) {
	receiveBootTime = millis() / 1000;
	receiveTimestamp = atol(time);
}

boolean isTimeReady() {
	return receiveTimestamp != 0; // TODO handle overflow //TODO Handle time before fill
}

unsigned long getCurrentPosixTimestamp() {
	long currentBoot = millis() / 1000;
	return currentBoot + receiveTimestamp - receiveBootTime;

}
