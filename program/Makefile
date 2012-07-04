

TARGET = target/restmcu
# Change this to match your arduino installation directory
PORT = /dev/ttyUSB2
#UPLOAD_RATE = 115200
#AVRDUDE_PROGRAMMER = stk500v2
#MCU = atmega328p
F_CPU = 16000000


UPLOAD_RATE = 57600
AVRDUDE_PROGRAMMER = stk500v1

###################################################
#ARDUINO
###################################################
VERSION=101
ARDUINO = lib/arduino/hardware/arduino/cores/arduino
ARDUINO_LIB = lib/arduino/libraries
VARIANTS_DIR = lib/arduino/hardware/arduino/variants
VARIANTS=$(VARIANTS_DIR)/standard

ifeq ($(MCU),atmega2560)
	VARIANTS=$(VARIANTS_DIR)/mega
endif

ifeq ($(MCU),atmega1280)
	VARIANTS=$(VARIANTS_DIR)/mega
endif
C_MODULES =  \
$(ARDUINO)/wiring_pulse.c \
$(ARDUINO)/wiring_analog.c \
$(ARDUINO)/wiring.c \
$(ARDUINO)/wiring_digital.c \
$(ARDUINO)/WInterrupts.c \
$(ARDUINO)/wiring_shift.c \

CXX_MODULES = \
$(ARDUINO)/Tone.cpp \
$(ARDUINO)/WMath.cpp \
$(ARDUINO)/Print.cpp \
$(ARDUINO)/HardwareSerial.cpp \
$(ARDUINO)/CDC.cpp \
$(ARDUINO)/HID.cpp \
$(ARDUINO)/IPAddress.cpp \
$(ARDUINO)/new.cpp \
$(ARDUINO)/Stream.cpp \
$(ARDUINO)/USBCore.cpp \
$(ARDUINO)/WMath.cpp \
$(ARDUINO)/WString.cpp \
$(ARDUINO_LIB)/EEPROM/EEPROM.cpp \
$(ARDUINO_LIB)/SPI/SPI.cpp \
$(ARDUINO_LIB)/Ethernet/Ethernet.cpp \
$(ARDUINO_LIB)/Ethernet/EthernetClient.cpp \
$(ARDUINO_LIB)/Ethernet/EthernetServer.cpp \
$(ARDUINO_LIB)/Ethernet/Dhcp.cpp \
$(ARDUINO_LIB)/Ethernet/EthernetUdp.cpp \
$(ARDUINO_LIB)/Ethernet/Dns.cpp \
$(ARDUINO_LIB)/Ethernet/utility/socket.cpp \
$(ARDUINO_LIB)/Ethernet/utility/w5100.cpp \

##################################################
# RESTMCU
##################################################

CONFIG = config.cpp

C_APP = 
CXX_APP = src/restmcu.cpp $(CONFIG) src/settings/settings.cpp \
		src/settings/settings-board.cpp src/settings/settings-pin.cpp \
		src/server/server.cpp src/pin/pin-manager.cpp src/config/config-manager.cpp \
		src/server/server-pin.cpp src/server/server-board.cpp src/client/client.cpp \
		src/util/json.cpp src/util/mylibc.cpp src/util/buffer.cpp \
		src/util/availableMemory.cpp

CXX_APP += src/driver/hardware/board-mega.cpp src/driver/hardware/network-w5100.cpp


##################################################

#AVR_TOOLS_PATH = /usr/bin/
#AVRDUDE_PATH = $(INSTALL_DIR)/hardware/tools/



MODULES = $(C_MODULES) $(CXX_MODULES)
SRC = $(C_MODULES) $(C_APP)
CXXSRC = $(CXX_MODULES) $(CXX_APP)
FORMAT = ihex

# Name of this Makefile (used for "make depend").
MAKEFILE = Makefile

# Debugging format.
# Native formats for AVR-GCC's -g are stabs [default], or dwarf-2.
# AVR (extended) COFF requires stabs, plus an avr-objcopy run.
#DEBUG = stabs
DEBUG =

OPT = s

# Place -D or -U options here
CDEFS = -DF_CPU=$(F_CPU)L -DARDUINO=$(VERSION)
CXXDEFS = -DF_CPU=$(F_CPU)L -DARDUINO=$(VERSION)

# Place -I options here
CINCS = -I$(ARDUINO)  -I$(VARIANTS) -I$(ARDUINO_LIB) -I$(ARDUINO_LIB)/SPI \
	-I$(ARDUINO_LIB)/Ethernet -I$(ARDUINO_LIB)/Ethernet/utility -Isrc
CXXINCS = $(CINCS)

