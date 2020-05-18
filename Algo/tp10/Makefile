CC=gcc
CFLAGS=-Wall -ansi
LDFLAGS=-lm
DEPS=visualtree.h avl.h
OBJ=visualtree.o avl.o

%.o: %.c $(DEPS)
	$(CC) -c -o $@ $< $(CFLAGS)

tp10: tp10.o $(OBJ)
	gcc -o $@ $^ $(LDFLAGS)

.PHONY: clean
clean:
	rm -f $(OBJ) tp10 tp10.o current-tree.dot current-tree.pdf