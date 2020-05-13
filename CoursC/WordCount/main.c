#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <ctype.h>

unsigned long countWords(FILE* fd);
unsigned long countLines(FILE* fd);
unsigned long countChars(FILE* fd);
int findOptions(int argc, char const *argv[]);

int main(int argc, char const *argv[]) { 
    size_t i;

    if (argc < 2) {
        printf("Usage: %s <file name>\n", argv[0]);
        exit(1);
    }

    for (i = 1; i < argc; i++) {
	FILE* fd;

	if (argv[i][0]=='-') {
	  continue;
	}

        fd = fopen(argv[i], "r");

        if (fd==NULL) {
            perror("fread");
        } else {
            switch (findOptions(argc, argv)) {
            case 0:
                printf("There are %lu words in %s\n", countWords(fd), argv[i]);
                break;
            case 1:
                printf("There are %lu lines in %s\n", countLines(fd), argv[i]);
                break;
            case 2:
                printf("There are %lu caracters in %s\n", countChars(fd), argv[i]);
                break;
            default:
              break;
            }
            
            fclose(fd);
        }
    }
    
    return 0;
}

unsigned long countWords(FILE* fd) {
    char tmp;
    int file_stat;
    int already_spaced = 1;
    unsigned long count = 0;

    file_stat = fread(&tmp, sizeof(char), 1, fd);
    while (file_stat!=0) {
        if (isspace(tmp)) {
            if (!already_spaced) {
                count++;
            }
            already_spaced++;
        } else {
            already_spaced=0;
        }
        file_stat = fread(&tmp, sizeof(char), 1, fd);
    }

    return count;
}

unsigned long countLines(FILE* fd) {
    char tmp;
    int file_stat;
    unsigned long count = 0;

    file_stat = fread(&tmp, sizeof(char), 1, fd);
    while (file_stat!=0) {
        if (tmp=='\n') {
            count++;
        }
        file_stat = fread(&tmp, sizeof(char), 1, fd);
    }

    return count;
}

unsigned long countChars(FILE* fd) {
    unsigned long count = 0;

    fseek(fd, 0, SEEK_END);
    count = ftell(fd);
    fseek(fd, 0, SEEK_SET);

    return count;
}


int findOptions(int argc, char const *argv[]) {
    size_t i;
    for (i = 1; i < argc; i++) {
        if (argv[i][0]=='-') {
            switch (argv[i][1]) {
            case 'w':
                return 0;
                break;
            case 'l':
                return 1;
                break;
            case 'c':
                return 2;
                break;
            default:
                return 0;
                break;
            }
        }
        
    }
    return 0;
}
