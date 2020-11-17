#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int main(int argc, char const *argv[]) {
    int fd[2];
    pipe(fd);

    switch(fork()) {
        case -1:
            perror("fork");
            exit(2);
            break;
        case 0: {
            int c;
            c = fgetc(stdin);
            break;
        }
        default: {
            int stat;
            if(wait(&stat) == -1 || WEXITSTATUS(stat) != 0) {
                printf("ERROR\n");
            } else {
                printf("OK\n");
            }
        }
    }
    return 0;
}
