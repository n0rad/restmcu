#ifndef __MUX_SHIELD_H__
#define __MUX_SHIELD_H__

#include <avr/pgmspace.h>
#include "../../restmcu-config.h"
#include <Arduino.h>

void muxShieldInputLineInit(int8_t lineId, const t_lineInputDescription *description);
uint16_t muxShieldLineRead(uint8_t lineId, uint8_t type, prog_int8_t params[]);
void muxShieldLineWrite(uint8_t lineId, uint8_t type, uint16_t value, prog_int8_t params[]);

#endif


