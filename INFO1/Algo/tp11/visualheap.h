#ifndef VISUALHEAP_H
#define VISUALHEAP_H

#include "heap.h"

/*
 * Open a file current-heap.dot, write the DOT code for
 * the heap h, and convert the .dot-file to a pdf.
 */
void write_heap(heap *h);

#endif /* VISUALHEAP_H */
