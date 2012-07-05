#include <avr/pgmspace.h>

#include "../driver/board.h"

char *buildGlobalError_P(const prog_char *progmem_s, int pin) {
    char *ptr = (char *) malloc(100 * sizeof(char));
    memset(ptr, 0, 100 * sizeof(char));
    sprintf_P(ptr, progmem_s, pin);
    return ptr;
}

const int8_t configGetInputPinIdx(uint8_t pinIdToFind) {
    int8_t pinId;
    for (uint8_t i = 0; -1 != (pinId = (int8_t) pgm_read_byte(&pinInputDescription[i].pinId)); i++) {
        if (pinId == pinIdToFind) {
            return i;
        }
    }
    return -1;
}

const int8_t configGetOutputPinIdx(uint8_t pinIdToFind) {
    int8_t pinId;
    for (uint8_t i = 0; -1 != (pinId = (int8_t) pgm_read_byte(&pinOutputDescription[i].pinId)); i++) {
        if (pinId == pinIdToFind) {
            return i;
        }
    }
    return -1;
}
