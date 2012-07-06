#include <avr/pgmspace.h>

#include "../driver/board.h"


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
