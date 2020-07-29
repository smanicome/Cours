#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "list.h"

/**
 * Create a Node representing 1 occurence of the string 'word'.
 * The string is copied and must be freed when the Node is freed.
 */
Node *create_node(char word[]) {
    Node *node = malloc(sizeof(Node));

    node->word = malloc(strlen(word) + 1);
    strcpy(node->word, word);
    node->next = NULL;
    return node;
}

void free_node(Node *node) {
    free(node->word);
    free(node);
}

void display_list(List lst) {
    while (lst != NULL) {
        printf("%s\n", lst->word);
        lst = lst->next;
    }
}

int size_table(table *tab) {
    int i;
    int distinct_words = 0;
    List ptr;

    for (i = 0; i < tab->M; i++) {
        List lst = tab->bucket[i];
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
        printf("Node %d\n\n", i);
        if (tab->bucket[i] != NULL) {
            display_list(tab->bucket[i]);
        }
        putchar('\n');
    }
}

void free_list(List lst) {
    while (lst != NULL) {
        List tmp = lst;
        lst = lst->next;
        free_node(tmp);
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

Node *find_list(Node* lst, char word[]) {
    Node *ptr = lst;
    while (ptr != NULL && !equal(ptr->word, word))
        ptr = ptr->next;
    return ptr;
}

Node *insert_first_list(Node *lst, char word[]) {
    Node *tmp = create_node(word);
    tmp->next = lst;
    return tmp;
}

void add_word(table *tab, char word[]) {
    int hash = generateHash(word, strlen(word));
    List current = find_list(tab->bucket[hash], word);
    if (current == NULL) {
        tab->size++;
        tab->bucket[hash] = insert_first_list(tab->bucket[hash], word);
    }
}

table * create_table(int M) {
    table *tbl = malloc(sizeof(table));
    Node **bucket = malloc(M * sizeof(Node *));
    tbl->M = M;
    tbl->bucket = bucket;
    tbl->size = 0;

    return tbl;
}

int generateHash(char *str, unsigned int len) {
    unsigned int result = 0;
    unsigned int i = 0;

    for (i = 0; i < len; str++, i++) {
        result += (i + 1) * (*str);
    }

    return result % NB_PACK;
}