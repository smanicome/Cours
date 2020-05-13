#include <stdlib.h>
#include <stdio.h>
#include <string.h>

#define BUFF_SIZE 20

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
void ordered_insertion(List *l, Cell *new, int order_func(Cell *, Cell *));
void print_list(List l);
void free_list(List l);

int main() {
    List l;

    l = read_file();
    print_list(l);
    free_list(l);

    return 0;
}

List read_file() {
    FILE *fd;
    List l = NULL;

    fd = fopen("liste_nom.txt", "r");
    if (fd == NULL) {
        perror("fopen");
    }

    while (1) {
        Cell *c;
        char *first;
        char *last;
        char buffer[BUFF_SIZE];
        int age, res;

        res = fscanf(fd, "%s", buffer);
        if (res == EOF) {
            break;
        }

        first = (char *)malloc((strlen(buffer) + 1) * sizeof(char));
        strcpy(first, buffer);

        fscanf(fd, "%s", buffer);
        last = (char *)malloc((strlen(buffer) + 1) * sizeof(char));
        strcpy(last, buffer);

        fscanf(fd, "%d", &age);

        c = allocate_cell(first, last, age);
        ordered_insertion(&l, c, age_order);
    }

    return l;
}

Cell *allocate_cell(char *first, char *last, int age) {
    Cell *c;
    c = (Cell *)malloc(sizeof(Cell));

    c->first_name = first;
    c->last_name = last;
    c->age = age;

    return c;
}

int age_order(Cell *p1, Cell *p2) {
    if (p1->age > p2->age) {
        return 1;
    } else if (p1->age < p2->age) {
        return -1;
    } else {
        return 0;
    }
}

int name_order(Cell *p1, Cell *p2) {
    return strcmp(p1->first_name, p2->first_name);
}

void ordered_insertion(List *l, Cell *new, int order_func(Cell *, Cell *)) {
    Cell *previous;
    
	for (Cell *c = *l; c != NULL && order_func(new, c) == 1; c = c->next) {
        previous = c;
    }

	if (previous != NULL) {
        new->next = previous->next;
        previous->next = new;
    } else {
        *l = new;
    }
}

void print_list(List l) {
    for (Cell *c = l; c != NULL; c = c->next) {
        printf("%s %s %d\n", c->first_name, c->last_name, c->age);
    }
}

void free_list(List l) {
	for (Cell* c = l; c != NULL;) {
        Cell* next = c->next;
        free(c->first_name);
        free(c->last_name);
        free(c);
        c = next;
    }
}