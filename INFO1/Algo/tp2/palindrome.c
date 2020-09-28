#include <string.h>

#include "palindrome.h"

int palindrome(char const *word) {
    return palindrome_rec(word, 0, strlen(word)-1); 
}

int palindrome_rec(char const *word, int lo, int hi) {
    if(lo >= hi) {
        return 1;
    }

    if (word[lo]==word[hi]) {
        return palindrome_rec(word, lo+1, hi-1);
    } else {
        return 0;
    }
}
