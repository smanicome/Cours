CC=gcc
CFLAGS=-Wall -ansi
LDFLAGS=
DEPS=node.h visualtree.h encode.h queue.h
OBJ=node.o visualtree.o encode.o queue.o

%.o: %.c $(DEPS)
	$(CC) -c -o $@ $< $(CFLAGS)

huffman: main.o $(OBJ)
	gcc -o $@ $^ $(LDFLAGS)

.PHONY: clean
clean:
	rm -f $(OBJ) huffman main.o current-tree.dot current-tree.pdf