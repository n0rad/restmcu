#ifndef CONFIG_MANAGER_H
#define CONFIG_MANAGER_H

#include <avr/pgmspace.h>

const int8_t configGetInputLineIdx(uint8_t lineIdToFind);
const int8_t configGetOutputLineIdx(uint8_t lineIdToFind);

char *buildGlobalError_P(const char PROGMEM *progmem_s, int line);

char *configCheck();



#endif
