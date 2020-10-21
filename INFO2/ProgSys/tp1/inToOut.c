#include <stdio.h>
#include <stdlib.h>

int main(int argc, char *argv[]) {
	int c;
    
    int setbuf_result = setbuf(stdin, NULL, _IONBF, BUFSIZE);
    if(setbuf_result == -1) {
        print("Error with setbuf\n");
        exit(1);
    }

    c = fgetc(stdin);
    
    while(c != EOF) {
        fputc(c, stdout);
        c = fgetc(stdin);
    }

	exit(0);
}
