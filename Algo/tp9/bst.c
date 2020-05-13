#include "bst.h"
#include "visualtree.h"

#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <time.h>

node *find_bst(node *t, int elt) {
    if (t == NULL) {
        return NULL;
    }

    if (t->data == elt) {
        return t;
    } else {

        node *left = find_bst(t->left, elt);
        node *right = find_bst(t->right, elt);
        
        if (left != NULL) {
            return left;
        } else if (right != NULL) {
            return right;
        } else {
            return NULL;
        }
    }
}

/**
 * Permet de conserver le code récursive sans vérifier la présence de l'élément à chaque
 * récursion
 */
node *insert_bst_wrapper(node *t, int elt) {
    if (find_bst(t, elt) != NULL) {
        return t;
    } else {
        t = insert_bst(t, elt);
    }

    return t;
}

node *insert_bst(node *t, int elt) {
    if (t == NULL) {
        t = create_node(elt);
        return t;
    }

    int height_left = height(t->left) - 1;
    int height_right = height(t->right) - 1;

    if (height_left < height_right) {
        t->left = insert_bst(t->left, elt);
    } else {
        t->right = insert_bst(t->right, elt);
    }

    return t;
}

node *insert_n_random(node *t, int n) {
    int i;
    srand(time(NULL));
    
    for (i = 0; i < n; i++) {
        t = insert_bst_wrapper(t, rand() % (2 * n));
    }

    return t;
}

void menu(node *t) {
    node *n = NULL;
    int i;
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

            for (i = 0; i < x; i++)
                t = insert_bst(t, i);

            break;
        case 'i':
            res = scanf(" %d", &x);
            if (res == EOF || res == 0) {
                fflush(stdin);
                break;
            }

            if (n == NULL) {
                t = insert_bst(t, x);
            } else {
                n = insert_bst(n, x);
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
}