#include <stdio.h>
#include <stdlib.h>
#include "node.h"

node *create_node(char data) {
    node *n = (node *)malloc(sizeof(node));
    if (n == NULL) {
        printf("Unable to allocate necessary memory\n");
        return NULL;
    }

    n->left = NULL;
    n->right = NULL;
    n->occurence = 0;
    n->data = data;
    
    return n;
}

node *create_o_node() {
    return create_node(0);
}

node *create_leaf(char data) {
    return create_node(data);
}
