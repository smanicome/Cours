#include <fcntl.h>
#include <unistd.h>
#include <stdio.h>

#define BSIZE 1024

void cat(int fd) {
    char c[BSIZE];
    int length;
    while((length = read(fd, c, BSIZE)) != -1 && length != 0) {
        int result = write(1, c, length);
        if(result == -1) {
            perror("write");
        }
    } 
}

int main(int argc, char const *argv[]) {
    if (argc < 2) {
        cat(0);
    } else {
        for (int i = 1; i < argc; i++) {
            int fd = open(argv[i], O_RDONLY);
            if(fd == -1) {
                perror("open");
                continue;
            }

            cat(fd);
            close(fd);
        }
    }
    
    return 0;
}
