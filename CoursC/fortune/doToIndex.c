#include <string.h>
#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>

#include "doToIndex.h"

#define TMP_SIZE 256

int getTo(char* c, unsigned int index, FILE* fd) {
    char tmp[TMP_SIZE];
    size_t i;

    fseek(fd, 0, SEEK_SET);
    for (i = 0; i < index;) {
        if(fgets(tmp, TMP_SIZE, fd)==NULL) {
            return -1;
        } else {
            if (strcmp(tmp, c)==0) {
                i++;
            }
        }
    }
    return ftell(fd);
}

int readUntil(char* c, FILE* fd) {
    char tmp[TMP_SIZE];
    
    while(fgets(tmp, TMP_SIZE, fd)!=NULL) {
        if (strcmp(tmp, c)==0) {
            return 0;
        } else {
            printf("%s", tmp);
        }
    }
    return -1;
}

unsigned int countIndex(char* c, FILE* fd) {
    unsigned int i;
    char tmp[TMP_SIZE];

    fseek(fd, 0, SEEK_SET);
    while(fgets(tmp, TMP_SIZE, fd)!=NULL) {
        if (strcmp(tmp, c)==0) {
            i++;
        }
    }
    return i;
}