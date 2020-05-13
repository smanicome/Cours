#include "occurence.h"

int count(int t[], int lo, int hi, int elt) {
    if(lo>hi) {
        return 0;
    }

    return (t[lo]==elt) + count(t, lo+1, hi, elt);
}

int max_count(int t[], int lo, int hi) {
    if(lo>=hi) {
        return 0;
    }
    int c = count(t, lo, hi, t[lo]);
    int next = max_count(t, lo+1, hi);
    return next > c ? next : c;
}