#include "bst.h"
#include "tree.h"
#include "visualtree.h"
#include <stdio.h>
#include <stdlib.h>

int main(int argc, char const *argv[]) {
    if (argc < 2) {
        printf("Usage: %s <nodes quantity>\n", argv[0]);
    }

    node *t = NULL;
    int i, n;

    n = (int)strtol(argv[1], NULL, 10);
    /* menu(t); */

    t = insert_n_random(t, n);
    t = remove_random(t, 200);
    /*
		time ./tp09 4000000

        real    0m9,654s
        user    0m9,359s
        sys     0m0,116s
	*/

    /* for (i = 1; i <= n; i++) {
        t = insert_bst(t, i);
    } */

    /*	
		time ./tp09 50000

        real    0m8,947s
        user    0m8,899s
        sys     0m0,008s
	*/

    /* if (t != NULL) {
        printf("Nodes: %d\n", count_nodes(t));
        printf("Leaves: %d\n", count_leaves(t));
        printf("Only children: %d\n", count_only_children(t));
        printf("Height: %d\n", height(t) - 1);
    } */

    write_tree(t);
    free_tree(t);

    return 0;
}
