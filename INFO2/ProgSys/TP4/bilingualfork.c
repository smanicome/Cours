#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <sys/types.h>
#include <sys/wait.h>

int main(int argc, char const *argv[]) {
    pid_t pid = getpid();
    printf("Mon PID est %d  ", pid);
    char string[256];
    sprintf(string, "My PID is %d   ", pid);
    write(1, string, strlen(string));
    
    int stat;
    switch(fork()) {
        case -1:
            perror("fork");
            exit(1);
        case 0:
            printf("Je suis l'enfant et mon PID est %d  ", getpid());
            sprintf(string, "I am the child and my PID is %d    ", getpid());
            write(1, string, strlen(string));
            break;
        default:
            printf("Je suis le parent et mon PID est %d     ", getpid());
            sprintf(string, "I am the parent and my PID is %d   ", getpid());
            write(1, string, strlen(string));
    }

    puts("");
    wait(&stat);
    return 0;
}