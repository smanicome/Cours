#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <time.h>

#include "doToIndex.h"
#define DELIMITER "%\n"

int main() {
    unsigned int index;
    unsigned int cursor = 0;
    unsigned int eof;

    FILE* fd;

    fd = fopen("fortune.txt", "r");
    if (fd==NULL) {
        printf("Seems like I can't read the file fortune.txt...\n");
        return 1;
    }

    srand(time(NULL));
    index = rand() % (countIndex(DELIMITER, fd)+1);
    
    fseek(fd, 0, SEEK_END);
    eof = ftell(fd);
    fseek(fd, 0, SEEK_SET);
    
    cursor = getTo(DELIMITER, index, fd);
    
    /*test if we reached last delimiter*/
    while (eof==cursor) {
        cursor = getTo(DELIMITER, index, fd);
    }
     
    if(cursor==-1) {
        printf("There is no index %u in fortune.txt\n", index);
        fclose(fd);
        return 2;
    }


    
    if(readUntil(DELIMITER, fd)==-1) {
        printf("Sadly we reached EOF\n");
        fclose(fd);
        return 3;
    }

    fclose(fd);
    return 0;
}
