#include <SPI.h>
#include <Ethernet.h>
#include "../network.h"

void (*resetFunc)(void) = 0; //declare reset function @ address 0

EthernetServer server(80);
EthernetClient client;

#define BUFFER_SIZE 1000
uint8_t buf[BUFFER_SIZE + 1];

void networkSetup() {
	uint8_t myip[4];
	settingsBoardGetIP(myip);
	IPAddress ip((const uint8_t *) myip);

	uint8_t mac[6];
	configBoardGetMac(mac);

	Ethernet.begin(mac, ip);
}

const unsigned long requestInterval = 60000; // delay between requests
unsigned long lastAttemptTime = 0; // last time you connected to the server, in milliseconds

void networkManage() {
	EthernetClient client = server.available();
	client.setTimeout(1);
	if (client) {
		while (client.connected()) {
			if (client.available()) {
				int size = client.readBytes((char *) buf, BUFFER_SIZE);
				buf[size] = 0;
				size = handleWebRequest((char *) buf, 0, size);
				buf[size] = 0;
				client.println((const char *) buf);
				break;
			}
		}
		delay(1);
		client.stop();
	}
	if (notification != 0) {
		if (client.connect(NotifyDstIp, 8080)) {
			int len = clientBuildNextQuery((char *) buf);
			client.write(buf, len);
//			if (client.connected()) {
				delay(1);
				client.stop();
//			}
		}
//		lastAttemptTime = millis();
	}
	if (needReboot) {
		resetFunc();
	}
}
