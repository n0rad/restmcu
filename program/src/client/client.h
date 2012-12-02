#ifndef __CLIENT_H__
#define __CLIENT_H__

#include <avr/pgmspace.h>
#include <stdint.h>
#include "../util/buffer.h"

uint16_t startRequestHeader(char **buf, const char PROGMEM *method);

#endif
