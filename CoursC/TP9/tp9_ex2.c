#include <stdio.h>
#include <stdlib.h>

char ** get_array(int n, int m) {
    int i;
    char ** array;

    array = (char **) malloc(n * sizeof(char *));
    for (i = 0; i < n; i++) {
        array[i] = (char *) malloc(m * sizeof(char));
    }

    return array;
}

void free_array(char ** array, int n) {
    int i;
    for (i = 0; i < n; i++) {
        free(array[i]);
    }

    free(array);
}

void print_array(char * array, int size) {
    int i;
    for (i = 0; i < size; i++) {
        printf("%c ", array[i]);
    }
    putchar('\n');
}

void fill_array(char ** array, int n, int m) {
    int i, j;
    for (i = 0; i < n; i++) {
        for (j = 0; j < m; j++) {
            array[i][j] = (char) ('a' + (i+j)%26);
        }
    }
}

int main() {
    char **array;
    int n, m, i;

    printf("Enter two dimensional array size: \n");
    scanf("%d", &n);
    scanf("%d", &m);

    array = get_array(n, m);

    fill_array(array, n, m);
    for (i = 0; i < n; i++) {
        print_array(array[i], m);
    }

    free_array(array, n);

    return 0;
}