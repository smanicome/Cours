#include <string.h>
#include <stdlib.h>
#include <stdio.h>
#include <math.h>
#include "sum.h"

int sum_digits_iter(int n) {
    int res=0;

    while(n>0) {
        int tmp = n%10;
        res += tmp;
        n = floor(n/10);
    }

    return res;
}

int sum_digits_rec(int n) {
    if (n<10) {
        return n;
    }

    return n%10 + sum_digits_rec(floor(n/10));
}

int digit_sum_digits_iter(int n) {
    while (n>=10) {
        printf("%d\n", n);
        n = sum_digits_iter(n);
    }
    printf("%d\n", n);
    return n; 
}

int digit_sum_digits_rec(int n) {
    printf("%d\n", n);
    if (n>=10) {
        n = sum_digits_rec(n);
        return digit_sum_digits_rec(n);
    }
    return n; 
}