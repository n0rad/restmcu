#include "../board.h"

const prog_char ERROR_LINE_RESERVED[] PROGMEM = "line%d is reserved";

char *boardCheckConfig() {

    int8_t lineId;
    for (uint8_t i = 0; -1 != (lineId = (int8_t) pgm_read_byte(&lineInputDescription[i].lineId)); i++) {
        int type = pgm_read_byte(&lineInputDescription[i].type);
        if (lineId >= 10 && lineId <= 13) {
            return buildGlobalError_P(ERROR_LINE_RESERVED, lineId);
        }
        if (type == ANALOG && lineId <= 13) {
            return buildGlobalError_P(PSTR("line%d cannot be INPUT ANALOG"), lineId);
        }
    }

    for (uint8_t i = 0; -1 != (lineId = (int8_t) pgm_read_byte(&lineOutputDescription[i].lineId)); i++) {
        int type = pgm_read_byte(&lineOutputDescription[i].type);
        if (lineId < 0 || lineId > 13) {
            return buildGlobalError_P(PSTR("line%d cannot be OUTPUT"), lineId);
        }
        if (lineId >= 10 && lineId <= 13) {
            return buildGlobalError_P(ERROR_LINE_RESERVED, lineId);
        }
        if (type == ANALOG && !(lineId == 3 || lineId == 5 || lineId == 6 || lineId == 9 || lineId == 10 || lineId == 11 || lineId >= 14)) {
            return buildGlobalError_P(PSTR("line%d cannot be OUTPUT ANALOG"), lineId);
        }
    }
    return 0;
}
