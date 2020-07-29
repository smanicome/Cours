#/bin/bash
array_size=10000;
time_s=0; 
while [ $time_s -ne 20 ]; 
do
	if [ $time_s -lt 20 ] 
	then
		array_size=$(( $array_size + $array_size/10 ))
	else
		array_size=$(( $array_size - $array_size/12 ))
	fi;

	time_s=0;
	./tp11 $array_size;
	time_s=$?; 
done
