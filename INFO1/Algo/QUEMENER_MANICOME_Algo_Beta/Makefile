CC=gcc
CFLAGS=-Wall -ansi
LDFLAGS=-lm
DEPS=tree.h queue.h node.h visualtree.h
OBJ=tree.o queue.o node.o visualtree.o

%.o: %.c $(DEPS)
	$(CC) -c -o $@ $< $(CFLAGS)

huffman: main.c $(OBJ)
	$(CC) -o $@ $^ $(CFLAGS) $(LDFLAGS)

.PHONY: clean
clean:
	rm -f $(OBJ) main
