#include "mux-shield.h"
#include "../../restmcu.h"

void muxShieldInputLineInit(int8_t lineId, const t_lineInputDescription *description) {
	DEBUG_PRINTLN("init shield");
	prog_int8_t *params = (prog_int8_t *) pgm_read_word(&description->params);
	DEBUG_PRINTDEC(pgm_read_byte(&params[0]));
	DEBUG_PRINTLN();
	pinMode(pgm_read_byte(&params[0]), OUTPUT);
	pinMode(pgm_read_byte(&params[1]), OUTPUT);
	pinMode(pgm_read_byte(&params[2]), OUTPUT);
	pinMode(pgm_read_byte(&params[3]), OUTPUT);
}

uint16_t muxShieldLineRead(uint8_t lineId, uint8_t type, prog_int8_t params[]) {
	pinMode(pgm_read_byte(&params[5]), INPUT);
	pinMode(pgm_read_byte(&params[6]), INPUT);
	pinMode(pgm_read_byte(&params[7]), INPUT);

	int i = pgm_read_byte(&params[0]) - lineId;
    digitalWrite(pgm_read_byte(&params[1]), (i&15)>>3);
    digitalWrite(pgm_read_byte(&params[2]), (i&7)>>2);
    digitalWrite(pgm_read_byte(&params[3]), (i&3)>>1);
    digitalWrite(pgm_read_byte(&params[4]), (i&1));

	if (type == ANALOG) {
        return analogRead(pgm_read_byte(&params[5]));
    } else {
    	return !digitalRead(pgm_read_byte(&params[5]));
    }
}
void muxShieldLineWrite(uint8_t lineId, uint8_t type, uint16_t value, prog_int8_t params[]) {
	int i = params[0] - lineId;

	pinMode(params[5], OUTPUT);
	//TODO read more pins if we have more than 3 multiplexer
	pinMode(params[6], INPUT);
	pinMode(params[7], INPUT);

	digitalWrite(params[1], (i&15)>>3);
	digitalWrite(params[2], (i&7)>>2);
	digitalWrite(params[3], (i&3)>>1);
	digitalWrite(params[4], (i&1));

    if (type == ANALOG) {
        analogWrite(params[5], value); // not exist
    } else {
        digitalWrite(params[5], value);
    }
}
