#include "time.h"
#include "restmcu.h"

unsigned long receiveBootTime = 0;
unsigned long receiveTimestamp = 0;

uint8_t hmacKey[CONFIG_BOARD_KEY_SIZE];
uint8_t hmacKeySize = addToBufferTCP_P((char *)hmacKey, 0, boardDescription.hmacKey);

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

boolean isValidWindow(unsigned long time) {
	unsigned long currentTime = getCurrentPosixTimestamp();
	return time > currentTime - maxValidWindowSecondOneSide
			&& time < currentTime + maxValidWindowSecondOneSide;
}

uint16_t addSecurityToBuffer(char *buf, uint16_t plen) {
    plen = addToBufferTCP_P(buf, plen, HMAC_TIME);
    unsigned long time = getCurrentPosixTimestamp();
	ltoa(time, &buf[plen], 10);
	plen += strlen(&buf[plen]);
    plen = addToBufferTCP_P(buf, plen, PSTR("\r\n"));

    plen = addToBufferTCP_P(buf, plen, HMAC_HASH);

    Sha1.initHmac(hmacKey, hmacKeySize);
    fillHmacMessage(time);
    plen = addToBufferTCPHex(buf, plen, Sha1.resultHmac(), 20);
    plen = addToBufferTCP_P(buf, plen, PSTR("\r\n"));
}
