#include "queue.h"
#include <stdio.h>
#include <stdlib.h>

typedef struct _link {
    customer *c;
    struct _link *next;
} link;

struct _queue {
    link *first;
    link *last;
    int size;
};

void display_q(queue *q) {
    link *l;
    for (l = q->first; l != NULL; l = l->next) {
        printf("%d --> ", l->c->atime);
    }
    putchar('\n');
}

customer *dequeue_q(queue *q) {
    link *l = q->first;
    customer *c = l->c;
    
    q->size--;
    if (q->size == 0) {
        q->first = NULL;
        q->last = NULL;
    } else {
        q->first = l->next;
    }

    free(l);
    return c;
}

void enqueue_q(queue *q, customer *c) {
    link *new_link = (link *) malloc(sizeof(link));
    new_link->c = c;
    new_link->next = NULL;
    if (q->size == 0) {
        q->first = new_link;
    } else {
        q->last->next = new_link;
    }

    q->last = new_link;
    q->size++;
}

queue *create_q() {
    queue *q = (queue *)malloc(sizeof(queue));
    q->first = NULL;
    q->last = NULL;
    q->size = 0;
    return q;
}

void free_q(queue *q) {
    while (q->size != 0) {
        free(dequeue_q(q));
    }
    free(q);
}

int size_q(queue *q) {
    return q->size;
}