RestMcu is a generic program for micro-controllers (arduino only for the moment) with a network interface.
It provide through a restfull (json only) interface :

- static board configuration (description, list of pins, ...)
- dynamic board configuration (ip, port, pin notify url)
- static pin configuration (based on electronic pin wiring) : analog/digital, input,output, ...
- dynamic pin name and notification conditions 
- notify a server of pin change based on condition set on pins
- support of hmac-sha256 security between board and server
- keep states of pins in eeprom is case of power failure

The electronic wiring configuration is provided by a simple C file used during compilation

Feature that can be enabled
===========================

You can enable some feature at compilation time :
 
- HMAC : enable security using hmac-sha1 signature on calls
- DEBUG : enable debug through serial

Build dependencies
==================

To build the JAVA part you will need maven 3

To build the C part : # sudo apt-get install avr-libc binutils-avr gcc-avr avrdude

Use the program on your board
=================

Go to 'program' folder

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
- GET /pin/{pinId}           : get static pin configuration
- GET /pin/{pinId}/settings  : get dynamic pin settings
- PUT /pin/{pinId}/settings  : update dynamic pin settings

on server:

- GET /time                  : get posix timestamp from server (used in hmac-sha256)
- PUT /board                 : notification of board (boot and test)
- PUT /pin                   : notification of pin change based on pin settings


TODO
====
- keep-alive
- support of encj28j60 (currently half-implemented)
- dhcp
- dns ?
- fit in 328p (almost done)
