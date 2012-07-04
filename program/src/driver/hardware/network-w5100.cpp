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

//void networkManage() {
//	EthernetClient client = server.available();
//	if (client) {
////		Serial.println("new client");
//		// an http request ends with a blank line
//		boolean currentLineIsBlank = true;
//		while (client.connected()) {
//			if (client.available()) {
//				////
//
//				char c = client.read();
////				Serial.write(c);
//				// if you've gotten to the end of the line (received a newline
//				// character) and the line is blank, the http request has ended,
//				// so you can send a reply
//				if (c == '\n' && currentLineIsBlank) {
//					// send a standard http response header
//					client.println("HTTP/1.1 200 OK");
//					client.println("Content-Type: application/json");
//					client.println("Connnection: close");
//					client.println();
//					client.println("{}");
////					client.println("<html>");
////					// add a meta refresh tag, so the browser pulls again every 5 seconds:
////					client.println(
////							"<meta http-equiv=\"refresh\" content=\"5\">");
////					// output the value of each analog input pin
////					for (int analogChannel = 0; analogChannel < 6;
////							analogChannel++) {
////						int sensorReading = analogRead(analogChannel);
////						client.print("analog input ");
////						client.print(analogChannel);
////						client.print(" is ");
////						client.print(sensorReading);
////						client.println("<br />");
////					}
////					client.println("</html>");
//					break;
//				}
//				if (c == '\n') {
//					// you're starting a new line
//					currentLineIsBlank = true;
//				} else if (c != '\r') {
//					// you've gotten a character on the current line
//					currentLineIsBlank = false;
//				}
//			}
//		}
//		// give the web browser time to receive the data
//		delay(1);
//		// close the connection:
//		client.stop();
////		Serial.println("client disonnected");
//	}
//}



void networkManage() {
	EthernetClient client = server.available();
//	client.setTimeout(1);
	if (client) {
		while (client.connected()) {
			if (client.available()) {
				int size = client.read((uint8_t *) buf, BUFFER_SIZE);
				buf[size] = 0;
				size = handleWebRequest((char *) buf, 0, size);
				buf[size] = 0;
				client.println((const char *) buf);
				break;
			}
		}
//		delay(1);
		client.stop();
	}
	if (notification != 0) {
		if (client.connect(NotifyDstIp, 8080)) {
			int len = clientBuildNextQuery((char *) buf);
			client.write(buf, len);
//			if (client.connected()) {
//				delay(1);
				client.stop();
//			}
		}
//		lastAttemptTime = millis();
	}
	if (needReboot) {
		resetFunc();
	}
}
