#include <unistd.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>

#define BSIZE 1024


void get_path_from_link(const char* link) {
    int fd = open(link, O_RDONLY);
    char buf[BSIZE]; 

    int length = readlink(link, buf, BUFSIZ);
    if(length == -1) {
        perror("readlink");
    }
    int result = write(1, buf, length);
    putchar('\n');
    if(result == -1) {
        perror("write");
    }
}

int main(int argc, char const *argv[]) {
    if(argc != 2) {
        printf("Usage: %s <filename>", argv[0]);
        exit(1);
    }

    struct stat sb;
    if(lstat(argv[1], &sb) == -1) {
        perror("lstat");
        exit(2);
    }

    printf("inode : %ld\n", (long) sb.st_ino);
    printf("Taille du fichier : %lld octets\n", (long long) sb.st_size);
    printf("Dernière modification du fichier : %ld\n", sb.st_mtime);

    printf("Type de fichier : ");

    switch (sb.st_mode & S_IFMT) {
        case S_IFDIR:  
            printf("d\n");                
            break;
        case S_IFREG:  
            printf("f\n");       
            break;
        case S_IFLNK:
            printf("l\n");
            get_path_from_link(argv[1]);
            break;
        default:       
            printf("?\n");
    }

    return 0;
}
