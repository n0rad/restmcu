#include "restmcu.h"
#include <Arduino.h>
#include "driver/network.h"
#include "settings/settings.h"
#include "client/client.h"
#include "config/config-manager.h"

#include <SPI.h>
#include <Ethernet.h>

char *definitionError;
char *criticalProblem_p;
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

EthernetServer server(80);
void networkManage() {
    // listen for incoming clients
     EthernetClient client = server.available();
     if (client) {
       // an http request ends with a blank line
       boolean currentLineIsBlank = true;
       while (client.connected()) {
         if (client.available()) {
           char c = client.read();
           // if you've gotten to the end of the line (received a newline
           // character) and the line is blank, the http request has ended,
           // so you can send a reply
           if (c == '\n' && currentLineIsBlank) {
             // send a standard http response header
             client.println(F("HTTP/1.1 200 OK"));
             client.println(F("Content-Type: text/html"));
             client.println();

             // output the value of each analog input pin
             for (int analogChannel = 0; analogChannel < 6; analogChannel++) {
               client.print(F("analog input "));
               client.print(analogChannel);
               client.print(F(" is "));
               client.print(analogRead(analogChannel));
               client.println(F("<br />"));
             }
             break;
           }
           if (c == '\n') {
             // you're starting a new line
             currentLineIsBlank = true;
           }
           else if (c != '\r') {
             // you've gotten a character on the current line
             currentLineIsBlank = false;
           }
         }
       }
       // give the web browser time to receive the data
       delay(1);
       // close the connection:
       client.stop();
     }
}

byte mac[] = { 0xDE, 0xAD, 0xBE, 0xEF, 0xFE, 0xED };

int main() {
    init(); // load init of arduino

#ifdef DEBUG
    delay(3000);
    Serial.begin(9600);
#endif

    definitionError = configCheck();
    settingsLoad();
//    networkSetup();

    uint8_t myip[4];
    settingsBoardGetIP(myip);
     IPAddress ip((const uint8_t *) myip);
    Ethernet.begin(mac, ip);

    if (!definitionError) {
        pinInit();
    }

    clientBoardNotify(BOARD_NOTIFY_BOOT);

    while (1) {
        networkManage();
        if (!definitionError) {
            pinCheckChange();
        }
    }

    return 0;
}
