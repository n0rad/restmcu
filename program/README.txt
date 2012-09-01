
29736 <- with 1 input only before eeprom

# read fuse
avrdude -C /etc/avrdude.conf -c stk500v1 -p atmega328p -P /dev/ttyUSB0 -b 57600 -U hfuse:r:target/hfuse.hex:i -U lfuse:r:target/lfuse.hex:i -U efuse:r:target/efuse.hex:i


http://www.protostack.com/blog/2011/01/reading-and-writing-atmega168-eeprom/

upload bootloader
=================

avrdude -V -F -C /etc/avrdude.conf -p atmega328p -P /dev/ttyUSB0 -c stk500v2 -b 115200 -D -U flash:w:lib/arduino/hardware/arduino/bootloaders/atmega/ATmegaBOOT_168_atmega328.hex
