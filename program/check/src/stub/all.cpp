#include <stdint.h>

int analogRead(uint8_t line) {
	return 0;
}


float noInputConversion(uint16_t lineValue) {
    return lineValue;
}
int16_t noOutputConversion(float lineValue) {
    return lineValue;
}


uint16_t defaultLineRead(uint8_t lineId, uint8_t type) {
	return 0;
}
void defaultLineWrite(uint8_t lineId, uint8_t type, uint16_t value) {}


uint8_t pgm_read_byte(int8_t *t) {
	return *t;
}
