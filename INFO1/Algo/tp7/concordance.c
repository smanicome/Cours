#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "list.h"

#define MAX_WORD_LENGTH 80
#define M 2000

void read_text(table *tab, FILE *infile) {
    int pos = 0;
    char *word = (char *)malloc(MAX_WORD_LENGTH * sizeof(char));
    while (fscanf(infile, "%s ", word) != -1) {
        add_occ_table(tab, word, pos);
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

    table *tab = create_table(M);
    read_text(tab, fin);
    fclose(fin);

    display_tab(tab);

    int words = 0;
    int distinct_words = 0;
    int i;
    link *ptr;

    for (i = 0; i < M; i++) {
        link *lst = tab->bucket[i];
        if (lst == NULL) {
            continue;
        }

        for (ptr = lst; ptr != NULL; ptr = ptr->next) {
            distinct_words++;
            words += count_occurrences(ptr);
        }
    }

    printf("total number of words = %d\n", words);
    printf("number of distinct words = %d\n", distinct_words);

    if (tab->size != size_table(tab)) {
        printf("Mismatching word count\n"); 
    }

    free_tab(tab);

    return 0;
}

/* 2city11.txt 
    M = 1:
        real    0m29,391s
        user    0m28,712s
        sys     0m0,092s
    
    M = 50
        real    0m4,265s
        user    0m3,913s
        sys     0m0,057s
    
    M = 2000
        real    0m2,780s
        user    0m2,233s
        sys     0m0,103s


    big.txt 
        M = 1:
            real    0m29,391s
            user    0m28,712s
            sys     0m0,092s
        
        M = 50
            real    0m4,265s
            user    0m3,913s
            sys     0m0,057s
        
        M = 2000
            real    0m2,780s
            user    0m2,233s
            sys     0m0,103s
 */