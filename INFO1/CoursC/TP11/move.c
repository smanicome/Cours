#include "move.h"
#include <stdlib.h>
#include <stdio.h>
#include <time.h>

void shuffle(Plateau *p) {
    int i;

    srand(time(NULL));

    for (i = 0; i < 120; i++) {
        random_move(p);
    }
}

void random_move(Plateau *p) {
    int random;

	random = rand() % 4;

    switch (random) {
	case 0:
		if (p->empty_i > 0) {
            move(p, -1, 0);
        }
        break;
    case 1:
		if (p->empty_i < NB_LIG - 1) {
            move(p, 1, 0);
        }
        break;
    case 2:
        if (p->empty_j > 0) {
            move(p, 0, -1);
        }
        break;
    case 3:
        if (p->empty_j < NB_COL - 1) {
            move(p, 0, 1);
        }
        break;
    default:
		break;
	}
}

void move(Plateau *p, int vertical, int horizontal) {
    Carre tmp_c;

    tmp_c = (p->bloc)[p->empty_i][p->empty_j];
    (p->bloc)[p->empty_i][p->empty_j] = (p->bloc)[p->empty_i + vertical][p->empty_j + horizontal];
    (p->bloc)[p->empty_i + vertical][p->empty_j + horizontal] = tmp_c;

    p->empty_i += vertical;
    p->empty_j += horizontal;
}