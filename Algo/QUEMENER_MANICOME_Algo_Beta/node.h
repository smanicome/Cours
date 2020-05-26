#ifndef __NODE_H__
#define __NODE_H__

/**
 * Node structure
 * data: holds the character
 * left: left node address
 * right: right node address
 * occurence: number of time it is used
 */
typedef struct _node {
    char data;
    struct _node *left;
    struct _node *right;
    int occurence;
} node;

/**
 * Create the interanl node
 */
node* create_o_node();

/**
 * Create data node
 */
node* create_leaf(char data);

#endif