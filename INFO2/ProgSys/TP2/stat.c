#include <unistd.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <stdio.h>
#include <stdlib.h>

int main(int argc, char const *argv[]) {
    if(argc != 2) {
        printf("Usage: %s <filename>", argv[0]);
        exit(1);
    }

    struct stat sb;
    if(stat(argv[1], &sb) == -1) {
        perror("stat");
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
        default:       
            printf("?\n");
    }

    return 0;
}
