#include "swap.h"
#include <stdlib.h>

void swap_mem(void *z1, void *z2, size_t size) {
    unsigned char *x = z1;
    unsigned char *y = z2;
    unsigned char tmp;
    size_t i;
    
    for (i = 0; i != size; ++i) {
        tmp = x[i];
        x[i] = y[i];
        y[i] = tmp;
    }
}