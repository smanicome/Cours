#include <stdlib.h>
#include <stdio.h>
#include <string.h>

char ** get_array(int argc, const char ** argv) {
    int i;
    char **array;

    array = (char **)malloc((argc -1) * sizeof(char *));
    for (i = 1; i < argc; i++) {
        array[i - 1] = malloc(strlen(argv[i]) + 1);
        strcpy(array[i - 1], argv[i]);
    }

    return array;
}

void free_array(char **array, int n) {
    int i;
    for (i = 0; i < n; i++) {
        free(array[i]);
    }

    free(array);
}

void print_array(char ** array, int size) {
    int i;
    for (i = 0; i < size; i++) {
        printf("%s ", array[i]);
    }
    putchar('\n');
}

int main(int argc, char const *argv[]) {
    int i;

    if (argc < 2) {
        printf("Usage: %s <string> ...\n", argv[0]);
    }

    char **array = get_array(argc, argv);
    print_array(array, argc - 1);
    free_array(array, argc - 1);

    return 0;
}
