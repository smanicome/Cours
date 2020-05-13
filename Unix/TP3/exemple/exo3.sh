#!/bin/bash

if [ $# -lt 3 ]; then
    echo "Usage: <x1> <x2> <i>"
    exit;
fi;

x1=$1;
x2=$2;
i=$3;

if [ $x1 -gt $x2 ]; then
    tmp=$x1;
    x1=$x2;
    x2=$tmp;
fi;

if [ $i < 0 ]; then
    until [ $x1 -gt $x2 ]; do
        echo $(($x1*$x1));
        x1=$(($x1+$i));
    done;
else
    echo "Bad incrementation passed";
fi;
