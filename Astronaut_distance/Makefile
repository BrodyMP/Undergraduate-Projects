CC = g++
CFLAGS = --std=c++11 -Wall -c
LFLAGS = --std=c++11
INCLUDE = include
SRC = src
OBJ = obj
DOC = doc
TEST = test

all: satcom
	@echo "satcom compiled"

satcom: $(OBJ)/main.o $(OBJ)/point3d.o
	$(CC) $(LFLAGS) $(OBJ)/main.o $(OBJ)/point3d.o -o ./bin/satcom

$(OBJ)/main.o: $(SRC)/main.cpp $(INCLUDE)/point3d.hpp
	$(CC) $(CFLAGS) $(SRC)/main.cpp -o $(OBJ)/main.o

$(OBJ)/point3d.o: $(SRC)/point3d.cpp $(INCLUDE)/point3d.hpp
	$(CC) $(CFLAGS) $(SRC)/point3d.cpp -o $(OBJ)/point3d.o

.PHONY: clean doc test

doc:
	doxygen Doxyfile

test:
	chmod 777 test.sh
	./test.sh

clean:
	rm -rf $(OBJ)/* satcom  $(DOC)/*
