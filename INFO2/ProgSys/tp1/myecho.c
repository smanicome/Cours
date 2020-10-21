#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int main(int argc, char *argv[]) {
	if(argc < 2) {
		printf("Usage: %s <arg1> <arg2> ... <argn>\n", argv[0]);
		exit(1);
	}

	int space = 1;
	int new_line = 1;
	int special_delimiter = ' ';

	int opt;
	while ((opt = getopt(argc, argv, "snS:")) != -1){
		switch(opt) {
			case 'n':
				new_line = 0;
				break;
			case 's':
				space = 0;
				break;
			case 'S':
				special_delimiter = optarg[0];
		}
	}

	for(int i = optind; i < argc; i++) {
		printf("%s", argv[i]);

		if((i < argc - 1) && space) {
			putchar(special_delimiter);
		}
	}

	if(new_line) {
		putchar('\n');
	}

	exit(0);
}
