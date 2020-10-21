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
	
	int opt;
	while (opt = getopt(argc, argv, "snx") != -1){
		printf("%c\n", opt);
		switch(opt) {
			case 'n':
				new_line = 0;
				break;
			case 's':
				printf("alert\n");
				space = 0;
				break;
			case 'x':
			printf("++++++++++++++++++++++");
				break;
			default:
				break;
		}
	}

	for(int i = optind; i < argc; i++) {
		printf("%s", argv[i]);

		if((i < argc - 1) && space) {
			putchar(' ');
		}
	}

	if(new_line) {
		putchar('\n');
	}

	exit(0);
}
