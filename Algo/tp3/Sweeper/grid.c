#include "grid.h"
#include "draw.h"
#include <stdlib.h>
#include <stdio.h>
#include <time.h>

/*
 * Allocate memory for a grid and initialize each cell.
 */
grid *create_grid(int x_size, int y_size) {
	int i, j;
	grid *g = (grid *)malloc(sizeof(grid));
	g->x_size = x_size;
	g->y_size = y_size;
	g->cells = (cell **)malloc(x_size * sizeof(cell *));
	for (i = 0; i < x_size; i++)
		g->cells[i] = (cell *)malloc(y_size * sizeof(cell));

	for (j = 0; j < y_size; j++)
		for (i = 0; i < x_size; i++) {
			g->cells[i][j].x_pos = i;
			g->cells[i][j].y_pos = j;
			g->cells[i][j].visible = 0;
			g->cells[i][j].marked = 0;
			g->cells[i][j].mine = 0;
			g->cells[i][j].mine_count = 0;
		}

	return g;
}

/*
 * Free memory for a grid.
 */
void free_grid(grid *g) {
	int i;
	for (i = 0; i < g->x_size; i++)
		free(g->cells[i]);
	free(g->cells);
	free(g);
}

/*
 * Set all cells to visible (for debugging).
 */
void set_all_visible(grid *g) {
	int x, y;
	for (x = 0; x < g->x_size; x++)
		for (y = 0; y < g->y_size; y++)
			g->cells[x][y].visible = 1;
}

/*
 * Add exactly n mines to grid g in random positions.
 */
void add_mines(grid *g, int n) {
	size_t i;
	srand((unsigned int)time(NULL));

	for (i = 0; i < n; i++) {
		int randx = rand() % (g->x_size);
		int randy = rand() % (g->y_size);
		
		while (g->cells[randx][randy].mine) {
			randx = rand() % (g->x_size);
			randy = rand() % (g->y_size);
		}
		g->cells[randx][randy].mine = 1;

		if (randx > 0) {
			if (randy > 0)
				g->cells[randx - 1][randy - 1].mine_count++;

			g->cells[randx - 1][randy].mine_count++;

			if (randy < (g->y_size)-1)
				g->cells[randx - 1][randy + 1].mine_count++;
		}

		if (randy > 0)
			g->cells[randx][randy - 1].mine_count++;

		g->cells[randx][randy].mine_count++;

		if (randy < (g->y_size)-1)
			g->cells[randx][randy + 1].mine_count++;

		if (randx < (g->x_size)-1) {
			if (randy > 0)
				g->cells[randx + 1][randy - 1].mine_count++;

			g->cells[randx + 1][randy].mine_count++;

			if (randy < (g->y_size)-1)
				g->cells[randx + 1][randy + 1].mine_count++;
		}
	}
	return;
}

/*
 * Uncover cell c in grid g.
 * Return the total number of cells uncovered.
 */
int uncover(grid *g, cell *c) {
	int somme = 0;
	if(c->visible || c->marked) {
		return 0;
	}
	c->visible = 1;
	int x = c->x_pos;
	int y = c->y_pos;
	
	draw_cell_actualise_window(c);

	if (!c->mine_count) {
		if (x > 0) {
			if (y > 0) 
				somme += uncover(g, &(g->cells[x - 1][y - 1]));

			somme += uncover(g,&(g->cells[x - 1][y]));

			if (y < (g->y_size)-1)
				somme += uncover(g, &(g->cells[x - 1][y + 1]));
		}

		if (y > 0)
			somme += uncover(g, &(g->cells[x][y - 1]));

		if (y < (g->y_size)-1)
			somme += uncover(g, &(g->cells[x][y + 1]));

		if (x < (g->x_size)-1) {
			if (y > 0)
				somme += uncover(g, &(g->cells[x + 1][y - 1]));

			somme += uncover(g, &(g->cells[x + 1][y]));

			if (y < (g->y_size)-1)
				somme += uncover(g, &(g->cells[x + 1][y + 1]));
		}
	}
	
	return somme+1;
}
