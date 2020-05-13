#!/bin/bash

if [ $# -lt 1 ]; then
	echo "Usage: [file name]"
fi

if [ -e $1 ]; then
	var=0
	for i in `ls $1`
	do
		if  [[ -d $i || -f $i ]]; then
			echo "$i"
			var=$(($var+1))
		fi
	done
	echo $var
else
	echo "$1 is not an existing file"
fi
    	  
