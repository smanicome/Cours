#include <stdio.h>
#include <stdlib.h>
#include "node.h"
#include "encode.h"

void encode(FILE *fdin, FILE *fdout, char **code_table)
{
    int c;

    fseek(fdin, 0, SEEK_SET);
    while ((c = fgetc(fdin)) != EOF)
    {
        fprintf(fdout, "%s ", code_table[c]);
        fflush(fdin);
    }
}