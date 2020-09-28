#ifndef SORT_H
#define SORT_H

int less(int a, int b);

void swap(int *a, int *b);

void selection_sort(int t[], int size);

void insertion_sort(int t[], int size);

void quick_sort(int t[], int lo, int hi);

int partition(int t[], int lo, int hi);

#endif /* SORT_H */