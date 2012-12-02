#ifndef __ROTARY_ENCODER_H__
#define __ROTARY_ENCODER_H__

#include <Encoder.h>
#include "../../restmcu-config.h"


void rotaryEncoderLineInit(int8_t lineId, const t_lineInputDescription *description);
uint16_t rotaryEncoderLineRead(uint8_t lineId, uint8_t type, int8_t PROGMEM params[]);



#endif
