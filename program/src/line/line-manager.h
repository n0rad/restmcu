#ifndef LINE_MANAGER_H_
#define LINE_MANAGER_H_

#include <stdint.h>
#include <avr/pgmspace.h>

typedef struct s_lineInputDescription t_lineInputDescription;
typedef struct s_lineOutputDescription t_lineOutputDescription;


typedef float (*LineInputConversion)(uint16_t lineValue);
typedef int16_t (*LineOutputConversion)(float lineValue);

// conversion
float noInputConversion(uint16_t lineValue);
int16_t noOutputConversion(float lineValue);

//init
void defaultOutputLineInit(int8_t lineId, const t_lineOutputDescription *description);
void defaultInputLineInit(int8_t lineId, const t_lineInputDescription *description);

//read write
uint16_t defaultLineRead(uint8_t lineId, uint8_t type, prog_int8_t *params);
void defaultLineWrite(uint8_t lineId, uint8_t type, uint16_t value, prog_int8_t *params);


#include <restmcu-config.h>

const prog_char *setLineValue(uint8_t lineOutputIdx, float value);
float getLineValue(uint8_t lineId);

void lineInit();
void lineCheckInit();
void lineCheckChange();

#endif
