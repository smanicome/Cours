all: bench

bench: occurence.o palindrome.o sum.o sequence.o bench.o
	gcc -o bench occurence.o palindrome.o sum.o sequence.o bench.o

sequence.o: sequence.c
	gcc -o sequence.o -c sequence.c -Wall -ansi

sum.o: sum.c
	gcc -o sum.o -c sum.c -lmath -Wall -ansi

palindrome.o: palindrome.c
	gcc -o palindrome.o -c palindrome.c -Wall -ansi

occurence.o: occurence.c
	gcc -o occurence.o -c occurence.c -Wall -ansi

bench.o: bench.c occurence.h palindrome.h sequence.h sum.h
	gcc -o bench.o -c bench.c -Wall -ansi

clean:
	rm -rf *.o

mrproper: clean
	rm -rf bench
