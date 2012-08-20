
RestMcu is a generic program for micro-controllers (arduino only for the moment) with a network interface.
It provide through a restfull interface :
- read board configuration
- update board configuration
- get pin configuration
- update pin configuration

The electronic wiring configuration is provided by a simple C file used during compilation


Build dependencies
==================

To build the JAVA part you will need maven 3
To build the C part : # sudo apt-get install avr-libc binutils-avr gcc-avr avrdude


Build the project
=================

#


Code verification with board
============================

The IT project can interactively test the program directly on a board
The wiring of the board can be found in the 'it/wiring/' folder
to test it yourself :
- wire a board like the schema 'it/wiring/TestBoardWiring.fzz' (open it with http://fritzing.org/) 
- plug the board to the network (for tests) and the usb (to program the board)
- run the command and replace the it with a free in your network and type of board :
# MCU=atmega1280 mvn -Dboard.host=192.168.42.244 -Dboard.port=80 -Dboard.path=/ -Pboard clean verify
- follow the interactive instructions to test the program


