#include "mux-shield.h"
#include "../../restmcu.h"

void muxShieldInputLineInit(int8_t lineId, const t_lineInputDescription *description) {
	prog_int8_t *params = (prog_int8_t *) pgm_read_word(&description->params);
	pinMode(pgm_read_byte(&params[0]), OUTPUT);
	pinMode(pgm_read_byte(&params[1]), OUTPUT);
	pinMode(pgm_read_byte(&params[2]), OUTPUT);
	pinMode(pgm_read_byte(&params[3]), OUTPUT);
}

void muxShieldOutputLineInit(int8_t lineId, const t_lineOutputDescription *description) {
	prog_int8_t *params = (prog_int8_t *) pgm_read_word(&description->params);
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
	pinMode(pgm_read_byte(&params[5]), OUTPUT);
	pinMode(pgm_read_byte(&params[6]), INPUT);
	pinMode(pgm_read_byte(&params[7]), INPUT);

	int i = pgm_read_byte(&params[0]) - lineId;

	digitalWrite(pgm_read_byte(&params[1]), (i&15)>>3); //S3
	digitalWrite(pgm_read_byte(&params[2]), (i&7)>>2);  //S2
	digitalWrite(pgm_read_byte(&params[3]), (i&3)>>1);  //S1
	digitalWrite(pgm_read_byte(&params[4]), (i&1));     //S0

	if (type == ANALOG) {
		analogWrite(pgm_read_byte(&params[5]), value); // not exist
	} else {
		digitalWrite(pgm_read_byte(&params[5]), value);
	}
}
