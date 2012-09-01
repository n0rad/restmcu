#include "restmcu.h"
#include <Arduino.h>
#include "driver/network.h"
#include "settings/settings.h"
#include "client/client-notify.h"
#include "config/config-manager.h"

uint8_t needReboot = false;
t_notification *notification = 0;
uint8_t srvIp[4];
uint8_t srvMac[6];
uint16_t srvPort;

int main() {
    init(); // load init of arduino

#ifdef DEBUG
    delay(3000);
    Serial.begin(9600);
#endif

//    srandom(42);
//    random();
//    millis();

    settingsLoad();

	settingsBoardGetIP(srvIp);
	configBoardGetMac(srvMac);
	srvPort = settingsBoardGetPort();

    networkSetup(srvMac, srvIp);

    pinInit();

    clientBoardNotify(BOARD_NOTIFY_BOOT);

    while (1) {
        networkManage();
        pinCheckChange();
    }

    return 0;
}
