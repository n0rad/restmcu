#ifndef BUFFER_H
#define BUFFER_H

#include <avr/pgmspace.h>
#include <avr/eeprom.h>
#include <stdio.h>

#include "../restmcu.h"

uint16_t addToBufferTCP(char *buf, uint16_t pos, float val);
uint16_t addToBufferTCPHex(char *buf, uint16_t pos, uint8_t *val, uint8_t len);
uint16_t addToBufferTCP(char *buf, uint16_t pos, uint16_t val);
uint16_t addToBufferTCP(char *buf, uint16_t pos, char val);
uint16_t addToBufferTCP(char *buf, uint16_t pos, char *mem_s);
uint16_t addToBufferTCP_P(char *buf, uint16_t pos, const prog_char *progmem_s);
uint16_t addToBufferTCP_E(char *buf, uint16_t pos, eeprom_char *eeprom_s);

#endif
