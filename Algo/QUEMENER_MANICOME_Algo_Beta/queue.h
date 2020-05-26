#ifndef __QUEUE_H__
#define __QUEUE_H__

#include "node.h"

typedef struct _link
{
    node* leaf;
    struct _link* next;
} link;

typedef struct _queue
{
    link* first;
    link* last;
    int length;
} queue;

queue* create_queue();

void add_queue(node *n, queue *q);

void free_queue(queue *q);

node* remove_queue(queue *q);

void display_queue(queue* q);

#endif