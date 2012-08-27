#include <SPI.h>
#include <Ethernet.h>
#include "../network.h"
#include "w5100.h"


void (*resetFunc)(void) = 0; //declare reset function @ address 0

EthernetServer server(settingsBoardGetPort());

#define BUFFER_SIZE 1000
uint8_t buf[BUFFER_SIZE + 1];

uint8_t buf2[BUFFER_SIZE + 1];

void networkSetup(uint8_t *srvMac, uint8_t *srvIp) {
	IPAddress ip(srvIp);
	Ethernet.begin(srvMac, ip);
	server.begin();
	delay(1000);
}

unsigned long lastFailTime = 0;

EthernetClient sendClient;

uint16_t readRequest(EthernetClient client) {
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
		int currentContentLength = strlen(&strstr_P(found, DOUBLE_ENDL)[4]);
		if (currentContentLength < contentLength) {
			size += client.read((uint8_t *) &buf[size], contentLength - currentContentLength);
		}
	}
//	}
	return size;
}

void networkManage() {

	if (sendClient.available()) {
		int size = sendClient.read((uint8_t *) buf2, BUFFER_SIZE);
	}

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
		uint16_t size = readRequest(client);

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
