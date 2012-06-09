#include <SPI.h>
#include <Ethernet.h>
#include "../network.h"

EthernetServer server(80);

void networkSetup() {
    uint8_t myip[4];
    settingsBoardGetIP(myip);
    IPAddress ip((const uint8_t *) myip);

    uint8_t mac[6];
    configBoardGetMac(mac);

    Ethernet.begin(mac, ip);
}

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
                    for (int analogChannel = 0; analogChannel < 6;
                            analogChannel++) {
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
                } else if (c != '\r') {
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
