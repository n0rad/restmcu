#include "client.h"

#define TCP_DATA_P          0x36 // FOR ENCJ

uint16_t startRequestHeader(char **buf, const char PROGMEM *method) {
//    *buf = &((*buf)[TCP_DATA_P]);
    uint16_t plen;
    plen = addToBufferTCP_P(*buf, 0, method);
    return plen;
}