# Compiler flag to set the C Standard level.
# c89   - "ANSI" C
# gnu89 - c89 plus GCC extensions
# c99   - ISO C99 standard (not yet fully implemented)
# gnu99 - c99 plus GCC extensions
#CSTANDARD = -std=gnu99
CDEBUG = -g$(DEBUG)
#CWARN = -Wall -Wstrict-prototypes
#CWARN = -Wall   # show all warnings
CWARN = -Wall #suppress all warnings
####CTUNING = -funsigned-char -funsigned-bitfields -fpack-struct -fshort-enums
CTUNING = -ffunction-sections -fdata-sections
CXXTUNING = -fno-exceptions -ffunction-sections -fdata-sections
#CEXTRA = -Wa,-adhlns=$(<:.c=.lst)

CFLAGS = $(CDEBUG) -O$(OPT) $(CWARN) $(CTUNING) $(CDEFS) $(CINCS) $(CSTANDARD) $(CEXTRA)
CXXFLAGS = $(CDEBUG) -O$(OPT) $(CWARN) $(CXXTUNING) $(CDEFS) $(CXXINCS)
#ASFLAGS = -Wa,-adhlns=$(<:.S=.lst),-gstabs
LDFLAGS = -O$(OPT) -lm -Wl,--gc-sections


# Programming support using avrdude. Settings and variables.
AVRDUDE_CONF = /etc/avrdude.conf
AVRDUDE_PORT = $(PORT)
AVRDUDE_WRITE_FLASH = -U flash:w:$(TARGET).hex

AVRDUDE_FLAGS = -V -F -C $(AVRDUDE_CONF) -p $(MCU) -P $(AVRDUDE_PORT) -c $(AVRDUDE_PROGRAMMER) -b $(UPLOAD_RATE) -D

# Program settings
CC = $(AVR_TOOLS_PATH)avr-gcc
CXX = $(AVR_TOOLS_PATH)avr-g++
LD = $(AVR_TOOLS_PATH)avr-gcc
OBJCOPY = $(AVR_TOOLS_PATH)avr-objcopy
OBJDUMP = $(AVR_TOOLS_PATH)avr-objdump
AR  = $(AVR_TOOLS_PATH)avr-ar
SIZE = $(AVR_TOOLS_PATH)avr-size
NM = $(AVR_TOOLS_PATH)avr-nm
AVRDUDE = $(AVRDUDE_PATH)avrdude
REMOVE = rm -f
MV = mv -f

# Define all object files.
OBJ = $(SRC:.c=.o) $(CXXSRC:.cpp=.o) $(ASRC:.S=.o)
OBJ_MODULES = $(C_MODULES:.c=.o) $(CXX_MODULES:.cpp=.o)
OBJ_APP = $(CXX_APP:.c=.o) $(C_APP:.c=.o)

# Define all listing files.
LST = $(ASRC:.S=.lst) $(CXXSRC:.cpp=.lst) $(SRC:.c=.lst)

# Combine all necessary flags and optional flags.
# Add target processor to flags.
ALL_CFLAGS = $(CFLAGS) -mmcu=$(MCU)
ALL_CXXFLAGS = $(CXXFLAGS) -mmcu=$(MCU)
ALL_ASFLAGS = -x assembler-with-cpp $(ASFLAGS) -mmcu=$(MCU)
ALL_LDFLAGS = $(LDFLAGS) -mmcu=$(MCU)


