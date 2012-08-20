#CC=g++
#CFLAGS=-c -Wall
#LDFLAGS=
#SOURCES=src/main.cpp ../config.cpp
##hello.cpp factorial.cpp
#OBJECTS=$(SOURCES:.cpp=.o)
#EXECUTABLE=restmcu-check
#
#all: $(SOURCES) $(EXECUTABLE)
#	
#$(EXECUTABLE): $(OBJECTS)
#	$(CC) $(LDFLAGS) $(OBJECTS) -o $@
#
#.cpp.o:
#	$(CC) $(CFLAGS) $< -o $@

CONFIG = config.cpp

MCU_CONFIG = ../$(CONFIG)


SRC	= src/main.cpp src/stub/all.cpp $(MCU_CONFIG)



#############################################################################
# # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # #
#############################################################################

OPT	= \n
RED	= \\033[0;31m
GREEN	= \\033[0;32m
YELLOW	= \\033[0;33m
WHITE	= \\033[0m
BACK	= \t\t\t\t
ECHO 	= printf


#############################################################################
# # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # #
#############################################################################

NAME	=	restmcu-check

OBJ	=	$(SRC:.cpp=.o)

TYPE	=	`uname -s`

LIB	=	 -L. 


CFLAGS	=	-I. -I../src -Isrc/stub $(CFLAGS2)

CFLAGS2		=	-ansi -pedantic -O3 -Wall

CC	=	g++

.cpp.o:
	$(CC) $(CFLAGS) -c $< -o $@

RM	=	rm -f

#############################################################################
# # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # #
#############################################################################

$(NAME) :	$(OBJ)
		$(CC) $(CFLAGS) $(OBJ) -o $(NAME) $(LIB)

all :		$(NAME)
clean :
		rm -f $(OBJ)

fclean : 	clean
		rm -f $(NAME)

re : 		fclean all

#############################################################################
# # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # #
#############################################################################