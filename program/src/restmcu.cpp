#include "restmcu.h"
#include <Arduino.h>
#include "driver/network.h"
#include "settings/settings.h"
#include "client/client.h"
#include "config/config-manager.h"

uint8_t needReboot = false;
t_notification *notification = 0;

void DEBUG_P(const prog_char *progmem) {
    char c;
    while ((c = pgm_read_byte(progmem++))) {
        DEBUG_PRINT(c);
    }
    DEBUG_PRINTLN();
}

void DEBUG_E(const char *eeprom) {
    char c;
    while ((c = eeprom_read_byte((uint8_t *)eeprom++))) {
        DEBUG_PRINT(c);
    }
    DEBUG_PRINTLN();
}

int main() {
    init(); // load init of arduino

#ifdef DEBUG
    delay(3000);
    Serial.begin(9600);
#endif

    settingsLoad();
    networkSetup();

    pinInit();

    clientBoardNotify(BOARD_NOTIFY_BOOT);

    while (1) {
        networkManage();
        pinCheckChange();
    }

    return 0;
}



//#include <SPI.h>
//#include <Ethernet.h>
//
//// Enter a MAC address and IP address for your controller below.
//// The IP address will be dependent on your local network:
//byte mac[] = {
//  0xDE, 0xAD, 0xBE, 0xEF, 0xFE, 0xED };
//IPAddress ip(192,168,42, 244);
//
//// Initialize the Ethernet server library
//// with the IP address and port you want to use
//// (port 80 is default for HTTP):
//EthernetServer server(80);
//
//void setup() {
// // Open serial communications and wait for port to open:
////  Serial.begin(9600);
////   while (!Serial) {
// //   ; // wait for serial port to connect. Needed for Leonardo only
// // }
//
//
//  // start the Ethernet connection and the server:
//  Ethernet.begin(mac, ip);
//  server.begin();
////  Serial.print("server is at ");
////  Serial.println(Ethernet.localIP());
//}

//#define BUFFER_SIZE 1000
//uint8_t buf[BUFFER_SIZE + 1];
//
//
//void loop() {
//  EthernetClient client = server.available();
//  if (client) {
//    boolean currentLineIsBlank = true;
////    while (client.connected()) {
////      if (client.available()) {
////        char c = client.read();
//        int size = client.read((uint8_t *) buf, BUFFER_SIZE);
////        if (c == '\n' && currentLineIsBlank) {
//          client.print(F("HTTP/1.0 200 OK\r\nContent-Type: text/html\r\n\r\n<!DOCTYPE HTML>\r\n"));
////          client.println();
////          client.println(F(""));
////          client.println(F("<html>"));
////          client.println(F("</html>"));
////          break;
////        }
////        if (c == '\n') {
////          currentLineIsBlank = true;
////        }
////        else if (c != '\r') {
////          currentLineIsBlank = false;
////        }
////      }
////    }
////    delay(1);
//    client.stop();
//  }
//}
//




//int main() {
//    init(); // load init of arduino
//
////#ifdef DEBUG
////    delay(3000);
////    Serial.begin(9600);
////#endif
//
//
//    setup();
//
//    while (1) {
//    	loop();
//    }
//
//    return 0;
//}
