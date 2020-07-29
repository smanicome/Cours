#include "heap.h"
#include "visualheap.h"
#include <stdlib.h>
#include <stdio.h>
#include <time.h>

#define MAX_HEAP_SIZE 100

int decreasing_comparator(const void *a, const void *b) {
    int x = *(const int*) a;
    int y = *(const int*) b;
    return y - x;
}

int main(int argc, char const *argv[]) {
    if (argc < 2) {
        printf("Usage: %s <array_size>", argv[0]);
    }

    int i;
    int n;

    n = (int) strtol(argv[1], NULL, 10);
    heap *h = create_heap(n);

    srand(time(NULL));
    for (i = 0; i < n; i++) {
        insert_heap(h, rand() % 100);
    }

    time_t start = time(NULL);
    qsort(h->tree, n, sizeof(int), decreasing_comparator);
    time_t end = time(NULL);

    printf("%ld ", end - start);
    printf("%d\n", n);
    free_heap(h);
    return end - start;
}
