#include "time.h"


long receiveBootTime = 0;
long receiveTimestamp = 0;

uint16_t receiveTime(char *time) {
	receiveBootTime = millis();
	atol(time);
}

boolean isTimeReady() {
	return receiveBootTime != 0; // TODO handle overflow //TODO Handle time before fill
}

uint16_t fillWithTimestamp(char *buf) {
	long currentBoot = millis();
	long currentTimestamp = currentBoot + receiveTimestamp - receiveBootTime;
	char* newPos = ltoa(currentTimestamp, buf, 10);
	uint16_t size = 0;
	while (buf != newPos) {
		size++;
		buf = &buf[1];
	}
	return size;
}
