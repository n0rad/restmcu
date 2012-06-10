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

void networkManage() {
    EthernetClient client = server.available();
    if (client) {
        while (client.connected()) {
            if (client.available()) {
                int size = client.readBytes((char *)buf, BUFFER_SIZE);
                buf[size] = 0;
//                DEBUG_PRINTLN((char *)buf);
                size = handleWebRequest((char *) buf, 0, size);
                buf[size] = 0;
                client.println((const char *)buf);
                break;
            }
        }
        delay(1);
        client.stop();
    }
    if (needReboot) {
        DEBUG_PRINTLN("REBOOT");
        resetFunc();
    }
}
