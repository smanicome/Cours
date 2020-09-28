#include "tree.h"
#include <assert.h>
#include <stdio.h>
#include <stdlib.h>

node *create_node(int data) {
    node *n = (node *)malloc(sizeof(node));
    assert(n != NULL);
    n->data = data;
    n->left = NULL;
    n->right = NULL;
    return n;
}

void free_tree(node *t) {
    if (t == NULL)
        return;

    if (t->left != NULL) {
        free_tree(t->left);
    }

    if (t->right != NULL) {
        free_tree(t->right);
    }

    free(t);
}

void display_prefix(node *t) {
    if (t == NULL)
        return;

    printf("%d ", t->data);

    if (t->left != NULL) {
        display_prefix(t->left);
    } else {
        printf("0 ");
    }

    if (t->right != NULL) {
        display_prefix(t->right);
    } else {
        printf("0 ");
    }
}

void display_infix(node *t) {
    if (t == NULL)
        return;

    if (t->left != NULL) {
        display_infix(t->left);
    } else {
        printf("0 ");
    }

    printf("%d ", t->data);

    if (t->right != NULL) {
        display_infix(t->right);
    } else {
        printf("0 ");
    }
}

void display_suffix(node *t) {
    if (t == NULL)
        return;

    if (t->left != NULL) {
        display_suffix(t->left);
    } else {
        printf("0 ");
    }

    if (t->right != NULL) {
        display_suffix(t->right);
    } else {
        printf("0 ");
    }

    printf("%d ", t->data);
}

int count_nodes(node *t) {
    if (t == NULL) {
        return 0;
    }

    return 1 + count_nodes(t->left) + count_nodes(t->right);
}

int count_leaves(node *t) {
    if (t == NULL) {
        return 0;
    } else if (t->left == NULL && t->right == NULL) {
        return 1;
    } else {
        return count_leaves(t->left) + count_leaves(t->right);
    }
}

int count_only_children(node *t) {
    if (t == NULL) {
        return 0;
    } else if (t->left == NULL && t->right != NULL) {
        return 1 + count_only_children(t->right);
    } else if (t->left != NULL && t->right == NULL) {
        return 1 + count_only_children(t->left);
    } else {
        return count_only_children(t->left) + count_only_children(t->right);
    }
}

int height(node *t) {
    if (t == NULL) {
        return 0;
    }
    int left = height(t->left);
    int right = height(t->right);

    return 1 + (left > right ? left : right);
}

node *scan_tree(void) {
    int x, res;
    node *t = NULL;

    res = scanf(" %d", &x);
    while (res == 0) {
        fflush(stdin);
        res = scanf(" %d", &x);
    }

    if (x != 0) {
        t = create_node(x);
        t->left = scan_tree();
        t->right = scan_tree();
    }

    return t;
}