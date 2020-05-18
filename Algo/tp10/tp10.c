#include "avl.h"
#include "bst.h"
#include "visualtree.h"
#include <stdio.h>
#include <stdlib.h>

int main() {

    node *t = create_node(10);
    write_tree(t);
    free_tree(t);

    return 0;
}
