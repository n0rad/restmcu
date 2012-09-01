#include "client-time.h"

uint16_t clientBuildTimeQuery(char *buf) {
    uint16_t plen;
    plen = startRequestHeader(&buf, GET);
    uint16_t start = plen;
    plen = addToBufferTCP(buf, plen, notifyUrlPrefix);
    plen = addToBufferTCP_P(buf, plen, PSTR("/time HTTP/1.0\r\nContent-Type: application/json\r\nConnection: Close\r\n\r\n"));
    return plen;
}
