#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <fcntl.h>

int main(int argc, char const *argv[]) {
    int fd = open("redirect_file.txt", O_CREAT|O_WRONLY, 0755);
    if(fd == -1) {
        perror("open");
        exit(1);
    }

    if(dup2(fd, 1) == -1) {
        perror("dup2");
        exit(2);
    }

    execlp("/bin/ls", "-l", NULL);
    close(fd);
    return 0;
}
