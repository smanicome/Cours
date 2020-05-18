#include "avl.h"
#include <stdio.h>
#include <stdlib.h>
#include <assert.h>

node *create_node(int elt) {
    node *n = (node *)malloc(sizeof(node));
    assert(n != NULL);
    n->elt = elt;
    n->height = 0;
    n->left = NULL;
    n->right = NULL;
    return n;
}

void free_tree(node *t) {
    if (t != NULL) {
        free_tree(t->left);
        free_tree(t->right);
        free(t);
    }
}

/* SEARCH */

node *find_avl(node *t, int elt) {
    node *ptr = t;
    while (ptr != NULL && ptr->elt != elt) {
        if (ptr->elt > elt)
            ptr = ptr->left;
        else
            ptr = ptr->right;
    }
    return ptr;
}

void display_prefix(node *t) {
    if (t == NULL)
        return;

    printf("%d ", t->elt);

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

    printf("%d ", t->elt);

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

    printf("%d ", t->elt);
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

