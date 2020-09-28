#ifndef __TREE_H__
#define __TREE_H__

#include <stdio.h>
#include "node.h"

#define STR_SIZE 256

/**
 * Create a table of string of given size
 * Each string is at most 20 characters
 */
char** create_string_table(int number);

/**
 * Create a table of int of given size
 */
int* create_int_table(int number);

void free_tree(node* t);

void free_table(char** tab, int length );

node* parse_tree(FILE* file);

char **export_tree(node* n, char** table);

void encode(char** tab, FILE* fdin, FILE* fdout);

void get_occurrences(FILE *fdin, int *tab);

#endif