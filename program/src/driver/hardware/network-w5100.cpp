#include <SPI.h>
#include <Ethernet.h>
#include "../network.h"
#include "w5100.h"


void (*resetFunc)(void) = 0; //declare reset function @ address 0

EthernetServer server(settingsBoardGetPort());

#define BUFFER_SIZE 1000
uint8_t buf[BUFFER_SIZE + 1];

void networkSetup(uint8_t *srvMac, uint8_t *srvIp) {
	IPAddress ip(srvIp);
	Ethernet.begin(srvMac, ip);
	server.begin();
	delay(1000);
}

unsigned long lastFailTime = 0;

EthernetClient sendClient;

uint16_t readHttpFrame(EthernetClient client) {
	uint16_t size = 0;

//	while (client.connected()) {
	if (client.available()) {
		size = client.read((uint8_t *) buf, BUFFER_SIZE);
		buf[size] = 0;

		char *found = strstr_P((char *) buf, PSTR("Content-Length: "));
		if (found == 0) {
			return size; // no length so we stop here
		}
		int contentLength = atoi(&found[16]);
		size_t contentPos = strlen(&strstr_P(found, DOUBLE_ENDL)[4]);
		if (contentPos < contentLength) {
			size += client.read((uint8_t *) &buf[size], contentLength - contentPos);
		}
	}
//	}
	return size;
}

void networkManage() {
	uint16_t size;

	if (sendClient.available()) {
//		int size = sendClient.read((uint8_t *) buf2, BUFFER_SIZE);
		size = readHttpFrame(sendClient);
#ifdef HMAC
		if (!isTimeReady()) {
			uint16_t endPos = strstrpos_P((char *) buf, DOUBLE_ENDL);
			receiveTime((char *) &buf[endPos + 4]);
		}
#endif
	}

#ifdef HMAC
	if (!isTimeReady() && sendClient.status() == SnSR::CLOSED && (lastFailTime == 0 || millis() - lastFailTime > dateFailRetryWait)) {
		if (sendClient.connect(NotifyDstIp, notifyDstPort)) {
			int len = clientBuildTimeQuery((char *) buf);
			sendClient.write(buf, len);
		} else {
			lastFailTime = millis();
			sendClient.stop();
		}
	}
#endif

	if (!sendClient.connected()) {
		sendClient.stop();
	}

	if (notification != 0 && sendClient.status() == SnSR::CLOSED) {
		// there is a notif and we are not handling another one
		if (lastFailTime == 0 || millis() - lastFailTime > notifFailRetryWait) {
			if (sendClient.connect(NotifyDstIp, notifyDstPort)) {
				int len = clientBuildNextQuery((char *) buf);
				sendClient.write(buf, len);
			} else {
				lastFailTime = millis();
				sendClient.stop();
			}
		}
	}

	EthernetClient client = server.available();
	if (client) {
		size = readHttpFrame(client);

		if (size > 0) {
			buf[size] = 0;
			size = handleWebRequest((char *) buf, 0, size);
			buf[size] = 0;
			client.println((const char *) buf);
		}

//		delay(1);
		client.stop();
	}
	if (needReboot) {
		resetFunc();
	}
}
