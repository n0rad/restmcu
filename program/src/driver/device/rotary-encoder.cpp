#include "rotary-encoder.h"

Encoder *myEnc;

long oldPosition  = -999;

void rotaryEncoderLineInit(int8_t lineId, const t_lineInputDescription *description) {
	int8_t PROGMEM *params = (int8_t PROGMEM *) pgm_read_word(&description->params);
//	myEnc = new Encoder(pgm_read_byte(&params[0]), pgm_read_byte(&params[1]));


//	myEnc = Encoder();
}

uint16_t rotaryEncoderLineRead(uint8_t lineId, uint8_t type, int8_t PROGMEM params[]) {
	long newPosition = myEnc->read();
	Serial.println(newPosition);
	if (newPosition != oldPosition) {
		oldPosition = newPosition;
	//	Serial.println(newPosition);
	}
	return newPosition;
//	long newPosition = myEnc->read();
//	if (newPosition != oldPosition) {
//		oldPosition = newPosition;
//		Serial.println(newPosition);
//	}
}
