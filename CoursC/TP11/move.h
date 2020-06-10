#ifndef __MOVE_H__
#define __MOVE_H__

#include "taquin.h"

void shuffle(Plateau *p);
void random_move(Plateau *p);
void move(Plateau *p, int vertical, int horizontal);

#endif