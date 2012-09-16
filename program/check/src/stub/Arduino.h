

int analogRead(uint8_t);



uint16_t defaultLineRead(unsigned char, unsigned char);
void defaultLineWrite(unsigned char, unsigned char, unsigned short);

float noInputConversion(unsigned short);
int16_t noOutputConversion(float);

uint8_t pgm_read_byte(uint8_t);


typedef int int8_t __attribute__((__mode__(__QI__)));
typedef unsigned int uint8_t __attribute__((__mode__(__QI__)));
typedef int int16_t __attribute__ ((__mode__ (__HI__)));


