#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>

void call_ext_func(char* func_name, char* const *argv) {
    int stat;

    switch (fork()) {
        case -1:
            perror("fork");
            exit(1);
        case 0:
            execvp(func_name, argv);
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

void mrun(char const *argv[]) {
    char global_name[256];
    sprintf(global_name, "RUN_%d", 0);
    char* value;

    for(int i = 1; ( value = getenv(global_name)) != NULL; i++) {
        call_ext_func(value, (char* const *) argv);
        sprintf(global_name, "RUN_%d", i);
    }
}

int main(int argc, char const *argv[]) {
    char global_name[256];
    sprintf(global_name, "RUN_%d", 0);
    char* value;

    for(int i = 1; ( value = getenv(global_name)) != NULL; i++) {
        printf("RUN_%d: %s\n", i-1, value);
        sprintf(global_name, "RUN_%d", i);
    }

    mrun(argv+1);

    return 0;
}
