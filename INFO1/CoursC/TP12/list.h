#ifndef __LIST_H__
#define __LIST_H__

#define NB_PACK 4096

typedef struct node {
    char *word;
    struct node *next;
} Node, *List;

typedef struct _table {
    List *bucket;
    int M;    /* nombre de seaux */
    int size; /* nombre de mots dans la table */
} table;

table *create_table(int M);
int size_table(table *tab);

void free_list(Node *lst);
void free_tab(table *tab);

Node *find_list(Node *lst, char word[]);
Node *insert_first_list(Node *lst, char word[], int pos);
void display_list(Node *lst);
void display_tab(table *tab);
void add_word(table *tab, char word[], int pos);
int generateHash(char *str, unsigned int len);

#endif
