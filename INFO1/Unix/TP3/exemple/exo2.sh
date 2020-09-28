#!/bin/bash

select choice in \
	"<1> Comptabilité" \
	"<2> Gestion commerciale" \
	"<3> Paie" \
	"<9> Quitter";
	do
		case $REPLY in
			1) echo $choice;;
			2) echo $choice;;
			3) echo $choice;;
			4) echo $choice;;
		esac
	done
