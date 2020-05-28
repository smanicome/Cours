#include "avl.h"
#include "bst.h"
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

node *rotate_right(node *y) {
    if (y == NULL)
        return y;

    node *x = y->left;
    y->left = x->right; /* B */
    x->right = y;       /* return the new root */

    update_height(y);
    update_height(x);

    return x;
}

node *rotate_left(node *y) {
    if (y == NULL)
        return y;

    node *x = y->right;
    y->right = x->left; /* B */
    x->left = y;       /* return the new root */

    update_height(y);
    update_height(x);

    return x;
}

node *rotate_left_right(node *t) {
    if (t == NULL)
        return t;

    t->left = rotate_left(t->left);
    return rotate_right(t);
}

node *rotate_right_left(node *t) {
    if (t == NULL)
        return t;

    t->right = rotate_right(t->right);
    return rotate_left(t);
}

node *insert_avl(node *t, int elt) {
    if (t == NULL) {
        t = create_node(elt);
        return t;
    }

    if (t->elt == elt) {
        return t;
    } else if (t->elt < elt) {
        t->right = insert_avl(t->right, elt);
    } else {
        t->left = insert_avl(t->left, elt);
    }

    update_height(t);
    t = rebalance(t);
    return t;
}

node *rebalance(node *t) {
    if (t == NULL)
        return t;

    if (compute_balance(t) == 2) {
        if (compute_balance(t->left) == -1) {
            t = rotate_left_right(t);
        } else {
            t = rotate_right(t);
        }
    } else if (compute_balance(t) == -2) {
        if (compute_balance(t->right) == 1) {
            t = rotate_right_left(t);
        } else {
            t = rotate_left(t);
        }
    }

    return t;
}

int compute_balance(node *t) {
    if (t == NULL)
        return 0;

    int h = 0;

    if (t->left != NULL) {
        h += t->left->height;
    }

    if (t->right != NULL) {
        h -= t->right->height;
    }

    return h;
}

int is_avl(node *t) {
    int b;
    if (t == NULL) {
        return 1;
    }

    if (height(t) != t->height) {
        return 0;
    }

    b = compute_balance(t);
    if (b < -1 || b > 1) {
        return 0;
    }

    return is_avl(t->left) && is_avl(t->right);
}

void update_height(node *t) {
    if (t == NULL)
        return;

    int lheight, rheight;

    lheight = t->left != NULL ? t->left->height + 1 : 0;
    rheight = t->right != NULL ? t->right->height + 1 : 0;

    t->height = lheight > rheight ? lheight : rheight;
}

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

