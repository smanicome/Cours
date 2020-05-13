#include <stdlib.h>
#include <stdio.h>
#include "sort.h"
#include "array.h"

#define MAX_VALUE 10000

extern int nb_less;
extern int nb_swap;

int main(int argc, char const *argv[]) {
  int size;
  nb_less = 0;
  nb_swap = 0;

	if (argc < 2) {
    printf("Usage: %s <array size>\n", argv[0]);
    exit(1);
  }

  size = (int)strtol(argv[1], NULL, 10);

  int *tab = NULL;
  tab = create_array(size);
  fill_random_array(tab, size, MAX_VALUE);

  quick_sort(tab, 0, size-1);

  /* printf("%d ", size);
  printf("%d ", nb_less);
  printf("%d\n", nb_swap); */

  free(tab);
  return 0;
}
