#ifndef __NODE_H__
#define __NODE_H__

#include <stdio.h>

typedef struct node_struct
{
    struct node_struct *left;
    struct node_struct *right;
    int freq;
    unsigned char dat;
} node;

node* create_node(unsigned char dat, int freq);

void free_node(node* node);

int node_height(node* node);

void print_tree(node* node);

node *scan_tree(FILE *fd);

void fill_code_table(node *n, char *path, int size, char **code_table);

void free_code_table(char **code_table);

void fill_frequencies(FILE *fd, int *frequencies);

node *create_huffman(int *frequencies);

#endif