#include "prioqueue.h"
#include <stdlib.h>
#include <stdio.h>

typedef struct _link {
    event *e;
    struct _link *next;
} link;

struct _prioqueue {
    link *first;
    int size;
};

prioqueue *create_pq() {
    prioqueue *q = (prioqueue *)malloc(sizeof(prioqueue));
    q->first = NULL;
    q->size = 0;
    return q;
}

void display_pq(prioqueue *pq) {
    link *l;
    for (l = pq->first; l != NULL; l = l->next) {
        printf("%d --> ", l->e->etime);
    }
    putchar('\n');
}

event *remove_min_pq(prioqueue *pq) {
    link *l = pq->first;
    event *e = l->e;

    pq->size--;
    if (pq->size == 0) {
        pq->first = NULL;
    } else {
        pq->first = l->next;
    }

    free(l);
    return e;
}

void insert_pq(prioqueue * pq, event* e) {
    link *l;
    link *previous_link = NULL;
    link *new_link = (link *)malloc(sizeof(link));

    new_link->e = e;
    new_link->next = NULL;

    if (pq->size == 0) {
        pq->first = new_link;
    } else {
        for (l = pq->first; l != NULL; l = l->next) {
            if (l->e->etime <= new_link->e->etime) {
                previous_link = l;
            } else {
                new_link->next = l;
                break;
            }
        }
        
        if (previous_link != NULL) {
            previous_link->next = new_link;
        } else {
            pq->first = new_link;
        }
    }

    pq->size++;
}

void free_pq(prioqueue *pq) {
    while(pq->size != 0) {
        event *e = remove_min_pq(pq);
        free(e->c);
        free(e);
    }
    free(pq);
}

int size_pq(prioqueue *pq) {
    return pq->size;
}