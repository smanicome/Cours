CC=gcc
CFLAGS=-Wall -ansi
LDFLAGS=
DEPS=visualtree.h tree.h bst.h
OBJ=visualtree.o tree.o bst.o

%.o: %.c $(DEPS)
	$(CC) -c -o $@ $< $(CFLAGS)

tp09: tp09.o $(OBJ)
	gcc -o $@ $^ $(LDFLAGS)

.PHONY: clean
clean:
	rm -f $(OBJ) tp09 tp09.o current-tree.dot current-tree.pdf