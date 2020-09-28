#include "sort.h"
#include <stdio.h>
#include <stdlib.h>

int nb_less, nb_swap;

int less(int a, int b) {
  nb_less++;
  return a < b;
}

void swap(int *a, int *b) {
  nb_swap++;
  int tmp = *a;
  *a = *b;
  *b = tmp;
}

void selection_sort(int t[], int size) {
  int lowest;
  int i, j;

  for (i = 0; i < size; i++) {
    lowest = i;
    for (j = i + 1; j < size; j++)
      if (less(t[j], t[lowest]))
        lowest = j;

    swap(&t[i], &t[lowest]);
  }
}

void insertion_sort(int t[], int size) {
  int i;
  int j;

  for (i = 1; i < size; i++) {
    if (less(t[i], t[i - 1])) {
      for (j = i; j > 0 && less(t[j], t[j - 1]); j--) {
        swap(&t[j], &t[j - 1]);
      }
    }
  }
}

void quick_sort(int t[], int lo, int hi) {
  if (lo >= hi)
    return;
  int pivot = partition(t, lo, hi);
  quick_sort(t, lo, pivot - 1);
  quick_sort(t, pivot + 1, hi);
}

int partition(int t[], int lo, int hi) {
  int i = lo + 1;
  int j = hi;
  while (1) {
    while (less(t[i], t[lo]) && i < hi)
      i++;
    while (less(t[lo], t[j]) && j > lo)
      j--;
    if (i >= j)
      break;
    swap(&t[i], &t[j]);
    i++;
    j--;
  }
  swap(&t[lo], &t[j]);
  return j;
}