#include <avr/pgmspace.h>

#include "../driver/board.h"


const int8_t configGetInputLineIdx(uint8_t lineIdToFind) {
    int8_t lineId;
    for (uint8_t i = 0; -1 != (lineId = (int8_t) pgm_read_byte(&lineInputDescription[i].lineId)); i++) {
        if (lineId == lineIdToFind) {
            return i;
        }
    }
    return -1;
}

const int8_t configGetOutputLineIdx(uint8_t lineIdToFind) {
    int8_t lineId;
    for (uint8_t i = 0; -1 != (lineId = (int8_t) pgm_read_byte(&lineOutputDescription[i].lineId)); i++) {
        if (lineId == lineIdToFind) {
            return i;
        }
    }
    return -1;
}
