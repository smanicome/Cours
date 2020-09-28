#include "node.h"
#include "visualtree.h"
#include "encode.h"
#include <stdlib.h>
#include <stdio.h>

int main(int argc, char const *argv[])
{
    char *code_table[256] = {NULL};
    int frequencies[256] = {0};

    if (argc < 4)
    {
        printf("Usage: %s <tree_file_name> <in_file_name> <out_file_name>\n", argv[0]);
        return 1;
    }

    FILE *fd = fopen(argv[1], "r");
	if (fd == NULL)
    {
        perror("fopen");
        return 2;
    }

    FILE *fdin = fopen(argv[2], "r");
    if (fdin == NULL)
    {
        perror("fopen");
        return 2;
    }

    FILE *fdout = fopen(argv[3], "w");
    if (fdout == NULL)
    {
        perror("fopen");
        return 2;
    }

    fill_frequencies(fdin, frequencies);
    /* node *t = create_huffman(frequencies); */

    node *t = scan_tree(fd);
    fill_code_table(t, NULL, 0, code_table);

    encode(fdin, fdout, code_table);

    fclose(fd);
    fclose(fdin);
    fclose(fdout);

    write_tree(t);
    free_node(t);
    free_code_table(code_table);
    return 0;
}