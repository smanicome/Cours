#include "arrays.h"
#include <stdlib.h>
#include <stdio.h>

int *create_array(int max_size) {
    int *ptr = (int*)malloc(max_size*sizeof(int));
    return ptr;
}

void free_array(int t[]) {
    free(t);
}

/*
 * Write this function!
 */
void insert_unsorted(int t[], int *size, int elt) {
    t[*size] = elt;
    (*size)++;
}

/*
 * Write this function!
 */
int find_unsorted(int t[], int size, int elt) {
    int i;
    for(i=0; i < size; i++) {
        if(t[i]==elt) {
            return 1;
        }
    }
    return 0;
}

/*
 * Write this function!
 */
void insert_sorted(int t[], int *size, int elt) {
    int i = (*size)-1;

    while (i>=0 && t[i]>elt) {
        t[i+1]=t[i];
        i--;
    }
    t[i+1] = elt;
    (*size)++;
}

/*
 * Write this function!
 */
int find_sorted(int t[], int size, int elt) {
    int tmpsize = size;
    int start = 0;
    int end = size;
    int mid = (start+end)/2;
    while(tmpsize>0) {
        tmpsize = tmpsize/2;
        if(t[mid]>elt) {
            end = mid;
            mid = (start+end)/2;
        } else if(t[mid]<elt){
            start = mid;
            mid = (start+end)/2;
        } else {
            return 1;
        }
    }
    return 0;
}