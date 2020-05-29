#ifndef HEAP_H
#define HEAP_H

typedef struct {
  int *tree;
  int size;
  int max;
} heap;

heap *create_heap(int max);
void insert_heap(heap *h, int elt);
int is_heap(heap *h);
int extract_min(heap *h);
void free_heap(heap *h);

#endif