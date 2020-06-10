#include "play.h"
#include "move.h"
#include <stdio.h>
#include <MLV/MLV_all.h>

void print_board(Plateau *p) {
    int i, j;

    for (i = 0; i < NB_LIG; i++) {
        for (j = 0; j < NB_COL; j++) {
            printf("%d ", (p->bloc)[i][j].lig * NB_COL + (p->bloc)[i][j].col);
        }
        putchar('\n');
    }
}

void play(Plateau *p) {
    MLV_Image *image = MLV_load_image("assets/squirell.jpg");
    
    while(!won(p)) {
        clear_board();
        display_image_board(p, image);
        user_action(p);
    }

    display_image_board(p, image);
    MLV_free_image(image);
}

void clear_board() {
    MLV_draw_filled_rectangle(
        0,
        0,
        WINDOW_SIZE,
        WINDOW_SIZE,
        MLV_COLOR_BLACK);
    MLV_update_window();
}

void display_image_board(Plateau *p, MLV_Image *image) {
    int i, j;

    for (i = 0; i < NB_LIG; i++) {
        for (j = 0; j < NB_COL; j++) {
            if (i == p->empty_i && j == p->empty_j) {
                continue;
            }

            draw_image_tile((p->bloc)[i][j], i, j, image);
        }
    }

    MLV_actualise_window();
}

void draw_image_tile(Carre c, int lig, int col, MLV_Image *image) {
    int i, j;
    int r, g, b, a;
    MLV_Color color;

    r = 0;
    g = 0;
    b = 0;
    a = 0;

    for (i = 0; i < SQUARE_SIZE; i++) {
        for (j = 0; j < SQUARE_SIZE; j++) {
            MLV_get_pixel_on_image(
                image, 
                j + c.col * SQUARE_SIZE, 
                i + c.lig * SQUARE_SIZE, 
                &r, &g, &b, &a);

            color = MLV_convert_rgba_to_color(r, g, b, a);

            MLV_draw_pixel(
                j + col * SQUARE_SIZE, 
                i + lig * SQUARE_SIZE, 
                color);
        }
    }    
}

void display_board(Plateau *p) {
    int i, j;
    MLV_Font *font;
    char tile_text[3];
    font = MLV_load_font("font.ttf", FONT_SIZE);

    for (i = 0; i < NB_LIG; i++) {
        for (j = 0; j < NB_COL; j++) {
            int value = (p->bloc)[i][j].lig * NB_COL + (p->bloc)[i][j].col;

            MLV_draw_rectangle(
                j * SQUARE_SIZE,
                i * SQUARE_SIZE,
                SQUARE_SIZE,
                SQUARE_SIZE,
                MLV_COLOR_WHITE);

            if (i == p->empty_i && j == p->empty_j) {
                continue;
            }

            sprintf(tile_text, "%d", value);

            MLV_draw_text_with_font(
                j * SQUARE_SIZE + SQUARE_SIZE / 2 - FONT_SIZE / 2,
                i * SQUARE_SIZE + SQUARE_SIZE / 2 - FONT_SIZE / 2,
                tile_text, font,
                MLV_COLOR_WHITE);
        }
    }

    MLV_actualise_window();
    MLV_free_font(font);
}

int won(Plateau *p) {
    int i, j;

    for (i = 0; i < NB_LIG; i++) {
        for (j = 0; j < NB_COL; j++) {
            if ((p->bloc)[i][j].lig != i || (p->bloc)[i][j].col != j) {
                return 0;
            }
        }
    }

    return 1;
}

void user_action(Plateau *p) {
    int valid_input;
    int x, y;

    x = 0;
    y = 0;
    valid_input = 0;

    while (!valid_input) {
        MLV_wait_mouse(&x, &y);

        if (p->empty_j > 0 && (x >= (p->empty_j - 1) * SQUARE_SIZE) && (x < p->empty_j * SQUARE_SIZE)) {
            move(p, 0, -1);
            valid_input = 1;
        }

        if (p->empty_j < NB_COL - 1 && (x >= (p->empty_j + 1) * SQUARE_SIZE) && (x < (p->empty_j + 2) * SQUARE_SIZE)) {
            move(p, 0, 1);
            valid_input = 1;
        }

        if (p->empty_i > 0 && (y >= (p->empty_i - 1) * SQUARE_SIZE) && (y < p->empty_i * SQUARE_SIZE)) {
            move(p, -1, 0);
            valid_input = 1;
        }

        if (p->empty_i < NB_LIG - 1 && (y >= (p->empty_i + 1) * SQUARE_SIZE) && (y < (p->empty_i + 2) * SQUARE_SIZE)) {
            move(p, 1, 0);
            valid_input = 1;
        }
    }
}
