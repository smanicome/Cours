#include "queue.h"
#include "node.h"
#include <stdio.h>
#include <stdlib.h>

void display_q(queue *q)
{
    link *l;
    for (l = q->first; l != NULL; l = l->next)
    {
        printf("%d --> ", l->n->dat);
    }
    putchar('\n');
}

node *dequeue_q(queue *q)
{
    link *l = q->first;
    node *n = l->n;
    
    q->size--;
    if (q->size == 0)
    {
        q->first = NULL;
        q->last = NULL;
    }
    else
    {
        q->first = l->next;
    }

    free(l);
    return n;
}

void enqueue_q(queue *q, node *n)
{
    link *new_link = (link *) malloc(sizeof(link));
    new_link->n = n;
    new_link->next = NULL;
    
    if (q->size == 0)
    {
        q->first = new_link;
    }
    else
    {
        link *i = NULL;
        link *previous = NULL;
        for (i = q->first; i != NULL && i->n->freq < n->freq; previous = i, i = i->next)
        {
        }

        if (i == NULL)
        {
            q->last = new_link;
            previous->next = new_link;
        }
        else
        {
            if (previous == NULL)
            {
                q->first = new_link;
            }
            else
            {
                previous->next = new_link;
            }

            new_link->next = i;
        }
    }
    q->size++;
}

queue *create_q()
{
    queue *q = (queue *)malloc(sizeof(queue));
    q->first = NULL;
    q->last = NULL;
    q->size = 0;
    return q;
}

void free_q(queue *q)
{
    while (q->size != 0)
    {
        free(dequeue_q(q));
    }
    free(q);
}

int size_q(queue *q)
{
    return q->size;
}