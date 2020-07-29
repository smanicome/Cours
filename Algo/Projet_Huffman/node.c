#include "node.h"
#include "queue.h"
#include <stdlib.h>
#include <stdio.h>
#include <string.h>

#define max(a,b) (((a)>(b))?(a):(b))

/*allocates and initializes a node*/
node* create_node(unsigned char dat, int freq)
{
    node* node_obj = malloc(sizeof(node));

    if (node_obj == NULL)
    {
        printf("out of memory\n");
        fflush(stdout);
    } 
    else
    {
        node_obj->left = NULL;
        node_obj->right = NULL;
        node_obj->freq = freq;
        node_obj->dat = dat;
    }
    return node_obj;
}

/*delete and free node*/
void free_node(node* n)
{
    if (n == NULL)
    {
        return;
    }

    free_node(n->left);
    free_node(n->right);
    free(n);
}

/*return nodes height*/
int node_height(node* n)
{
	if(n->left != NULL || n->right != NULL)
	{
		/*calcuate left and right nodeHeight*/
		int leftHeight = n->left ? node_height(n->left) : 0;                
		int rightHeight = n->right ? node_height(n->right) : 0;

		/*return highest one*/
		return max(leftHeight,rightHeight) + 1;
	}
	return 0;
}

node *find_node(node *n, unsigned char c)
{
    if (n == NULL)
    {
        return NULL;
    }
    
    if (n->dat == c)
    {
        return n;
    }

    node *left = find_node(n->left, c);
    if (left != NULL)
    {
        return left;
    }
    
    node *right = find_node(n->right, c);
    if (right != NULL)
    {
        return right;
    }

    return NULL;
}

void print_tree(node* node)
{
	/*TODO*/
}

void fill_code_table(node *n, char *path, int size, char **code_table)
{
    if (n == NULL)
    {
        return;
    }

    if (path == NULL)
    {
        path = "";
    }
    
    
    if (n->dat != 0)
    {
        code_table[n->dat] = path;
    }
    else
    {
        if (n->left != NULL)
        {
            char *left = (char *)malloc((size + 1) * sizeof(char));
            strcpy(left, path);
            strcat(left, "0");
            fill_code_table(n->left, left, size + 1, code_table);
        }
        
        if (n->right != NULL)
        {
            char *right = (char *)malloc((size + 1) * sizeof(char));
            strcpy(right, path);
            strcat(right, "1");
            fill_code_table(n->right, right, size + 1, code_table);
        }
    }

    return;
}

void free_code_table(char **code_table)
{
    int i;
    
    for (i = 0; i < 256; i++)
    {
        if (code_table[i] != NULL)
        {
            free(code_table[i]);
        }
    }
}

node *scan_tree(FILE *fd)
{
    int c;
    node *t = NULL;

    if ((c = fgetc(fd)) != EOF)
    {
        return NULL;
    }

    if (c == '0')
    {
        if ((c = fgetc(fd)) != EOF)
        {
            return NULL;
        }

        t = create_node(c, 0);
    }
    else if (c == '1')
    {
        t = create_node(0, 0);

        t->left = scan_tree(fd);
        t->right = scan_tree(fd);
    }

    return t;
}

/*Reads the file and fills the frequency table (size 256)*/
void fill_frequencies(FILE *f, int *frequencies)
{
    int c;
    fseek(f, 0, SEEK_SET);
    while ((c = fgetc(f)) != EOF)
    {
        frequencies[c]++;
    }
}

node *create_huffman(int *frequencies)
{
    node *n;
    node *t;
    queue *q = create_q();
    int i;
    
    for (i = 0; i < 256; i++)
    {
        if (frequencies[i] > 0)
        {
            n = (node *)malloc(sizeof(node));
            n->dat = (unsigned char)i;
            n->freq = frequencies[i];

            printf("OK3\n");

            enqueue_q(q, n);
            printf("OK4\n");
        }
    }

    printf("size: %d\n", q->size);

    while(q->size > 0)
    {
        printf("OK6\n");

        t = (node *)malloc(sizeof(node));
        node *left = dequeue_q(q);
        printf("OK7\n");
        node *right = dequeue_q(q);
        printf("OK8\n");

        t->freq = left->freq + right->freq;
        printf("OK7\n");

        enqueue_q(q, t);
    }

    return t;
}
