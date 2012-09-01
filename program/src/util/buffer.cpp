#include "buffer.h"

static char value_to_add[16] =
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
static char hexArray[] = "0123456789abcdef";

uint16_t addToBufferTCPHex32(char *buf, uint16_t pos, uint8_t *hash) {
	for (int i = 0; i < 32; i++) {
//	buf[pos++] = "0123456789abcdef"[hash[i]>>4];
//	buf[pos++] = "0123456789abcdef"[hash[i]&0xf];
		buf[pos++] = hexArray[hash[i] >> 4];
		buf[pos++] = hexArray[hash[i] & 0xf];
	}
	return pos;
}

uint16_t addToBufferTCPHex(char *buf, uint16_t pos, uint16_t val) {
	int j = 0;

	sprintf_P(value_to_add, sprintfpHEX, val);
	while (value_to_add[j]) {
		buf[pos] = value_to_add[j++];
		pos++;
	}
	return pos;
}

uint16_t addToBufferTCP(char *buf, uint16_t pos, float number) {
	uint8_t digits = 2;

	// Handle negative numbers
	if (number < 0.0) {
		buf[pos++] = '-';
		number = -number;
	}

	// Round correctly so that print(1.999, 2) prints as "2.00"
	double rounding = 0.5;
	for (uint8_t i = 0; i < digits; ++i)
		rounding /= 10.0;

	number += rounding;

	// Extract the integer part of the number and print it
	unsigned long int_part = (unsigned long) number;
	double remainder = number - (double) int_part;
	pos = addToBufferTCP(buf, pos, (uint16_t) int_part);

	// Print the decimal point, but only if there are digits beyond
	if (digits > 0) {
		buf[pos++] = '.';
	}

	// Extract digits from the remainder one at a time
	while (digits-- > 0) {
		remainder *= 10.0;
		int toPrint = int(remainder);
		pos = addToBufferTCP(buf, pos, (uint16_t) toPrint);
		remainder -= toPrint;
	}

	return pos;
}

uint16_t addToBufferTCP(char *buf, uint16_t pos, char val) {
	buf[pos++] = val;
	return pos;
}

uint16_t addToBufferTCP(char *buf, uint16_t pos, uint16_t val) {
	int j = 0;
	sprintf_P(value_to_add, sprintfpDEC, val);
	while (value_to_add[j]) {
		buf[pos] = value_to_add[j++];
		pos++;
	}
	return pos;
}

uint16_t addToBufferTCP(char *buf, uint16_t pos, char *mem_s) {
	char c;
	while ((c = *(mem_s++))) {
		buf[pos] = c;
		pos++;
	}
	return pos;
}

uint16_t addToBufferTCP_P(char *buf, uint16_t pos, const prog_char *progmem) {
	unsigned char c;
	while ((c = pgm_read_byte(progmem++))) {
		buf[pos] = c;
		pos++;
	}
	return pos;
}

uint16_t addToBufferTCP_E(char *buf, uint16_t pos, uint8_t *eeprom_s) {
	char c;
	while ((c = eeprom_read_byte(eeprom_s++))) {
		buf[pos] = c;
		pos++;
	}
	return pos;
}
