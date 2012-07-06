#include <stdint.h>

int analogRead(uint8_t pin) {
	return 0;
}


float noInputConversion(uint16_t pinValue) {
    return pinValue;
}
int16_t noOutputConversion(float pinValue) {
    return pinValue;
}


uint16_t defaultPinRead(uint8_t pinId, uint8_t type) {
	return 0;
}
void defaultPinWrite(uint8_t pinId, uint8_t type, uint16_t value) {}


uint8_t pgm_read_byte(int8_t *t) {
	return *t;
}
