#include <restmcu-config.h>
#include <Arduino.h>

float tmp36Conversion(uint16_t value) {
	float voltage = value * 5.0; // 5V as aref
	voltage /= 1024.0;
	return (voltage - 0.5) * 100;
}
uint16_t sepcdefaultPinRead(uint8_t pinId, uint8_t type) {
	return analogRead(0);
}
uint16_t lumdefaultPinRead(uint8_t pinId, uint8_t type) {
	return analogRead(1);
}


const t_boardDescription boardDescription PROGMEM = {
    {0x54, 0x55, 0x58, 0x10, 0x00, 0xF5},           // mac
    "window in front of the house not powered from POE but only by a transfo"   // description
};
t_boardSettings boardSettings EEMEM = {
	{192, 168, 32, 30},          // ip
	80,                           // port
	"window1 controller",         // name
	"http://192.168.42.210:8080"  // notify url
};


const t_pinInputDescription pinInputDescription[] PROGMEM = {
        {21, DIGITAL, 0, noInputConversion, defaultPinRead, "a simple PIR"},
        {8, DIGITAL, 0, noInputConversion, defaultPinRead, "lm35 temperature captor"},
        {54, DIGITAL, 0, noInputConversion, defaultPinRead, "input from 9"},
        {-1}
};
t_pinInputSettings pinInputSettings[] EEMEM = {
	{"PIR", {{OVER_EQ, 0},{UNDER_EQ, 0},{0,0},{0,0}}},
	{"Push button", {{OVER_EQ, 0},{UNDER_EQ, 0},{0,0},{0,0}}},
	{"input9", {{0, 0},{0, 0},{0,0},{0,0}}}
};

//////////////

const t_pinOutputDescription pinOutputDescription[] PROGMEM = {
        {6, ANALOG, 0, 255, noOutputConversion, defaultPinWrite, "optocoupler isolated and triac / no zero detection"},
        {7, DIGITAL, 0, 1, noOutputConversion, defaultPinWrite, "optocoupler isolated and triac / no zero detection"},
		{9, DIGITAL, 0, 1, noOutputConversion, defaultPinWrite, "control of analog 0"},
        {-1}
};
t_pinOutputSettings pinOutputSettings[] EEMEM = {
	{"variator for light 1", 10},
	{"variator for light 1", 0},
	{"output9", 0}
};
