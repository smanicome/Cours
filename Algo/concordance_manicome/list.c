#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "list.h"

/**
 * Create a link representing 1 occurence of the string 'word'.
 * The string is copied and must be freed when the link is freed.
 */
link *create_link(char word[], int pos) {
    link *lnk = malloc(sizeof(link));
    olink *olnk = malloc(sizeof(olink));
    olnk->pos = pos;
    olnk->next = NULL;

    lnk->word = malloc(strlen(word) + 1);
    strcpy(lnk->word, word);
    lnk->occurrences = olnk;
    lnk->next = NULL;
    return lnk;
}

void free_link(link *lnk) {
    olink *olnk = lnk->occurrences;
    while (olnk != NULL) {
        olink *next = olnk->next;
        free(olnk);
        olnk = next;
    }

    free(lnk->word);
    free(lnk);
}

int count_occurrences(link *lnk) {
    int occurrences = 0;
    olink *i;
    for (i = lnk->occurrences; i != NULL; i = i->next) {
        occurrences++;
    }
    return occurrences;
}

void display_list(link *lst) {
    while (lst) {
        olink *i;
        printf("%s : ", lst->word);
        for (i = lst->occurrences; i != NULL; i = i->next) {
            printf("%d ", i->pos);
        }
        putchar('\n');
        lst = lst->next;
    }
}

int size_table(table *tab) {
    int i;
    int distinct_words = 0;
    link *ptr;

    for (i = 0; i < tab->M; i++) {
        link *lst = tab->bucket[i];
        if (lst == NULL) {
            continue;
        }

        for (ptr = lst; ptr != NULL; ptr = ptr->next) {
            distinct_words++;
        }
    }

    return distinct_words;
}

void display_tab(table * tab) {
    int i;

    printf("M: %d\n", tab->M);
    printf("size: %d\n\n", tab->size);

    for (i = 0; i < tab->M; i++) {
        printf("LINK %d\n\n", i);
        if (tab->bucket[i] != NULL) {
            display_list(tab->bucket[i]);
        }
        putchar('\n');
    }
}

void free_list(link *lst) {
    while (lst) {
        link *tmp = lst;
        lst = lst->next;
        free_link(tmp);
    }
}

void free_tab(table *tab) {
    int i;
    for (i = 0; i < tab->M; i++) {
        if (tab->bucket[i] != NULL) {
            free_list(tab->bucket[i]);
        }
    }
}

int equal(char* w1, char* w2) {
    return strcmp(w1, w2) == 0;
}

link *find_list(link* lst, char word[]) {
    link *ptr = lst;
    while (ptr != NULL && !equal(ptr->word, word))
        ptr = ptr->next;
    return ptr;
}

link *insert_first_list(link *lst, char word[], int pos) {
    link *tmp = create_link(word, pos);
    tmp->next = lst;
    return tmp;
}

void add_occurrence(link *lnk, int pos) {
    olink *ptr, *new_ptr;
    
    for (ptr = lnk->occurrences;  ptr->next != NULL; ptr = ptr->next) {}

    new_ptr = (olink *)malloc(sizeof(olink));
    new_ptr->pos = pos;
    new_ptr->next = NULL;
    ptr->next = new_ptr;
}

table * create_table(int M) {
    table *tbl = malloc(sizeof(table));
    link **bucket = malloc(M * sizeof(link *));
    tbl->M = M;
    tbl->bucket = bucket;
    tbl->size = 0;

    return tbl;
}

void add_occ_table(table *tab, char word[], int pos) {
    int hash = generateHash(word, strlen(word), tab->M);
    link *current = find_list(tab->bucket[hash], word);
    if (current != NULL) {
        add_occurrence(current, pos);
    } else {
        tab->size++;
        tab->bucket[hash] = insert_first_list(tab->bucket[hash], word, pos);
    }
}

int generateHash(char *str, unsigned int len, int modulo) {
    unsigned int result = 0;
    unsigned int b = 3751; /* random number */
    unsigned int a = 368; /* random number */
    unsigned int i = 0;

    for (i = 0; i < len; str++, i++) {
        result = result * a + (*str);
        a = a * b;
    }

    return result % modulo;
}