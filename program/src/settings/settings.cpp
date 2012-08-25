#include "settings.h"

uint8_t NotifyDstIp[4];
uint16_t notifyDstPort;
char notifyUrlPrefix[36];

t_notify **pinNotifies = 0;

uint8_t pinInputSize = 0;
uint8_t pinOutputSize = 0;

const prog_char *pinType[] = { PIN_TYPE_ANALOG, PIN_TYPE_DIGITAL};
const prog_char *pinNotification[] = { PIN_NOTIFICATION_SUP, PIN_NOTIFICATION_INF};

static void eeprom_write_block_P(uint8_t *eepromPos, prog_char *progPos, uint16_t len) {
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
    for (uint8_t i = 0; i < pinInputSize; i++) {
        for (uint8_t j = 0; j < 4; j++) {
            eeprom_read_block((void *)&pinNotifies[i][j], &pinInputSettings[i].notifies[j], sizeof(t_notify));
        }
    }

}

void settingsLoad() {
    // find size of pin list
    for (; -1 != (int8_t) pgm_read_byte(&pinInputDescription[pinInputSize].pinId); pinInputSize++);
    for (; -1 != (int8_t) pgm_read_byte(&pinOutputDescription[pinOutputSize].pinId); pinOutputSize++);

    // allocate notifies in ram
    pinNotifies = (t_notify **) malloc(pinInputSize * sizeof(t_notify *));
    for (uint8_t i = 0; i < pinInputSize; i++) {
        pinNotifies[i] = (t_notify *) malloc(4 * sizeof(t_notify));
    }

    settingsReload();
}
