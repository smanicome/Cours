#include "queue.h"
#include "node.h"
#include <stdio.h>
#include <stdlib.h>

queue *create_queue() {
    queue *q = (queue *)malloc(sizeof(queue));
    if (q == NULL) {
        printf("Unable to allocate necessary memory\n");
        return NULL;
    }

    q->first = NULL;
    q->last = NULL;
    q->length = 0;
    return q;
}

link *create_link(node *n) {
    link *l = (link *)malloc(sizeof(link));
    l->leaf = n;
    l->next = NULL;
    return l;
}

void free_link(link *l) {
    free(l->leaf);
    free(l);
}

void add_queue(node *n, queue *q) {
    if (q->length == 0) {
        q->first = create_link(n);
        q->last = q->first;
    } else {
        q->last->next = create_link(n);
        q->last = q->last->next;
    }
    q->length++;
}

void free_queue(queue *q) {
    link *tmp = q->first;
    while (tmp != NULL) {
        q->first = q->first->next;
        free_link(tmp);
        tmp = q->first;
    }
    free(q);
}

node *remove_queue(queue *q) {
    if (q->length == 0)
        return NULL;
    link *l = q->first;
    node *n = l->leaf;
    q->first = q->first->next;
    free(l);
    return n;
}

void display_queue(queue *q) {
    link *l = q->first;
    printf("{ ");
    while (l != NULL) {
        printf("%d ", l->leaf->data);
        l = l->next;
    }
    printf("}\n");
}
