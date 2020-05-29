#include "heap.h"
#include "visualheap.h"
#include <stdlib.h>
#include <stdio.h>
#include <time.h>

#define MAX_HEAP_SIZE 100

int main(int argc, char const *argv[]) {
    int i;
    heap *h = create_heap(MAX_HEAP_SIZE);

    srand(time(NULL));

    for (i = 0; i < MAX_HEAP_SIZE; i++) {
        insert_heap(h, i);
    }

    if (is_heap(h)) {
        printf("OK\n");
    } else {
        printf("KO\n");
    }

    int n = extract_min(h);
    printf("value extracted: %d\n", n);

    write_heap(h);
    free_heap(h);
    return 0;
}
