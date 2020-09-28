#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "tree.h"

char **create_string_table(int n) {
    int i;
    char **tab = (char **) malloc(n * sizeof(char *));

    if (tab == NULL) {
        printf("Unable to allocate necessary memory\n");
        return NULL;
    }

    for (i = 0; i < n; i++) {
        tab[i] = (char *) malloc(2 * STR_SIZE * sizeof(char));
    }

    return tab;
}

int *create_int_table(int n) {
    int i;
    int *tab = (int *)malloc(n * sizeof(int));

    if (tab == NULL) {
        printf("Unable to allocate necessary memory\n");
        return NULL;
    }

    for (i = 0; i < n; i++) {
        tab[i] = 0;
    }

    return tab;
}

void free_tree(node *t) {
    if (t == NULL)
        return;

    if (t->left != NULL) {
        free_tree(t->left);
    }

    if (t->right != NULL) {
        free_tree(t->right);
    }

    free(t);
}

void free_table(char **tab, int length) {
    int i = 0;

    for (i = 0; i < length; i++) {
        free(tab[i]);
    }

    free(tab);
}

node *parse_tree(FILE *file) {
    node *n = NULL;
    char c;

    if (fscanf(file, "%c", &c) == -1)
        return NULL;

    if (c == '0') {
        fscanf(file, "%c", &c);
        n = create_leaf(c);

    } else if (c == '1') {
        n = create_o_node();
        n->left = parse_tree(file);
        n->right = parse_tree(file);
    }

    return n;
}

char **insert(char **table, node *n, char *str) {
    if (n == NULL)
        return NULL;

    char t1[STR_SIZE];
    char t2[STR_SIZE];

    if (n->left == NULL && n->right == NULL) {
        printf("%s\n", str);
        strcpy(table[(unsigned char)n->data], str);
    } else {
        strcpy(t1, str);
        strcpy(t2, str);
        table = insert(table, n->left, strcat(t1, "0"));
        table = insert(table, n->right, strcat(t2, "1"));
    }

    return table;
}

char **export_tree(node *n, char **table) {
    char string[STR_SIZE] = "";

    return insert(table, n, string);
}

void encode(char **tab, FILE *fdin, FILE *fdout) {
    int i = 0;
    char c;
    while (fscanf(fdin, "%c", &c) != -1) {
        fprintf(fdout, "%s ", tab[(unsigned char)c]);
        i++;
    }
    
}

void get_occurrences(FILE *fdin, int *tab) {
    char c;
    while (fscanf(fdin, "%c", &c) != -1) {
        tab[(unsigned char)c] += 1;
    }
}