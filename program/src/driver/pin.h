#ifndef PIN_H
#define PIN_H

#include "../restmcu.h"
#include <restmcu-config.h>

uint16_t pinReadValue(uint8_t pin, uint8_t type);
void pinWriteValue(uint8_t pin, uint16_t value);

#endif
