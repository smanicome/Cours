#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "list.h"

#define MAX_WORD_LENGTH 80

void read_text(table *tab, FILE *infile) {
    char *word = (char *)malloc(MAX_WORD_LENGTH * sizeof(char));
    int pos;
    while (fscanf(infile, "%s ", word) != -1) {
        add_word(tab, word, pos);
        pos++;
    }

    free(word);
}

int main(int argc, char **argv) {

    if (argc < 2) {
        fprintf(stderr, "Usage: concordance <in_file>\n");
        return 1;
    }

    FILE *fin = fopen(argv[1], "r");
    if (fin == NULL) {
        fprintf(stderr, "Error opening file for reading: %s\n", argv[1]);
        return 1;
    }

    table *tab = create_table(NB_PACK);
    read_text(tab, fin);
    fclose(fin);

    if (tab->size != size_table(tab)) {
        printf("Mismatching word count\n"); 
    }

    printf("%d different words found in Germinal\n", tab->size);
    free_tab(tab);

    return 0;
}

/* 
    25353 different words found in Germinal

    real    0m0,197s
    user    0m0,175s
    sys     0m0,013s
 */