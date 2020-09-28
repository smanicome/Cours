#include "taquin.h"
#include "move.h"
#include "play.h" 
#include <MLV/MLV_all.h>
#include <stdlib.h>

void initialisation_plateau(Plateau *p) {
    int i, j;
    for(i = 0; i < NB_LIG; i++) {
        for(j = 0; j < NB_COL; j++) {
            ((p->bloc)[i][j]).lig = i;
            ((p->bloc)[i][j]).col = j;
        }
    }

    p->empty_i = NB_LIG - 1;
    p->empty_j = NB_COL - 1;
}

int main() {
    Plateau *p = (Plateau *)malloc(sizeof(Plateau));
    initialisation_plateau(p);
    MLV_create_window("Taquin", "taquin", WINDOW_SIZE, WINDOW_SIZE);
    
	shuffle(p);
    play(p);

    MLV_wait_seconds(10);
    MLV_free_window();
    return 0;
}

