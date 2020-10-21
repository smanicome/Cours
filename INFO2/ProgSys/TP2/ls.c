#include <unistd.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>
#include <errno.h>
#include <string.h>
#include <dirent.h>

#define BSIZE 1024

int is_dir(const char* file_name) {
    struct stat sb;
    if(lstat(file_name, &sb) == -1) {
        perror("lstat");
        exit(2);
    }

    return (sb.st_mode & S_IFMT) == S_IFDIR;
}

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

void do_lstat(const char* file_name, int do_recursive) {
    struct stat sb;
    if(lstat(file_name, &sb) == -1) {
        perror("lstat");
        exit(2);
    }

    switch (sb.st_mode & S_IFMT) {
        case S_IFDIR:  
            printf("d ");             
            break;
        case S_IFREG:  
            printf("f ");       
            break;
        case S_IFLNK:
            printf("l ");
            get_path_from_link(file_name);
            break;
        default:       
            printf("? ");
    }

    printf("%ld ", (long) sb.st_ino);
    printf("%ld ", sb.st_mtime);
    printf("%6lld ", (long long) sb.st_size);
    
    char cpy_file_name[256];
    strcpy(cpy_file_name, file_name);

    char* ptr = strtok(cpy_file_name, "/");
    char name[256];

    while (ptr != NULL) {
        strcpy(name, ptr);
        ptr = strtok(NULL, "/");
    }
    
    printf("%s\n", name);
    
    if(do_recursive && (sb.st_mode & S_IFMT) == S_IFDIR) {
        read_dir(file_name, do_recursive);
    }
}

int read_dir(const char* file_name, int do_recursive) {
    DIR* dir = opendir(file_name);
    if(dir == NULL) {
        perror("opendir");
        errno = 0;
        return 1;
    }

    struct dirent * data;
    while((data = readdir(dir))) {
        if(data == NULL) {
            if(errno != 0) {
                perror("readdir");
            }
            break;
        }
        char* cpy = (char*) malloc(strlen(file_name)); 
        strcpy(cpy, file_name);

        if(cpy[strlen(cpy) -1] != '/')
            strcat(cpy, "/");
        
        strcat(cpy, data->d_name);

        do_lstat(cpy, do_recursive);

        free(cpy);
    }
    
    closedir(dir);
    return 0;
}

int main(int argc, char const *argv[]) {

    int opt;
    int do_recursive = 0;
	while ((opt = getopt(argc, argv, "rl1")) != -1){
		if(opt == 'r') {
            do_recursive = 1;
            break;
        }
	}

    if (argc == 1) {
        read_dir(".", do_recursive);
    } else {
        for (int i = 1; i < argc; i++) {
            printf("%s\n\n", argv[i]);
            if(is_dir(argv[i])) {
                read_dir(argv[i], do_recursive);
            } else {
                do_lstat(argv[i], 0);
            }

            putchar('\n');
        }
    }
    
    return 0;
}
