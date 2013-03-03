#include <restmcu-config.h>
#include <Arduino.h>
#include <string.h>


void fillHmacMessage(unsigned long time) {
	Sha1.print(time);
	Sha1.print("MESSAGE");
}


const t_boardDescription boardDescription PROGMEM = {
    {0x54, 0x55, 0x58, 0x10, 0x01, 0xF7},           // mac
    "window in front of the house not powered from POE but only by a transfo",   // description
    "TOTO42" // Hmac key
};
t_boardSettings boardSettings EEMEM = {
	{192, 168, 88, 20},          // ip
	80,                           // port
	"window1 controller",         // name
	"http://192.168.42.12:6786"  // notify url
};

const t_lineInputDescription lineInputDescription[] PROGMEM = {
        {14, ANALOG, 0, defaultInputLineInit, noInputConversion, defaultLineRead, "tmp36 temperature sensor"},
        {-1}
};
t_lineInputSettings lineInputSettings[] EEMEM = {
        {"living temperature", {{0, 500},{0, 100},{0,300},{0,200}}},
};

//////////////

const t_lineOutputDescription lineOutputDescription[] PROGMEM = {
        {3, DIGITAL, 0, 1, defaultOutputLineInit, noOutputConversion, defaultLineWrite, "red led"},
        {-1}
};
t_lineOutputSettings lineOutputSettings[] EEMEM = {
    {"red", 1},
};
