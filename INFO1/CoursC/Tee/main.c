#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <string.h>

#define STR_LENGTH 256

void openFiles(unsigned long* list_size, FILE** file_list[], int argc, char* const argv[]);
void tee(unsigned long list_size, FILE* file_list[]);
void clean(unsigned long list_size, FILE* file_list[]);

int main(int argc, char* const argv[]) {
    FILE** file_list;
    file_list = (FILE**) malloc(sizeof(FILE));
    file_list[0] = stdout;
    unsigned long list_size = 1;
    /*standards i/o, no tests here*/

    if (argc > 1) {
        openFiles(&list_size, &file_list, argc, argv);
    }

    tee(list_size, file_list);
    clean(list_size, file_list);
    return 0;
}

void openFiles(unsigned long* list_size, FILE** file_list[], int argc, char* const argv[]) {
    size_t i;
        for (i = 1; i < argc; i++) {
            FILE* fd = fopen(argv[i], "a");
            if (fd==NULL) {
                perror("fread");
            } else {
                (*list_size)++;
                FILE** tmp;
                tmp = (FILE**) realloc((*file_list),(size_t) (*list_size)*sizeof(FILE));
                if (tmp!=NULL) {
                    (*file_list) = tmp;
                    (*file_list)[(*list_size)-1] = fd;
                } else {
                    perror("realloc");
                } 
            } 
        }
}

void tee(unsigned long list_size, FILE* file_list[]) {
    char* str = calloc(STR_LENGTH, 1);
    /*creates an array with no garbage value*/
    
    while (fgets(str, STR_LENGTH, stdin) != NULL) {
        
        size_t i;
        int write_stat;

        for (i = 0; i < list_size; i++) {
            write_stat = fwrite(str, 1, strlen(str), file_list[i]);
            if (write_stat==0) {
                perror("fwrite");
            }
            fflush(file_list[i]);
        }
    }
}

void clean(unsigned long list_size, FILE* file_list[]) {
    size_t i;
    for (i = 1; i < list_size; i++) {
        fclose(file_list[i]);
    }
    free(file_list);
}