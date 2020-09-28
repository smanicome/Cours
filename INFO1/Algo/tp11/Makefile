CC=gcc
CFLAGS=-Wall -ansi
LDFLAGS=
DEPS=heap.h visualheap.h
OBJ=heap.o visualheap.o

%.o: %.c $(DEPS)
	$(CC) -c -o $@ $< $(CFLAGS)

tp11: tp11.o $(OBJ)
	gcc -o $@ $^ $(LDFLAGS)

.PHONY: clean
clean:
	rm -f $(OBJ) tp11 tp11.o