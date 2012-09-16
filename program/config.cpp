#include <restmcu-config.h>
#include <Arduino.h>
#include <string.h>

float tmp36Conversion(uint16_t value) {
	float voltage = value * 5.0; // 5V as aref
	voltage /= 1024.0;
	return (voltage - 0.5) * 100;
}
uint16_t sepcdefaultLineRead(uint8_t lineId, uint8_t type) {
	return analogRead(0);
}
uint16_t lumdefaultLineRead(uint8_t lineId, uint8_t type) {
	return analogRead(1);
}

void fillHmacMessage(unsigned long time) {
	Sha1.print(time);
	Sha1.print("MESSAGE");
}


const t_boardDescription boardDescription PROGMEM = {
    {0x54, 0x55, 0x58, 0x10, 0x00, 0xF5},           // mac
    "window in front of the house not powered from POE but only by a transfo",   // description
    "TOTO42" // Hmac key
};
t_boardSettings boardSettings EEMEM = {
	{192, 168, 42, 30},          // ip
	80,                           // port
	"window1 controller",         // name
	"http://192.168.42.213:5879"  // notify url
};


const t_lineInputDescription lineInputDescription[] PROGMEM = {
        {21, DIGITAL, 0, noInputConversion, defaultLineRead, "a simple PIR"},
        {8, DIGITAL, 0, noInputConversion, defaultLineRead, "lm35 temperature captor"},
        {54, DIGITAL, 0, noInputConversion, defaultLineRead, "input from 9"},
        {-1}
};
t_lineInputSettings lineInputSettings[] EEMEM = {
	{"PIR", {{OVER_EQ, 0},{UNDER_EQ, 0},{0,0},{0,0}}},
	{"Push button", {{OVER_EQ, 0},{UNDER_EQ, 0},{0,0},{0,0}}},
	{"input9", {{0, 0},{0, 0},{0,0},{0,0}}}
};

//////////////

const t_lineOutputDescription lineOutputDescription[] PROGMEM = {
        {6, ANALOG, 0, 255, noOutputConversion, defaultLineWrite, "optocoupler isolated and triac / no zero detection"},
        {7, DIGITAL, 0, 1, noOutputConversion, defaultLineWrite, "optocoupler isolated and triac / no zero detection"},
		{9, DIGITAL, 0, 1, noOutputConversion, defaultLineWrite, "control of analog 0"},
        {-1}
};
t_lineOutputSettings lineOutputSettings[] EEMEM = {
	{"variator for light 1", 10},
	{"variator for light 1", 0},
	{"output9", 0}
};
