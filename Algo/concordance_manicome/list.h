#ifndef __LIST_H__
#define __LIST_H__

typedef struct _olink {
    int pos;
    struct _olink *next;
} olink;

typedef struct _link {
    char *word;
    olink *occurrences;
    struct _link *next;
} link;

typedef struct _table {
    link **bucket;
    int M;    /* nombre de seaux */
    int size; /* nombre de mots dans la table */
} table;

table *create_table(int M);
int size_table(table *tab);

void free_list(link *lst);
void free_tab(table *tab);

link *find_list(link *lst, char word[]);
link *insert_first_list(link *lst, char word[], int pos);
void add_occurrence(link *lnk, int pos);
int count_occurrences(link *lnk);
void display_list(link *lst);
void display_tab(table *tab);
void add_occ_table(table *tab, char word[], int pos);
int generateHash(char *str, unsigned int len, int modulo);

#endif
