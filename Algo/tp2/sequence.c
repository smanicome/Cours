#include <stdio.h>
#include "sequence.h"

void increasing_sequence_rec(int n) {
    if(n<0) {
        return;
    }
    printf("%d ", n);
    increasing_sequence_rec(n-1);
}

void decreasing_sequence_rec(int n) {
    if(n<0) {
        return;
    }
    decreasing_sequence_rec(n-1);
    printf("%d ", n);
}

int longest_incr_iter(int t[], int lo, int hi) {
    int best = 1;
    int best_starter = t[lo];
    int tmpbest = 1;
    size_t i;

    for (i = lo; i < hi; i++) {
        if (t[i]<t[i+1]) {
            tmpbest++;
            if (tmpbest>best) {
                best = tmpbest;
                best_starter = i+2-tmpbest;
            }
        } else {
            tmpbest = 1;
        }        
    }
    printf("best increasing sequence started at index %d.\n", best_starter);
    return best;
}

int first_incr(int t[], int lo, int hi) {
    if (lo >= hi) {
        return 1;
    }
    if (t[lo]<t[lo+1]) {
        return 1 + first_incr(t, lo+1, hi);
    }
    return 1; 
}

int longest_incr_rec(int t[], int lo, int hi) {
    int current;
    int next;

    if (lo >= hi) {
        return 0;
    }

    current = first_incr(t, lo, hi);
    next = longest_incr_rec(t, lo+current, hi);

    return current > next ? current : next;
}