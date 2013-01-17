RestMcu is a generic program for micro-controllers (arduino only for the moment) with a network interface.
It provide through a restfull (json only) interface :

- static board configuration (description, list of lines, ...)
- dynamic board configuration (ip, port, line notify url)
- static line configuration (based on electronic line wiring) : analog/digital, input,output, ...
- dynamic line name and notification conditions 
- notify a server of line change based on condition set on lines
- support of hmac-sha256 security between board and server
- keep states of lines in eeprom is case of power failure

The electronic wiring configuration is provided by a simple C file used during compilation

Feature that can be enabled
===========================

You can enable some feature at compilation time :
 
- HMAC : enable security using hmac-sha1 signature on calls
- DEBUG : enable debug through serial

Build dependencies
==================

Building the Java part 
---------------------

To build the JAVA part you will need maven 3

Building the C part
---------------------

On Linux : # sudo apt-get install avr-libc binutils-avr gcc-avr avrdude

On Mac OS X : 
* Install the command line tools from XCode (menu > preferences > downloads)

* Get AVR from http://www.obdev.at/products/crosspack/download.html (will take 200 Mb on your hard drive)

* Export the right paths for the makefile (this will get an update) : 

```
export AVRPACK_HOME=/usr/local/CrossPack-AVR

export AVR_TOOLS_PATH=$AVRPACK_HOME/bin/

export AVR_LIB_PATH=$AVRPACK_HOME/avr

export AVRDUDE_PATH=$AVRPACK_HOME/bin/

export AVRDUDE_CONF=$AVRPACK_HOME/etc/avrdude.conf
```

Use the program on your board
=================

Go to the 'program' folder

Create a new configuration file based on config.cpp file

# make MCU=atmega1280 CONFIG=YOUR_BOARD_CONFIGURATION_FILE.cpp clean upload

with hmac security :

# make MCU=atmega1280 CONFIG=YOUR_BOARD_CONFIGURATION_FILE.cpp FEATURE=-DHMAC clean upload

Code verification with board
============================

The IT project can test the program directly on a board. The wiring of the board can be found in the 'it/wiring/' folder

to test it yourself :

- wire a board like the schema 'it/wiring/TestBoardWiring.fzz' (open it with http://fritzing.org/) 
- plug the board to the network (for tests) and the usb (to program the board)
- run the command and replace the type of board AND the ip address with a free one in your network :
   # MCU=atmega1280 mvn -Dboard.host=192.168.42.244 -Dboard.port=80 -Dboard.path=/ -Pboard clean verify

Resources 
=========

on board:

- GET /                      : read static board configuration
- GET /settings              : read dynamic board settings 
- PUT /settings              : update dynamic board settings
- GET /line/{lineId}           : get static line configuration
- GET /line/{lineId}/settings  : get dynamic line settings
- PUT /line/{lineId}/settings  : update dynamic line settings

on server:

- GET /time                  : get posix timestamp from server (used in hmac-sha1)
- PUT /board                 : notification of board (boot and test)
- PUT /line                   : notification of line change based on line settings


TODO
====
- keep-alive
- support of encj28j60 (currently half-implemented)
- dhcp
- dns ?
- fit in 328p (almost done)
- constrain between pins
- reduce memory by removing pullup info in payload ?
