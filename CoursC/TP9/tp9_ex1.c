#include <stdlib.h>
#include <stdio.h>

int * get_array(int n) {
    int i;
    int *array;

    array = (int *) malloc(n * sizeof(int));

    for (i = 0; i < n; i++) {
        array[i] = i + 1;
    }

    return array;
}

void free_array(int * array) {
    free(array);
}

void print_array(int * array, int size) {
    int i;
    for (i = 0; i < size; i++) {
        printf("%d ", array[i]);
    }
    putchar('\n');
}

int main(int argc, char const *argv[]) {
    int *array;
    int n;

    printf("Enter array size\n");
    scanf("%d", &n);
    array = get_array(n);
    print_array(array, n);

    free_array(array);
    return 0;
}