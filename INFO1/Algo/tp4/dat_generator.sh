#/bin/bash
for i in `seq 1 20`; 
do
	./tp04-instr 10000 >> data/select_sort.dat
done