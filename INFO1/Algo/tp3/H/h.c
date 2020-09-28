#include <MLV/MLV_all.h>

void drawH(int x, int y, int size);

int main() {
    MLV_create_window( "H", "H", 1000, 1000);
    drawH(500, 500, 500);
    MLV_actualise_window();
    MLV_wait_seconds(20);
    MLV_free_window();
    return 0;
}

void drawH(int x, int y, int size) {
	if (size<8) {
		return;
	}

	MLV_draw_line(x-size/2, y, x+size/2, y, MLV_COLOR_BLUE);
	MLV_draw_line(x-size/2, y-size/2, x-size/2, y+size/2, MLV_COLOR_BLUE);
	MLV_draw_line(x+size/2, y-size/2, x+size/2, y+size/2, MLV_COLOR_BLUE);

	drawH(x-size/2, y+size/2, size/2);
	drawH(x-size/2, y-size/2, size/2);
	drawH(x+size/2, y+size/2, size/2);
	drawH(x+size/2, y-size/2, size/2);
}