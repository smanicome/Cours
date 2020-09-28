#ifndef BST_H
#define BST_H

#include "avl.h"

int height(node *t);
node *find_bst(node *t, int elt);
node *insert_bst(node *t, int elt);
node *insert_n_random(node *t, int n);
node *extract_min_bst(node *t, node **min);
node *remove_bst(node *t, int elt);
node *remove_random(node *t, int n);
node *menu(node *t);

#endif