RED	= \\033[0;31m
GREEN	= \\033[0;32m
YELLOW	= \\033[0;33m
WHITE = \\033[m
ECHO 	= printf

ERROR = echo "[${RED} ERROR ${WHITE}]" && echo -n "${RED}" && cat temp.log && echo -n "${WHITE}"
WARN = echo "[${YELLOW} WARNING ${WHITE}]" && echo -n "${YELLOW}" && cat temp.log && echo -n "${WHITE}"
OK = echo "[${GREEN} OK ${WHITE}]"


# Default target.
all: target_files package

testMCU:
	@if [ -z $$MCU ]; then \
		echo "-----------------------"; \
		echo "Missing \$$MCU property to set your micro-controller type"; \
		echo "ex: # make MCU=atmega328p package"; \
		echo "-----------------------"; \
		exit 42; \
	fi

package: testMCU elf hex sizeafter

#$(TARGET).o: $(OBJ_APP)
#	test -d target || mkdir target
#	$(CXX) -c $(ALL_CXXFLAGS) main.cpp -o $(TARGET).o

elf: $(TARGET).elf
hex: $(TARGET).hex
eep: $(TARGET).eep
lss: $(TARGET).lss
sym: $(TARGET).sym

# Program the device.
upload: $(TARGET).hex
	$(AVRDUDE) $(AVRDUDE_FLAGS) $(AVRDUDE_WRITE_FLASH)

# Display size of file.
HEXSIZE = $(SIZE) --target=$(FORMAT) $(TARGET).hex
ELFSIZE = $(SIZE)  $(TARGET).elf
sizebefore:
	@if [ -f $(TARGET).elf ]; then echo; echo $(MSG_SIZE_BEFORE); $(HEXSIZE); echo; fi

sizeafter:
	@if [ -f $(TARGET).elf ]; then echo; echo $(MSG_SIZE_AFTER); $(HEXSIZE); echo; fi


# Convert ELF to COFF for use in debugging / simulating in AVR Studio or VMLAB.
COFFCONVERT=$(OBJCOPY) --debugging \
--change-section-address .data-0x800000 \
--change-section-address .bss-0x800000 \
--change-section-address .noinit-0x800000 \
--change-section-address .eeprom-0x810000


coff: $(TARGET).elf
	$(COFFCONVERT) -O coff-avr $(TARGET).elf $(TARGET).cof


extcoff: $(TARGET).elf
	$(COFFCONVERT) -O coff-ext-avr $(TARGET).elf $(TARGET).cof


.SUFFIXES: .elf .hex .eep .lss .sym

.elf.hex:
	$(OBJCOPY) -O $(FORMAT) -R .eeprom $< $@

.elf.eep:
	$(OBJCOPY) -O $(FORMAT) -j .eeprom --set-section-flags=.eeprom="alloc,load" \
	--no-change-warnings \
	--change-section-lma .eeprom=0 $< $@

# Create extended listing file from ELF output file.
.elf.lss:
	$(OBJDUMP) -h -S $< > $@

# Create a symbol table from ELF output file.
.elf.sym:
	$(NM) -n $< > $@

# Link: create ELF output file from library.
#target/$(TARGET).elf: $(TARGET).pde target/core.a
$(TARGET).elf: target/core.a
	@echo "Linking   ${GREEN} $@ ${WHITE}"
	$(LD) $(ALL_LDFLAGS) -o $@ -L. target/core.a /usr/lib/avr/lib/avr5/libc.a  target/core.a

target/core.a: $(OBJ)
	@for i in $(OBJ); do echo $(AR) rcs target/core.a $$i; $(AR) rcs target/core.a $$i; done

#target/core.a: $(OBJ_MODULES)
#	@for i in $(OBJ_MODULES); do echo $(AR) rcs target/core.a $$i; $(AR) rcs target/core.a $$i; done

.cpp.o:
	$(CXX) -c $(ALL_CXXFLAGS) $< -o $@ 2> temp.log || touch temp.errors
	@echo -n "compiling $<...    "
	@if test -e temp.errors; then $(ERROR); elif test -s temp.log; then $(WARN); else $(OK) ; fi;
	@if test -e temp.errors; then rm -f temp.errors temp.log; exit 42; fi;
	@rm -f temp.errors temp.log	

.c.o:
	$(CC) -c $(ALL_CFLAGS) $< -o $@ 2> temp.log || touch temp.errors
	@echo -n "compiling $<...    "
	@if test -e temp.errors; then $(ERROR); elif test -s temp.log; then $(WARN); else $(OK) ; fi;
	@if test -e temp.errors; then rm -f temp.errors temp.log; exit 42; fi;
	@rm -f temp.errors temp.log	

# Compile: create assembler files from C source files.
.c.s:
	$(CC) -S $(ALL_CFLAGS) $< -o $@
	
# Assemble: create object files from assembler source files.
.S.o:
	$(CC) -c $(ALL_ASFLAGS) $< -o $@


# Automatic dependencies
%.d: %.c
	$(CC) -M $(ALL_CFLAGS) $< | sed "s;$(notdir $*).o:;$*.o $*.d:;" > $@

%.d: %.cpp
	$(CXX) -M $(ALL_CXXFLAGS) $< | sed "s;$(notdir $*).o:;$*.o $*.d:;" > $@


# Target: clean project.
clean:
	$(REMOVE) $(TARGET).hex $(TARGET).eep $(TARGET).cof $(TARGET).elf \
	$(TARGET).map $(TARGET).sym $(TARGET).o $(TARGET).lss target/core.a \
	$(OBJ) $(LST) $(SRC:.c=.s) $(SRC:.c=.d) $(CXXSRC:.cpp=.s) $(CXXSRC:.cpp=.d)

.PHONY:	all package elf hex eep lss sym program coff extcoff clean target_files sizebefore sizeafter

#include $(SRC:.c=.d)
#include $(CXXSRC:.cpp=.d)
