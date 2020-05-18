#ifndef AVL_H
#define AVL_H

typedef struct _node {
    int elt;                 /* donnee stockee : un entier  */
    int height;              /* la hauteur de l'arbre       */
    struct _node *left;      /* pointeur sur le fils gauche */
    struct _node *right;     /* pointeur sur le fils droit  */
} node;

node *create_node(int elt);

void free_tree(node *t);

int is_avl(node *t);

node *find_avl(node *t, int elt);

node *insert_avl(node *t, int elt);

node *remove_avl(node *t, int elt);

node *scan_tree(void);

void display_prefix(node *t);

void display_infix(node *t);

void display_suffix(node *t);

#endif /* AVL_H */
