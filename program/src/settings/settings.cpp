#include "settings.h"

uint8_t NotifyDstIp[4];
uint16_t notifyDstPort;
char notifyUrlPrefix[36];

t_notify **lineNotifies = 0;

uint8_t lineInputSize = 0;
uint8_t lineOutputSize = 0;

const char *lineType[] = { LINE_TYPE_ANALOG, LINE_TYPE_DIGITAL};
const char *lineNotification[] = { LINE_NOTIFICATION_SUP, LINE_NOTIFICATION_INF};

static void eeprom_write_block_P(uint8_t *eepromPos, char PROGMEM *progPos, uint16_t len) {
    for (uint16_t i = 0; i < len; i++) {
        uint8_t data = pgm_read_byte(&progPos[i]);
        eeprom_write_byte(&eepromPos[i], data);
    }
}

void settingsReload() {
    char tmpNotifyUrl[CONFIG_BOARD_NOTIFY_SIZE];
    eeprom_read_block((void*)&tmpNotifyUrl, &boardSettings.notifyUrl, CONFIG_BOARD_NOTIFY_SIZE);

    // notify ip
    uint16_t endIP = strspn_P(&tmpNotifyUrl[7], IP_CHARACTERS);
    readIP(&tmpNotifyUrl[7], endIP, NotifyDstIp);

    // notify port
    uint16_t startUrl = 7 + endIP;
    if (tmpNotifyUrl[7 + endIP] == ':') {
        notifyDstPort = atoi(&tmpNotifyUrl[startUrl + 1]);
        startUrl += strspn_P(&tmpNotifyUrl[startUrl + 1], PSTR("0123456789")) + 1;
    } else {
        notifyDstPort = 80;
    }

    // notify url
    uint16_t len = strlen(&tmpNotifyUrl[startUrl]);
    memcpy(notifyUrlPrefix, &tmpNotifyUrl[startUrl], len + 1);

    // notifies
    for (uint8_t i = 0; i < lineInputSize; i++) {
        for (uint8_t j = 0; j < 4; j++) {
            eeprom_read_block((void *)&lineNotifies[i][j], &lineInputSettings[i].notifies[j], sizeof(t_notify));
        }
    }

}

void settingsLoad() {
    // find size of line list
    for (; -1 != (int8_t) pgm_read_byte(&lineInputDescription[lineInputSize].lineId); lineInputSize++);
    for (; -1 != (int8_t) pgm_read_byte(&lineOutputDescription[lineOutputSize].lineId); lineOutputSize++);

    // allocate notifies in ram
    lineNotifies = (t_notify **) malloc(lineInputSize * sizeof(t_notify *));
    for (uint8_t i = 0; i < lineInputSize; i++) {
        lineNotifies[i] = (t_notify *) malloc(4 * sizeof(t_notify));
    }

    settingsReload();
}
