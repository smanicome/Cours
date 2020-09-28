#ifndef __PLAY_H__
#define __PLAY_H__

#include "taquin.h"
#include <MLV/MLV_all.h>
#define FONT_SIZE 24

void play(Plateau *p);
void clear_board();
void display_image_board(Plateau *p, MLV_Image *image);
void draw_image_tile(Carre c, int lig, int col, MLV_Image *image);
void display_board(Plateau *p);
int won(Plateau *p);
void user_action(Plateau *p);

#endif