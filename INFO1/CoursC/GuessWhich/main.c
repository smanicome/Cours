#include <ctype.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define MAX_LENGTH 5

void ask_difficulty(int* difficulty);
unsigned int random_generation(int difficulty);
void make_him_guess(int difficulty);

int main() {
  int difficulty;

  printf("Welcome to the most boring game in the world ! Known as guessing a number.\n");
  ask_difficulty(&difficulty);
  make_him_guess(difficulty);

  return 0;
}

void ask_difficulty(int* difficulty) {

  printf("Enter the maximum value to guess (between 0 and 99999):\n");

  scanf("%d", difficulty);
  return;
}

unsigned int random_generation(int difficulty) {
  srand(time(NULL));
  return rand() % difficulty + 1;
}

void make_him_guess(int difficulty) {
  unsigned int guess;
  unsigned int nb_guess = 0;
  int won = 0;
  unsigned int answer = random_generation(difficulty);

  printf("Guess it !\n");
  while (!won) {
    while (scanf("%d", &guess) != 1) {
      printf("Please enter a number between 0 and %d.\n", difficulty);
    }

    won = guess == answer;
    if (!won)
      printf("Too bad... Try again !\n");

    nb_guess++;
  }
  printf("Congrats, you found it in %u attempts!\n", nb_guess);

  return;
}