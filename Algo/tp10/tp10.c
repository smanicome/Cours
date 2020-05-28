#include "avl.h"
#include "bst.h"
#include "visualtree.h"
#include <stdio.h>
#include <stdlib.h>

int main(int argc, char *argv[]) {
    if (argc < 2) {
        printf("Usage: %s <number above 0>", argv[0]);
        exit(1);
    }

    int n = (int)strtol(argv[1], NULL, 10);

    /* node *t = NULL;
    t = menu(t);

    printf("value: %d\n", t->elt);
    printf("height: %d\n", t->height); */

    /* node *t = NULL;
    int i;
    for (i = 0; i < 100; i++) {
        t = insert_avl(t, i);
        if (!is_avl(t)) {
            printf("The tree is not balanced\n");
            write_tree(t);
            exit(-1);
        }
    }
    printf("Ok!\n"); */

    node *t = NULL;

    /* t = insert_n_random(t, n);
    time ./tp10 6000000

    real    0m10,002s
    user    0m9,898s
    sys     0m0,084s
    */
    /*
			int i;
			for (i = 0; i < n; i++) {
					t = insert_avl(t, i);
			}
			
			time ./tp10 13700000

			real    0m10,007s
			user    0m9,845s
			sys     0m0,160s
		*/
    free_tree(t);

    return 0;
}
