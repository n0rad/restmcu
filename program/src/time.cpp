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

uint16_t addSecurityToBuffer(char *buf, uint16_t plen) {
    plen = addToBufferTCP_P(buf, plen, HMAC_TIME);
    unsigned long time = getCurrentPosixTimestamp();
	ltoa(time, &buf[plen], 10);
	plen += strlen(&buf[plen]);
    plen = addToBufferTCP_P(buf, plen, PSTR("\r\n"));

    plen = addToBufferTCP_P(buf, plen, HMAC_HASH);

    uint8_t key[CONFIG_BOARD_KEY_SIZE];
    uint8_t hmacKeySize = addToBufferTCP_P((char *)key, 0, boardDescription.hmacKey);
    Sha1.initHmac(key, hmacKeySize);
    fillHmacMessage(time);
    plen = addToBufferTCPHex32(buf, plen, Sha1.resultHmac());
    plen = addToBufferTCP_P(buf, plen, PSTR("\r\n"));
}
