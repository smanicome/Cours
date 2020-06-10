CC=gcc
CFLAGS=-Wall -ansi
LDFLAGS=
DEPS=sort.h swap.h
OBJ=sort.o swap.o

%.o: %.c $(DEPS)
	$(CC) -c -o $@ $< $(CFLAGS)

sort: sort.o $(OBJ)
	gcc -o $@ $^ $(LDFLAGS)

.PHONY: clean
clean:
	rm -f $(OBJ) sort sort.o