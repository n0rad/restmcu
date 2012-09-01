#ifndef CONFIG_H_
#define CONFIG_H_

typedef char eeprom_char;

#include <avr/eeprom.h>
#include <avr/pgmspace.h>
#include <sha256.h>
#include "pin/pin-manager.h"

void fillHmacMessage();

///////////////////////////////////////////////////////////////
// BOARD
///////////////////////////////////////////////////////////////
#define CONFIG_BOARD_MAC_SIZE           6
#define CONFIG_BOARD_DESCRIPTION_SIZE   101
#define CONFIG_BOARD_KEY_SIZE			 64
#define CONFIG_BOARD_IP_SIZE            4
#define CONFIG_BOARD_PORT_SIZE          sizeof(uint16_t)
#define CONFIG_BOARD_NOTIFY_SIZE        51
#define CONFIG_BOARD_NAME_SIZE          21

typedef struct s_boardDescription {
    uint8_t mac[CONFIG_BOARD_MAC_SIZE];
    prog_char description[CONFIG_BOARD_DESCRIPTION_SIZE];
    prog_char hmacKey[CONFIG_BOARD_KEY_SIZE];
} t_boardDescription;
typedef struct s_boardSettings {
    uint8_t ip[CONFIG_BOARD_IP_SIZE];
    uint16_t port;
    eeprom_char name[CONFIG_BOARD_NAME_SIZE];
    eeprom_char notifyUrl[CONFIG_BOARD_NOTIFY_SIZE];
} t_boardSettings;


////////////////////////////////////////////////////////////
// PIN
///////////////////////////////////////////////////////////////
#define ANALOG 1
#define DIGITAL 2

#define OVER_EQ 1
#define UNDER_EQ 2

#define PIN_NUMBER_OF_NOTIFY 4

#define CONFIG_PIN_NAME_SIZE 21
#define CONFIG_PIN_DESCRIPTION_SIZE 101

typedef struct s_notify {
    uint8_t condition;
    float value; // value after conversion (this is why its a float)
} t_notify;

typedef struct s_pinInputDescription {
    int8_t pinId;           // unique pin id on board
    int8_t type;            // ANALOG, DIGITAL
    uint8_t pullup;         // enable internal pullup resistor
    PinInputConversion convertValue; // convert the 0-1023 to a display value (ex: float for temperature)
    PinRead read;           // function to read value
    prog_char description[CONFIG_PIN_DESCRIPTION_SIZE];
} t_pinInputDescription;
typedef struct s_pinInputSettings {
	eeprom_char name[CONFIG_PIN_NAME_SIZE];
    t_notify notifies[PIN_NUMBER_OF_NOTIFY];
} t_pinInputSettings;

typedef struct s_pinOutputDescription {
    int8_t pinId;         // unique pin id on board
    int8_t type;          // ANALOG, DIGITAL
    float valueMin;       // for output pin : min value as input for transform function that will not result under 0 (display value)
    float valueMax;       // for output pin : max value as input for transform function that will not result over 255 (display value)
    PinOutputConversion convertValue; // convert the display value to a 0-255 value
    PinWrite write;       // function to write value
    prog_char description[CONFIG_PIN_DESCRIPTION_SIZE];
} t_pinOutputDescription;
typedef struct s_pinOutputSettings {
	eeprom_char name[CONFIG_PIN_NAME_SIZE];
    float lastValue;
} t_pinOutputSettings;

extern const t_pinInputDescription pinInputDescription[];
extern const t_pinOutputDescription pinOutputDescription[];
extern const t_boardDescription boardDescription;

extern t_pinInputSettings pinInputSettings[];
extern t_pinOutputSettings pinOutputSettings[];
extern t_boardSettings boardSettings;

#endif
