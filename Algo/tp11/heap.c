#include "heap.h"
#include <stdlib.h>
#include <stdio.h>

heap *create_heap(int max) {
    heap *h = (heap *)malloc(sizeof(heap));
    h->max = max;
    h->size = 0;
    h->tree = (int *)malloc(max * sizeof(int));
    return h;
}

void free_heap(heap *h) {
	if (h == NULL)
        return;
    free(h->tree);
    free(h);
}

void insert_heap(heap *h, int elt) {
    int i;
    if (h->size == h->max) {
        printf("Heap has already reached max size\n");
        return;
    }

    h->tree[h->size] = elt;

    if (h->size % 2 == 1) { /* If is left child */
        i = (h->size - 1) / 2;
    } else { /* If is right child */
		i = (h->size - 2) / 2;
    }

	while (i >= 0 && elt <= h->tree[i]) {
        h->tree[h->size] = h->tree[i];
        h->tree[i] = elt;

        if (i % 2 == 1) { /* If is left child */
            i = (i - 1) / 2;
        } else { /* If is right child */
            i = (i - 2) / 2;
        }
    }

    (h->size)++;
}

int is_heap(heap *h) {
    int i;
    if (h == NULL) {
        return 0;
    }

    for (i = h->size - 1; i > 0; i--) {
        int j;
        if (i % 2 == 1) { /* If is left child */
            j = (i - 1) / 2;
        } else { /* If is right child */
            j = (i - 2) / 2;
        }

        if (j >= 0 && h->tree[j] > h->tree[i]) {
            printf("ARgh! i: %d tree[i]: %d tree[j]: %d\n", i, h->tree[i], h->tree[j]);
            return 0;
        }
    }

    return 1;
}

int extract_min(heap *h) {
    int i = 0;
    int n, j;

    if (h == NULL || h->size == 0) {
        return -1;
    }

    /* i = 0 */
    n = h->tree[i];
    h->tree[i] = h->tree[h->size - 1];

    (h->size)--;

    j = i * 2 + 1;
    while (j < h->size) {
        if (j + 1 < h->size && h->tree[j] > h->tree[j + 1] && h->tree[j + 1] < h->tree[i]) {
            int tmp = h->tree[i];
            h->tree[i] = h->tree[j];
            h->tree[j] = tmp;

            i = j + 1;
        } else if (h->tree[j] < h->tree[i]) {
            int tmp = h->tree[i];
            h->tree[i] = h->tree[j];
            h->tree[j] = tmp;

            i = j;
        } else {
            break;
        }

        j = i * 2 + 1;
    }

    return n;
}