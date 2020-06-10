#ifndef SORT_H
#define SORT_H

typedef struct cell {
    char *first_name;
    char *last_name;
    int age;
    struct cell *next;
} Cell, *List;

Cell *allocate_cell(char *first, char *last, int age);
List read_file();
int age_order(Cell *p1, Cell *p2);
int name_order(Cell *p1, Cell *p2);
void ordered_insertion(List *l, Cell *newC, int order_func(Cell *, Cell *));
void print_list(List l);
void free_list(List l);

#endif