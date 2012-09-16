#ifndef LINE_MANAGER_H_
#define LINE_MANAGER_H_

#include <stdint.h>

/////////////////////////
// conversion
/////////////////////////
typedef float (*LineInputConversion)(uint16_t lineValue);
typedef int16_t (*LineOutputConversion)(float lineValue);

float noInputConversion(uint16_t lineValue);
int16_t noOutputConversion(float lineValue);

/////////////////////////
// read / write
/////////////////////////
typedef uint16_t (*LineRead)(uint8_t lineId, uint8_t type);
typedef void (*LineWrite)(uint8_t lineId, uint8_t type, uint16_t value);

uint16_t defaultLineRead(uint8_t lineId, uint8_t type);
void defaultLineWrite(uint8_t lineId, uint8_t type, uint16_t value);




#include <restmcu-config.h>

const prog_char *setLineValue(uint8_t lineOutputIdx, float value);
float getLineValue(uint8_t lineId);

void lineInit();
void lineCheckInit();
void lineCheckChange();

#endif
