#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "tree.h"
#include "queue.h"

#include "node.h"

#include "visualtree.h"

#define NB_CASES 256

int main(int argc, char **argv) {
    if (argc < 2) {
        fprintf(stderr, "Usage : main <in_file> <out_file> \n");
        return 1;
    }

    FILE* fdin = fopen(argv[1], "r");
    FILE* fdout = fopen(argv[2], "w");

    if (fdin == NULL) {
        perror("fopen");
        return 2;
    }

    if (fdout == NULL) {
        perror("fopen");
        return 3;
    }

    /*2.1*/

    node* t = parse_tree(fdin);
    fclose(fdin);
    write_tree(t);

    char** code_table = create_string_table(NB_CASES);
    code_table = export_tree(t, code_table);
    
    /*2.2*/
    printf("code_table[97] = %s\n",code_table[97]); 
    printf("code_table[98] = %s\n",code_table[98]);
    printf("code_table[99] = %s\n",code_table[99]);
    printf("code_table[100] = %s\n",code_table[100]);
    printf("code_table[114] = %s\n",code_table[114]);
    
    char str[] = "test";
    int i, len = strlen(str);
    for(i=0; i < len;i++) {
        printf("%s ", code_table[ (unsigned char) str[i] ]);
    }

    putchar('\n');

    /*2.3*/
    FILE *test = fopen("test.txt", "r");
    encode(code_table, test, fdout);
    fclose(test);
    fclose(fdout);

    /* PARTIE 3 */    
    int* hash_tab = create_int_table(NB_CASES);
    
    test = fopen("test.txt", "r");
    get_occurrences(test, hash_tab);
    fclose(test);

    /*3.1*/
    printf("hash_tab[97] = %d\n", hash_tab[97]); 
    printf("hash_tab[98] = %d\n", hash_tab[98]);
    printf("hash_tab[99] = %d\n", hash_tab[99]);
    printf("hash_tab[100] = %d\n", hash_tab[100]);
    printf("hash_tab[114] = %d\n", hash_tab[114]);

    /*3.2*/

    queue *q = create_queue();

    node *l1 = create_leaf('m');
    node *l2 = create_leaf('d');
    node *l3 = create_leaf('r');

    add_queue(l1, q);
    add_queue(l2, q);
    add_queue(l3, q);

    display_queue(q);

    node* test_node = remove_queue(q);
    printf("Removed node : %d \n", test_node->data );
    display_queue(q);
    free(test_node);

    free_tree(t);
    free_queue(q);
    free_table(code_table, NB_CASES);
    free(hash_tab);

    
    return 0;
}