#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <fcntl.h>
#include <sys/wait.h>
#include <sys/types.h>

int main(int argc, char const *argv[]) {
    if(argc < 2) {
        printf("Usage: %s <command name> <...args>\n", argv[0]);
        exit(1);
    }
    
    switch(fork()) {
        case -1:
            perror("fork");
            exit(2);
            break;
        case 0: {
            int null = open("/dev/null", O_WRONLY);
            if(null == -1) {
                perror("open");
                exit(3);
            }

            dup2(null, 1);
            execvp(argv[1], (char * const*) argv+1);
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
