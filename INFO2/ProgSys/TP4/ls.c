#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>

void call_ext_func(char* func_name) {
    int stat;

    switch (fork()) {
        case -1:
            perror("fork");
            exit(1);
        case 0:
            execlp(func_name, func_name, NULL);
            break;
        default: {
            pid_t pid = wait(&stat);
            if(pid == -1) {
                perror("wait");
                exit(2);
            }
            break;
        }
    }
}

int main(int argc, char const *argv[]) {
    call_ext_func("ls");
    call_ext_func("ps");
    call_ext_func("free");
    return 0;
}
