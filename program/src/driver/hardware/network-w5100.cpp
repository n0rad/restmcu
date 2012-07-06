#include <SPI.h>
#include <Ethernet.h>
#include "../network.h"
#include "w5100.h"


void (*resetFunc)(void) = 0; //declare reset function @ address 0

EthernetServer server(settingsBoardGetPort());

#define BUFFER_SIZE 1000
uint8_t buf[BUFFER_SIZE + 1];

uint8_t buf2[BUFFER_SIZE + 1];

void networkSetup() {
	uint8_t myip[4];
	settingsBoardGetIP(myip);
	IPAddress ip((const uint8_t *) myip);

	uint8_t mac[6];
	configBoardGetMac(mac);

	Ethernet.begin(mac, ip);
	server.begin();
	delay(1000);
}

unsigned long lastFailTime = 0;

EthernetClient sendClient;

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
//		while (client.connected()) {
		if (client.available()) {
			int size = client.read((uint8_t *) buf, BUFFER_SIZE);
			buf[size] = 0;
			size = handleWebRequest((char *) buf, 0, size);
			buf[size] = 0;
			client.println((const char *) buf);
//				break;
		}
//		}
//		delay(1);
		client.stop();
	}
	if (needReboot) {
		resetFunc();
	}
}
