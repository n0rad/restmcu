#ifndef __DEBOUNCED_H__
#define __DEBOUNCED_H__

#include <avr/pgmspace.h>
#include "../../restmcu-config.h"
#include <Arduino.h>

uint16_t debouncedLineRead(uint8_t lineId, uint8_t type, int8_t PROGMEM params[]);

extern uint16_t debouncedIntegrator[];
extern uint16_t debouncedValue[];

#endif
