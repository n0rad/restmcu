30710

32232
# read fuse
avrdude -C /etc/avrdude.conf -c stk500v1 -p atmega328p -P /dev/ttyUSB0 -b 57600 -U hfuse:r:target/hfuse.hex:i -U lfuse:r:target/lfuse.hex:i -U efuse:r:target/efuse.hex:i


http://www.protostack.com/blog/2011/01/reading-and-writing-atmega168-eeprom/

