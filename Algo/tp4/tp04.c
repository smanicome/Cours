#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#include "array.h"
#include "sort.h"

#define MAX_VALUE 10000
#define MAX_SIZE 1000
#define WIDTH 640

int compare(const void *a, const void *b) {
  int aint = *(int *)a;
  int bint = *(int *)b;
  if (aint == bint)
    return 0;
  else if (aint < bint)
    return -1;
  else
    return 1;
}

int main(int argc, char *argv[]) {

  srand(time(NULL));

  int size = MAX_SIZE;
  int max_value = MAX_VALUE;
  clock_t c1, c2;

  /* tableau de travail */
  int *tab = NULL;
  int *tabcpy = NULL;
  int *tabcpy1 = NULL;
  int *tabcpy2 = NULL;

  /* allocation et initialisation du tableau avec des valeurs aléatoires */
  tab = create_array(size);
  tabcpy = create_array(size);
  tabcpy1 = create_array(size);
  tabcpy2 = create_array(size);

  fill_random_array(tab, size, max_value);
  copy_array(tab, tabcpy, size);
  copy_array(tab, tabcpy1, size);
  copy_array(tab, tabcpy2, size);

  /* tri du tableau */
  c1 = clock();
  qsort(tab, size, sizeof(int), compare);
  c2 = clock();
  printf("qsort %f s\n", (float)(c2 - c1) / CLOCKS_PER_SEC);

  /* tri du tableau */
  c1 = clock();
  selection_sort(tabcpy, size);
  c2 = clock();
  printf("selection_sort %f s\n", (float)(c2 - c1) / CLOCKS_PER_SEC);

  /* tri du tableau */
  c1 = clock();
  insertion_sort(tabcpy1, size);
  c2 = clock();
  printf("insertion_sort %f s\n", (float)(c2 - c1) / CLOCKS_PER_SEC);

  /* tri du tableau */
  c1 = clock();
  quick_sort(tabcpy2, 0, size - 1);
  c2 = clock();
  printf("quick_sort %f s\n", (float)(c2 - c1) / CLOCKS_PER_SEC);

  if (cmp_array(tabcpy, tab, size)) {
    printf("tab = tabcpy OK\n");
  } else {
    printf("tab != tabcpy KO\n");
  }

  if (cmp_array(tabcpy1, tab, size)) {
    printf("tab = tabcpy1 OK\n");
  } else {
    printf("tab != tabcpy1 KO\n");
  }

  if (cmp_array(tabcpy2, tab, size)) {
    printf("tab = tabcpy2 OK\n");
  } else {
    printf("tab != tabcpy2 KO\n");
  }

  /* libération du tableau */
  free(tab);
  free(tabcpy);
  free(tabcpy1);
  free(tabcpy2);
  tab = NULL;

  return EXIT_SUCCESS;
}
