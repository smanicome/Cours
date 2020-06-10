#ifndef __TAQUIN_H__
#define __TAQUIN_H__

#define NB_COL 4
#define NB_LIG 4
#define WINDOW_SIZE 512
#define SQUARE_SIZE WINDOW_SIZE/NB_COL

typedef struct carre {
	int lig; 
	int col;
} Carre;

typedef struct plateau {
    int empty_i;
    int empty_j;
    Carre bloc[NB_LIG][NB_COL];
} Plateau;

void initialisation_plateau(Plateau *p);

#endif