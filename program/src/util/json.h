#ifndef JSON_H
#define JSON_H

#include <stdint.h>
#include <avr/pgmspace.h>

#include "../restmcu.h"
#include "mylibc.h"


const char JSON_ERROR_NO_ARRAY_END[] PROGMEM = "Cannot find array end";
const char JSON_ERROR_NO_OBJECT_END[] PROGMEM = "Cannot find object end";
const char JSON_ERROR_NO_KEY_START[] PROGMEM = "Cannot find key start";
const char JSON_ERROR_NO_VALUE_END[] PROGMEM = "Cannot find value end";
const char JSON_ERROR_NO_SEPARATOR[] PROGMEM = "Cannot find key-value separator";

// notifies : [{notifyValue: 42.4, notifyCondition : "sup_or_equal"}, {notifyValue.3, notifyCondition : "inf_or_equal"}]

typedef const char PROGMEM *(*jsonHandleValue)(char *val, uint16_t valLen, uint8_t index);
typedef const char PROGMEM *(*jsonHandleEndArray)(uint8_t index);

typedef struct s_json {
    const char PROGMEM *key;
    jsonHandleValue handleValue;           // handle a value
    s_json *valueStruct;                   // sub obj
    jsonHandleEndArray handleEndArray;     // is an array and handle the end
} t_json;

const char PROGMEM  *jsonParse(char *buf, const t_json *structure);

#endif
