#include "settings-board.h"

void configBoardGetMac(uint8_t mac[6]) {
    memcpy_P(mac, boardDescription.mac, 6);
}
void settingsBoardGetIP(uint8_t ip[4]) {
    eeprom_read_block((void*)ip, &boardSettings.ip, CONFIG_BOARD_IP_SIZE);
}
uint16_t settingsBoardGetPort() {
   return eeprom_read_word(&boardSettings.port);
}
eeprom_char *settingsBoardGetName_E() {
    return (eeprom_char *) &boardSettings.name;
}
eeprom_char *settingsBoardGetNotifyUrl_E() {
    return (eeprom_char *) &boardSettings.notifyUrl;
}

const prog_char *settingsBoardSetNotifyUrl(char *buf, uint16_t len, uint8_t index) {
    if (len >  CONFIG_BOARD_NOTIFY_SIZE - 1) {
        return PSTR("notifyUrl is too long");
    }
    if (strncmp_P(buf, PSTR("http://"), 7)) {
        return PSTR("http only for notifyUrl");
    }

    uint8_t newIp[4];
    if (readIP(&buf[7], strspn_P(&buf[7], IP_CHARACTERS), newIp)) {
        return PSTR("invalid ip in notifyUrl");
    }
    eeprom_write_block(buf, &boardSettings.notifyUrl, len);
    eeprom_write_byte((uint8_t *)&boardSettings.notifyUrl + len, 0);
    settingsReload();
    return 0;
}
const prog_char *settingsBoardSetName(char *buf, uint16_t len, uint8_t index) {
	if (len >  CONFIG_BOARD_NAME_SIZE - 1) {
        return NAME_TOO_LONG;
    }
    eeprom_write_block(buf, &boardSettings.name, len);
    eeprom_write_byte((uint8_t *) &boardSettings.name + len, 0);

    return 0;
}

const prog_char *settingsBoardSetIP(char *buf, uint16_t len, uint8_t index) {
    uint8_t newIp[4];
    if (readIP(buf, len, newIp)) {
        return NOT_VALID_IP;
    }

    uint8_t currentIp[4];
    settingsBoardGetIP(currentIp);
    if (currentIp[0] != newIp[0] || currentIp[1] != newIp[1] || currentIp[2] != newIp[2] || currentIp[3] != newIp[3]) {
        eeprom_write_block(newIp, &boardSettings.ip, 4);
        needReboot = true;
    }
    return 0;
}

const prog_char *settingsBoardSetPort(char *buf, uint16_t len, uint8_t index) {
    for (uint8_t i = 0; i < len; i++) {
        if (buf[i] < '0' || buf[i] > '9') {
            return NOT_VALID_PORT;
        }
    }
    uint16_t port = atoi(buf);
    if (port != settingsBoardGetPort()) {
        eeprom_write_word(&boardSettings.port, port);
        needReboot = true;
    }
    return 0;
}
