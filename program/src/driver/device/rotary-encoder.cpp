#include "rotary-encoder.h"

//Encoder myEnc  = Encoder();

Encoder myEnc(0, 1);

//Encoder *myEnc;
//myEnc = new Encoder(pgm_read_byte(&params[0]), pgm_read_byte(&params[1]));
//myEnc = Encoder();

//long oldPosition  = -999;

long positionLeft  = -999;
long positionRight = -999;

void rotaryEncoderLineInit(int8_t lineId, const t_lineInputDescription *description) {
	int8_t PROGMEM *params = (int8_t PROGMEM *) pgm_read_word(&description->params);
}

uint16_t rotaryEncoderLineRead(uint8_t lineId, uint8_t type, int8_t PROGMEM params[]) {
    long newLeft, newRight;
     newLeft = myEnc.read();
     newRight = myEnc.read();
     if (newLeft != positionLeft || newRight != positionRight) {
       Serial.print("Left = ");
       Serial.print(newLeft);
       Serial.print(", Right = ");
       Serial.print(newRight);
       Serial.println();
       positionLeft = newLeft;
       positionRight = newRight;
     }
     return positionLeft;

//	long newPosition = myEnc.read();
//	//Serial.println(newPosition);
//	if (newPosition != oldPosition) {
//		oldPosition = newPosition;
//		Serial.println(newPosition);
//	}
//	return myEnc.read();
//	long newPosition = myEnc->read();
//	if (newPosition != oldPosition) {
//		oldPosition = newPosition;
//		Serial.println(newPosition);
//	}
}
