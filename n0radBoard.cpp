#include <restmcu-config.h>
#include <Arduino.h>
#include <string.h>

float tmp36CelciusConversion(uint16_t value) {
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
	{192, 168, 32, 244},          // ip
	80,                           // port
	"window1 controller",         // name
	"http://192.168.42.12:6786"  // notify url
};

const int8_t PROGMEM muxFirstParams[] PROGMEM = {21, 2, 3, 4, 5, 54, 55, 56}; //
const int8_t PROGMEM muxSecondParams[] PROGMEM = {21, 2, 3, 4, 5, 55, 54, 56};
const int8_t PROGMEM muxThirdParams[] PROGMEM = {21, 2, 3, 4, 5, 56, 54, 55};

const int8_t PROGMEM rotary[] PROGMEM = {32, 33};


uint16_t debouncedIntegrator[] = {3, 3, 3};
uint16_t debouncedValue[] = {1, 1, 1};

const int8_t PROGMEM debounced0[] PROGMEM = {0};
const int8_t PROGMEM debounced1[] PROGMEM = {1};
const int8_t PROGMEM debounced2[] PROGMEM = {2};

const t_lineInputDescription lineInputDescription[] PROGMEM = {
        {24, DIGITAL, 1, defaultInputLineInit, digitalReversedConversion, debouncedLineRead, "red push button with internal pullup", debounced0},
        {25, DIGITAL, 1, defaultInputLineInit, digitalReversedConversion, debouncedLineRead, "black push button with internal pullup", debounced1},
        {23, DIGITAL, 1, defaultInputLineInit, digitalReversedConversion, debouncedLineRead, "rotary push button with internal pullup", debounced2},
        {57, ANALOG, 1, defaultInputLineInit, noInputConversion, defaultLineRead, "sample photoresistor on pullup"},
        {58, ANALOG, 0, defaultInputLineInit, tmp36CelciusConversion, defaultLineRead, "tmp36 temperature sensor"},
        {18, DIGITAL, 0, defaultInputLineInit, noInputConversion, defaultLineRead, "pir motion sensor with pullup"},
        {100, DIGITAL, 0, rotaryEncoderLineInit, noInputConversion, rotaryEncoderLineRead, "rotary encoder", rotary},
//        {21, DIGITAL, 0, muxShieldInputLineInit, noInputConversion, muxShieldLineRead, "a simple PIR", muxFirstParams},
        {-1}
};
t_lineInputSettings lineInputSettings[] EEMEM = {
    {"living button", {{OVER_EQ, 0},{UNDER_EQ, 0},{OVER_EQ,1},{UNDER_EQ,1}}},
    {"bedroom button", {{OVER_EQ, 0},{UNDER_EQ, 0},{OVER_EQ,1},{UNDER_EQ,1}}},
    {"soft light button", {{OVER_EQ, 0},{UNDER_EQ, 0},{OVER_EQ,1},{UNDER_EQ,1}}},
    {"living light", {{OVER_EQ, 0},{UNDER_EQ, 0},{OVER_EQ,0},{UNDER_EQ,0}}},
    {"living temperature", {{0, 500},{0, 100},{0,300},{0,200}}},
    {"entrance sensor", {{OVER_EQ, 0},{UNDER_EQ, 0},{OVER_EQ,1},{UNDER_EQ,1}}},
	{"soft light rotary", {{0, 0},{0, 0},{0,0},{0,0}}},
//	{"PIR", {{OVER_EQ, 0},{UNDER_EQ, 0},{0,0},{0,0}}},

};

//////////////

const t_lineOutputDescription lineOutputDescription[] PROGMEM = {
        {8, ANALOG, 0, 255, defaultOutputLineInit, noOutputConversion, defaultLineWrite, "led 3 color red"},
        {6, ANALOG, 0, 255, defaultOutputLineInit, noOutputConversion, defaultLineWrite, "led 3 color green"},
        {7, ANALOG, 0, 255, defaultOutputLineInit, noOutputConversion, defaultLineWrite, "led 3 color blue"},
        {21, DIGITAL, 0, 1, muxShieldOutputLineInit, noOutputConversion, muxShieldLineWrite, "opto", muxFirstParams},
        {37, DIGITAL, 0, 1, muxShieldOutputLineInit, noOutputConversion, muxShieldLineWrite, "opto", muxSecondParams},
        {6, ANALOG, 0, 255, defaultOutputLineInit, noOutputConversion, defaultLineWrite, "optocoupler isolated and triac / no zero detection"},
        {7, DIGITAL, 0, 1, defaultOutputLineInit, noOutputConversion, defaultLineWrite, "optocoupler isolated and triac / no zero detection"},
		{9, DIGITAL, 0, 1, defaultOutputLineInit, noOutputConversion, defaultLineWrite, "control of analog 0"},
        {42, DIGITAL, 0, 1, defaultOutputLineInit, noOutputConversion, defaultLineWrite, "red"},
        {43, DIGITAL, 0, 1, defaultOutputLineInit, noOutputConversion, defaultLineWrite, "green"},
        {44, DIGITAL, 0, 1, defaultOutputLineInit, noOutputConversion, defaultLineWrite, "yellow"},
        {45, DIGITAL, 0, 1, defaultOutputLineInit, noOutputConversion, defaultLineWrite, "blue"},
        {-1}
};
t_lineOutputSettings lineOutputSettings[] EEMEM = {
    {"red", 10},
    {"green", 10},
    {"blue", 10},
	{"light test", 1},
	{"light test", 1},
	{"variator for light 1", 10},
	{"variator for light 1", 0},
	{"output9", 0},
    {"red", 1},
    {"green", 1},
    {"yellow", 1},
    {"blue", 1},
};
