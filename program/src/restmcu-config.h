#ifndef CONFIG_H_
#define CONFIG_H_

typedef char eeprom_char;


#include <avr/eeprom.h>
#include <avr/pgmspace.h>
#include <sha1.h>
#include "line/line-manager.h"

#include "driver/device/mux-shield.h"
#include "driver/device/rotary-encoder.h"

typedef struct s_lineInputDescription t_lineInputDescription;
typedef struct s_lineOutputDescription t_lineOutputDescription;


typedef uint16_t (*LineRead)(uint8_t lineId, uint8_t type, int8_t PROGMEM *params);
typedef void (*LineWrite)(uint8_t lineId, uint8_t type, uint16_t value, int8_t PROGMEM *params);
typedef void (*LineInputInit)(int8_t lineId, const t_lineInputDescription *description);
typedef void (*LineOutputInit)(int8_t lineId, const t_lineOutputDescription *description);


void fillHmacMessage(unsigned long posixTime);


///////////////////////////////////////////////////////////////
// BOARD
///////////////////////////////////////////////////////////////
#define CONFIG_BOARD_MAC_SIZE           6
#define CONFIG_BOARD_DESCRIPTION_SIZE   101
#define CONFIG_BOARD_KEY_SIZE			 64
#define CONFIG_BOARD_IP_SIZE            4
#define CONFIG_BOARD_PORT_SIZE          sizeof(uint16_t)
#define CONFIG_BOARD_NOTIFY_SIZE        101
#define CONFIG_BOARD_NAME_SIZE          21

typedef struct s_boardDescription {
    uint8_t mac[CONFIG_BOARD_MAC_SIZE];
    char PROGMEM description[CONFIG_BOARD_DESCRIPTION_SIZE];
    char PROGMEM hmacKey[CONFIG_BOARD_KEY_SIZE];
} t_boardDescription;
typedef struct s_boardSettings {
    uint8_t ip[CONFIG_BOARD_IP_SIZE];
    uint16_t port;
    eeprom_char name[CONFIG_BOARD_NAME_SIZE];
    eeprom_char notifyUrl[CONFIG_BOARD_NOTIFY_SIZE];
} t_boardSettings;


////////////////////////////////////////////////////////////
// LINE
///////////////////////////////////////////////////////////////
#define ANALOG 1
#define DIGITAL 2

#define OVER_EQ 1
#define UNDER_EQ 2

#define LINE_NUMBER_OF_NOTIFY 4

#define CONFIG_LINE_NAME_SIZE 21
#define CONFIG_LINE_DESCRIPTION_SIZE 101

typedef struct s_notify {
    uint8_t condition;
    float value; // value after conversion (this is why its a float)
} t_notify;

typedef struct s_lineInputDescription {
    int8_t lineId;           // unique line id on board
    int8_t type;            // ANALOG, DIGITAL
    uint8_t pullup;         // enable internal pullup resistor
    LineInputInit init;			// function to init line
    LineInputConversion convertValue; // convert the 0-1023 to a display value (ex: float for temperature)
    LineRead read;           // function to read value
    char PROGMEM description[CONFIG_LINE_DESCRIPTION_SIZE];
    int8_t PROGMEM *params;
} t_lineInputDescription;
typedef struct s_lineInputSettings {
	eeprom_char name[CONFIG_LINE_NAME_SIZE];
    t_notify notifies[LINE_NUMBER_OF_NOTIFY];
} t_lineInputSettings;

typedef struct s_lineOutputDescription {
    int8_t lineId;         // unique line id on board
    int8_t type;          // ANALOG, DIGITAL
    float valueMin;       // for output line : min value as input for transform function that will not result under 0 (display value)
    float valueMax;       // for output line : max value as input for transform function that will not result over 255 (display value)
    LineOutputInit init;			// function to init line
    LineOutputConversion convertValue; // convert the display value to a 0-255 value
    LineWrite write;       // function to write value
    char PROGMEM description[CONFIG_LINE_DESCRIPTION_SIZE];
    int8_t PROGMEM *params;
} t_lineOutputDescription;
typedef struct s_lineOutputSettings {
	eeprom_char name[CONFIG_LINE_NAME_SIZE];
    float lastValue;
} t_lineOutputSettings;

extern const t_lineInputDescription lineInputDescription[];
extern const t_lineOutputDescription lineOutputDescription[];
extern const t_boardDescription boardDescription;

extern t_lineInputSettings lineInputSettings[];
extern t_lineOutputSettings lineOutputSettings[];
extern t_boardSettings boardSettings;

#endif
