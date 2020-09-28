#include "visualheap.h"
#include "heap.h"
#include <stdio.h>
#include <stdlib.h>

FILE *write_begin(char *name) {
    FILE *f = fopen(name, "w");
    fprintf(f, "digraph heap {\n");
    fprintf(f, "  node [shape=record,height=.1]\n");
    fprintf(f, "  edge [tailclip=false, arrowtail=dot, dir=both];\n\n");
    return f;
}

void write_end(FILE *f) {
    fprintf(f, "\n}\n");
    fclose(f);
}

void write_node(FILE *f, int *value, int index) {
    fprintf(f, "  n%p [label=\"<left> |{ <value> %d | <height> %d }| <right>\"];\n", (void *)value, *value, index);
}

void write_left_link(FILE *f, int *value, int *parent) {
    fprintf(f, "  n%p:left:c -> n%p:value;\n", (void *)parent, (void *)value);
}

void write_right_link(FILE *f, int *value, int *parent) {
    fprintf(f, "  n%p:right:c -> n%p:value;\n", (void *)parent, (void *)value);
}

void write_heap_aux(FILE *f, int *tree, int index, int size) {
    if (index >= size)
        return;

    write_node(f, tree + index, index);

    int next_index = index * 2 + 1;

    if (next_index < size) {
        write_left_link(f, tree + next_index, tree + index);
        write_heap_aux(f, tree, next_index, size);
    }

    next_index++;
    if (next_index < size) {
        write_right_link(f, tree + next_index, tree + index);
        write_heap_aux(f, tree, next_index, size);
    }
}

void write_heap(heap *h) {
    if (h == NULL)
        return;
    FILE *f;
    f = write_begin("current-heap.dot");
    write_heap_aux(f, h->tree, 0, h->size);
    write_end(f);
    system("dot -Tpdf current-heap.dot -o current-heap.pdf");
}
