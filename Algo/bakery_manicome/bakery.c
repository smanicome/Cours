#include "customer.h"
#include "event.h"
#include "prioqueue.h"
#include "queue.h"
#include <math.h>
#include <stdlib.h>
#include <stdio.h>

#define N_VENDORS 2
#define CLOSING_TIME 10000
#define ARRIVAL_RATE (1.0 / 60)
#define MEAN_SERVICE_TIME 500

prioqueue *event_queue;
queue *customer_queue;
customer *vendor[N_VENDORS];
int current_time;
int customer_count;
int customer_presence_time;

double normal_delay(double mean) {
    return -mean * log(1 - ((double) rand() / RAND_MAX));
}

void add_customer(customer *c) {
    size_t i;
    for (i = 0; i < N_VENDORS; i++) {
        if (vendor[i] == NULL) {
            vendor[i] = c;
            event *e = create_departure(current_time + normal_delay(MEAN_SERVICE_TIME), c);
            insert_pq(event_queue, e);
            return;
        }
    }

    enqueue_q(customer_queue, c);
}

void remove_customer(customer *c) {
    size_t i;
    for (i = 0; i < N_VENDORS; i++) {
        if (vendor[i] == c) {
            vendor[i] = NULL;
            break;
        }
    }

    free_customer(c);

    if (size_q(customer_queue) > 0) {
        vendor[i] = dequeue_q(customer_queue);
        event *e = create_departure(current_time + normal_delay(MEAN_SERVICE_TIME), vendor[i]);
        insert_pq(event_queue, e);
    }
}

void process_arrival(event *e) {
    int time;
    add_customer(e->c);

    time = current_time + normal_delay(1.0/ARRIVAL_RATE);
    customer *c = create_customer(time);
    event *e1 = create_arrival(time, c);
    insert_pq(event_queue, e1);
}

void process_departure(event *e) {
    customer_count++;
    customer_presence_time += e->etime - e->c->atime;
    remove_customer(e->c);
}

void print_state() {
    size_t i;
    printf("%d ", current_time);

    printf("| ");
    for (i = 0; i < N_VENDORS; i++) {
        if (vendor[i] == NULL) {
            putchar('_');
        } else {
            putchar('X');
        }
        putchar(' ');
    }
    printf("| ");

    for (i = 0; i < size_q(customer_queue); i++) {
        putchar('X');
    }
    putchar('\n');
}

int main() {
    int i;
    current_time = 0;
    customer_count = 0;
    customer_presence_time = 0;

    event_queue = create_pq();
    customer_queue = create_q();
    for (i = 0; i < N_VENDORS; i++) {
        vendor[i] = NULL;
    }

    /* enqueue_q(customer_queue, create_customer(42));
  customer *c = dequeue_q(customer_queue);
  printf("Arrival time: %d\n", c->atime); */
    customer *c = create_customer(current_time);
    event *e = create_arrival(current_time, c);
    insert_pq(event_queue, e);

    while (size_pq(event_queue) > 0) {
        event *e = remove_min_pq(event_queue);
        current_time = e->etime;

        /* display_q(customer_queue); */
        /* display_pq(event_queue); */
        printf("event time --> %d\n", e->etime);
        printf("event type --> %d\n", e->type);

        if (e->type) {
            process_departure(e);
        } else {
            process_arrival(e);
        }

        free_event(e);
        
        /* display_q(customer_queue); */
        /* display_pq(event_queue); */

        print_state();

        if (current_time >= CLOSING_TIME) {
            break;
        }
    }

    printf("\nEND OF SERVICE\n");

    free_q(customer_queue);
    free_pq(event_queue);
    for (i = 0; i < N_VENDORS; i++) {
        if (vendor[i] != NULL) {
            free(vendor[i]);
        }
    }

    printf("\nCUSTOMER COUNT = %d\n", customer_count);
    printf("\nTOTAL SERVICE TIME = %d\n", customer_presence_time);
    printf("\nMEAN SERVICE TIME = %d\n", customer_presence_time / customer_count);
    return 0;
}