#include <Arduino.h>
#include <avr/eeprom.h>

#define CONFIG_BOARD_MAC_SIZE           6
#define CONFIG_BOARD_DESCRIPTION_SIZE   101
#define CONFIG_BOARD_KEY_SIZE            64
#define CONFIG_BOARD_IP_SIZE            4
#define CONFIG_BOARD_PORT_SIZE          sizeof(uint16_t)
#define CONFIG_BOARD_NOTIFY_SIZE        101
#define CONFIG_BOARD_NAME_SIZE          21


typedef struct s_boardSettings {
    uint8_t ip[CONFIG_BOARD_IP_SIZE];
    uint16_t port;
    char name[CONFIG_BOARD_NAME_SIZE];
    char notifyUrl[CONFIG_BOARD_NOTIFY_SIZE];
} t_boardSettings;


t_boardSettings boardSettings EEMEM = {
    {192, 168, 42, 30},          // ip
    80,                           // port
    "window1 controller",         // name
    "http://192.168.42.12:50852"  // notify url
};


int main() {
    init(); // load init of arduino

    delay(3000);
    Serial.begin(9600);

//    settingsLoad();


    Serial.println("ouda");

    char *tt = (char *) &boardSettings.name;
    char c;
    while ((c = eeprom_read_byte((uint8_t *) tt++))) {
        Serial.print(c);
    }
    Serial.println();

    Serial.println("ouda2");

    while (1) {
//        digitalWrite(led, HIGH);   // turn the LED on (HIGH is the voltage level)
//        delay(1000);               // wait for a second
//        digitalWrite(led, LOW);    // turn the LED off by making the voltage LOW
//        delay(1000);               // wait for a second


//        networkManage();
//        lineCheckChange();
    }

    return 0;
}
