#include "restmcu.h"

#ifdef DEBUG
void DEBUG_P(const char PROGMEM *progmem) {
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
#endif
