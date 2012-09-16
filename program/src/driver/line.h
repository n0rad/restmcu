#ifndef LINE_H
#define LINE_H

#include "../restmcu.h"
#include <restmcu-config.h>

uint16_t lineReadValue(uint8_t line, uint8_t type);
void lineWriteValue(uint8_t line, uint16_t value);

#endif
