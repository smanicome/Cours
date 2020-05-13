#include "bst.h"
#include "tree.h"
#include "visualtree.h"
#include <stdlib.h>
#include <stdio.h>

int main(int argc, char const *argv[]) {
	if (argc < 2) {
        printf("Usage: %s <nodes quantity>\n", argv[0]);
    }

    node *t = NULL;
    int i, n;

    n = (int)strtol(argv[1], NULL, 10);
    /* menu(t); */

    /* 
		t = insert_n_random(t, n);

		time ./tp09 36000

		real    0m9,727s
		user    0m9,647s
		sys     0m0,008s 
	*/

    /* 
		for (i = 0; i < n; i++) {
			t = insert_bst(t, n);
		} 
		
		time ./tp09 39000

		real    0m9,963s
		user    0m9,797s
		sys     0m0,016s
	*/

    /* if (t != NULL) {
        printf("Nodes: %d\n", count_nodes(t));
        printf("Leaves: %d\n", count_leaves(t));
        printf("Only children: %d\n", count_only_children(t));
        printf("Height: %d\n", height(t) - 1);
    } */

        free_tree(t);

    return 0;
}
