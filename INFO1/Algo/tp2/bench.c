#include <stdlib.h>
#include <stdio.h>

#include "palindrome.h"
#include "sequence.h"
#include "occurence.h"
#include "sum.h"


int main() {
    int result;

    printf("----- Test de palindrome -----\n");
    printf("Tests for a, aba, abba, radar, esoperesteetserepose, msi\n\n");
    if (palindrome("a") 
        && palindrome("aba") 
        && palindrome("abba")
        && palindrome("radar")
        && palindrome("esoperesteetserepose")
        && !palindrome("msi")) {
        printf("PASSED\n");
    } else {
        printf("FAILED\n");
    }
    putchar('\n');
    putchar('\n');

    printf("----- Test de séquences -----\n");
    printf("Tests for n=5, n=10, n=20\n\n");

    printf("Decreasing sequence\n");
    increasing_sequence_rec(5);
    putchar('\n');
    increasing_sequence_rec(10);
    putchar('\n');
    increasing_sequence_rec(20);
    putchar('\n');
    putchar('\n');

    printf("Increasing sequence\n");
    decreasing_sequence_rec(5);
    putchar('\n');
    decreasing_sequence_rec(10);
    putchar('\n');
    decreasing_sequence_rec(20);
    putchar('\n');
    putchar('\n');

    int t1[] = {1,4,5,8,9,6,1,4,1,6,7,2,3};
    int t2[] = {1,4,9,8,7,6,4,5,9,2,3,1,4,5,6,1,5,4,7,1,4,6,3,2,5,8,7};
    int t3[] = {1,4,9,1,8,1,7,6,4,5,9,1,2,3,1,4,5,6,1,5,4,7,1,4,1,4,6,3,1,2,5,8,7,1};

    printf("----- Test d'occurences -----\n");
    printf("Tests count\n\n");
    
    result = count(t1, 0, 14, 4);
    printf("Expected 2 got %d ", result);
    if(result==2) {
        printf("PASSED\n");
    } else {
        printf("FAILED\n");
    }

    result = count(t2, 0, 28, 4);
    printf("Expected 5 got %d ", result);
    if(result==5) {
        printf("PASSED\n");
    } else {
        printf("FAILED\n");
    }

    result = count(t3, 0, 34, 4);
    printf("Expected 6 got %d ", result);
    if(result==6) {
        printf("PASSED\n");
    } else {
        printf("FAILED\n");
    }

    printf("\nTests max_count\n\n");
    
    result = max_count(t1, 0, 14);
    printf("Expected 3 got %d ", result);
    if(result==3) {
        printf("PASSED\n");
    } else {
        printf("FAILED\n");
    }

    result = max_count(t2, 0, 28);
    printf("Expected 5 got %d ", result);
    if(result==5) {
        printf("PASSED\n");
    } else {
        printf("FAILED\n");
    }

    result = max_count(t3, 0, 34);
    printf("Expected 10 got %d ", result);
    if(result==10) {
        printf("PASSED\n");
    } else {
        printf("FAILED\n");
    }
    putchar('\n');
    
    printf("----- Test de somme -----\n");
    printf("912942\n");
    result = sum_digits_iter(912942);
    printf("iterative sum %d\n", result);
    if (result==27) {
        printf("PASSED\n\n");
    } else {
        printf("FAILED\n\n");
    }

    printf("912942\n");
    result = sum_digits_rec(912942);
    printf("recursive sum %d\n", result);

    if (result==27) {
        printf("PASSED\n\n");
    } else {
        printf("FAILED\n\n");
    }
    
    printf("----- Test de chiffre de somme -----\n");
    result = digit_sum_digits_iter(912942);
    printf("Iterative result: %d\n", result);
    if (result==9) {
        printf("PASSED\n\n");
    } else {
        printf("FAILED\n\n");
    }

    result = digit_sum_digits_rec(912942);
    printf("Recursive result: %d\n", result);
    if (result==9) {
        printf("PASSED\n\n");
    } else {
        printf("FAILED\n\n");
    }

    printf("----- Test de sequence croissante -----\n");
    int t4[] = {1, 2, 3, 2, 4, 6, 8, 3};
    int t5[] = {1, 2, 3, 1, 2, 3, 4, 5};
    int t6[] = {1, 3, 5, 2, 3, 4, 5, 3};

    printf("Tests longest_incr_iter\n");

    result = longest_incr_iter(t4, 0, 8);
    printf("Expected 4 got %d ", result);
    if (result==4) {
        printf("PASSED\n\n");
    } else {
        printf("FAILED\n\n");
    }

    result = longest_incr_iter(t5, 0, 8);
    printf("Expected 5 got %d ", result);
    if (result==5) {
        printf("PASSED\n\n");
    } else {
        printf("FAILED\n\n");
    }

    result = longest_incr_iter(t6, 0, 8);
    printf("Expected 4 got %d ", result);
    if (result==4) {
        printf("PASSED\n\n");
    } else {
        printf("FAILED\n\n");
    }

    printf("Tests first_incr\n");

    result = first_incr(t4, 0, 8);
    printf("Expected 3 got %d ", result);
    if (result==3) {
        printf("PASSED\n\n");
    } else {
        printf("FAILED\n\n");
    }

    result = first_incr(t5, 0, 8);
    printf("Expected 3 got %d ", result);
    if (result==3) {
        printf("PASSED\n\n");
    } else {
        printf("FAILED\n\n");
    }

    result = first_incr(t6, 0, 8);
    printf("Expected 3 got %d ", result);
    if (result==3) {
        printf("PASSED\n\n");
    } else {
        printf("FAILED\n\n");
    }


    printf("Tests longest_incr_rec\n");

    result = longest_incr_rec(t4, 0, 8);
    printf("Expected 4 got %d ", result);
    if (result==4) {
        printf("PASSED\n\n");
    } else {
        printf("FAILED\n\n");
    }

    result = longest_incr_rec(t5, 0, 8);
    printf("Expected 5 got %d ", result);
    if (result==5) {
        printf("PASSED\n\n");
    } else {
        printf("FAILED\n\n");
    }

    result = longest_incr_rec(t6, 0, 8);
    printf("Expected 4 got %d ", result);
    if (result==4) {
        printf("PASSED\n\n");
    } else {
        printf("FAILED\n\n");
    }

    return 0;
}
