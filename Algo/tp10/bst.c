#include "bst.h"
#include "avl.h"
#include "visualtree.h"

#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <time.h>

node *find_bst(node *t, int elt) {
    if (t == NULL) {
        return NULL;
    }

    if (t->elt == elt) {
        return t;
    } else if (t->elt < elt) {
        return find_avl(t->right, elt);
    } else {
        return find_avl(t->left, elt);
    }
}

int height(node *t) {
    if (t == NULL) {
        return -1;
    }
    int left = height(t->left) + 1;
    int right = height(t->right) + 1;

    return left > right ? left : right;
}

/**
 * Permet de conserver le code récursive sans vérifier la présence de l'élément à chaque
 * récursion
 */

node *insert_bst(node *t, int elt) {
    if (t == NULL) {
        t = create_node(elt);
        return t;
    }

    if (t->elt == elt) {
        return t;
    } else if (t->elt < elt) {
        t->right = insert_bst(t->right, elt);
    } else {
        t->left = insert_bst(t->left, elt);
    }

    return t;
}

node *insert_n_random(node *t, int n) {
    int i;
    srand(time(NULL));
    
    for (i = 0; i < n; i++) {
        t = insert_bst(t, rand() % n);
    }

    return t;
}

node *remove_random(node *t, int n) {
    int i;
    srand(time(NULL));

    for (i = 0; i < n; i++) {
        t = remove_bst(t, rand() % 999);
    }

    return t;
}

node *extract_min_bst(node *t, node **min) {
    if (t == NULL) {
        return NULL;
    }

    if (t->left == NULL) {
        *min = t;
        return NULL;
    } else {
        t->left = extract_min_bst(t->left, min);
    }
    
    return t;
}

node *remove_bst(node *t, int elt) {
    if (t == NULL) {
        return NULL;
    }

    if (t->elt == elt) {
        node **n = (node **)malloc(sizeof(node *));
        node *right = t->right;

        extract_min_bst(t->left, n);

        while ((*n) != NULL) {
            insert_bst(t->right, (*n)->elt);
            free((*n));
            extract_min_bst(t->left, n);
        }

        free(t);
        free(n);
        return right;
    } else if (t->elt < elt) {
        t->right = remove_bst(t->right, elt);
    } else {
        t->left = remove_bst(t->left, elt);
    }

    return t;
}

node *menu(node *t) {
    node *n = NULL;
    int x;
    int res;

    char choice;

    do {
        printf("m) Voir ce menu\n");
        printf("s) Créer un arbre manuellement\n");
        printf("a) Créer un arbre automatiquement\n");
        printf("i) Insérer un noeud\n");
        printf("f) Trouver un noeud\n");
        printf("p) Afficher l'arbre\n");
        printf("q) Quitter\n\n");
        scanf(" %c", &choice);

        switch (choice) {
        case 'm':
            break;
        case 's':
            free_tree(t);
            t = NULL;

            t = scan_tree();
            break;
        case 'a':
            free_tree(t);
            t = NULL;

            res = scanf(" %d", &x);
            if (res == EOF || res == 0) {
                fflush(stdin);
                break;
            }

            t = insert_n_random(t, x);

            break;
        case 'i':
            res = scanf(" %d", &x);
            if (res == EOF || res == 0) {
                fflush(stdin);
                break;
            }

            if (n == NULL) {
                t = insert_avl(t, x);
            } else {
                n = insert_avl(n, x);
            }

            break;
        case 'r':
            res = scanf(" %d", &x);
            if (res == EOF || res == 0) {
                fflush(stdin);
                break;
            }

            if (n == NULL) {
                t = remove_bst(t, x);
            } else {
                n = remove_bst(n, x);
            }
        case 'f':
            res = scanf(" %d", &x);
            if (res == EOF || res == 0) {
                fflush(stdin);
                break;
            }

            n = find_bst(t, x);
            break;
        case 'p':
            display_infix(t);
            break;
        case 'q':
            printf("Terminé\n");
            break;
        default:
            printf("Il n'y a que sept(7) options\n");
        }

        write_tree(t);
    } while (choice != 'q');

    return t;
}