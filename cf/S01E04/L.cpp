#include <iostream>
#include "stdio.h"

char NO_CIRCLE = 128;
char NO_CHOICE = 128;

int size;
char dict[128], theCount[128], theCircle[128], circle[27], choice[128];
char theChar[128][27];

int main() {
    int test;
    scanf("%d", &test);
    char stri[1000001], strd[128];
    while (test-- > 0) {
        for (int i = 0; i < 27; i++) {
            circle[i] = NO_CIRCLE;
        }
        scanf("%d", &size);
        scanf("%s", stri);
        scanf("%s", strd);
        for (char i = 0; i < 26; i ++) {
            dict[strd[i]] = i + 'a';
        }
        for (char i = 'a'; i <= 'z'; i ++) {
            theCircle[i] = 0;
            choice[i] = NO_CHOICE;
            char min = i, current=i;
            char num = 0;
            theChar[i][0] = current;
            for (char k = 1; k <= 26; k++) {
                current = dict[current];
                //printf(" = >%d  %d\n", current, i);
                if (theCircle[i] == 0 && current == i) {
                    theCircle[i] = k;
                }
                theChar[i][k] = current;
                if (current < min) {
                    min = current;
                    num = k;
                }
            }
            theCount[i] = num;
        }
        
        char nowChar, nowCircle, useCount = 0, testCircle;
        bool found;
        for (int i = 0; i < size; i ++ ) {
            if (choice[stri[i]] != NO_CHOICE) {
                printf("%c",choice[stri[i]]);
                continue;
            }
            nowChar = stri[i];
            nowCircle = theCircle[nowChar];
            //printf(" nowCircle %d -> %d\n",nowChar, nowCircle );
            if (circle[nowCircle] == NO_CIRCLE) {
                useCount = theCount[nowChar];
                found = false;
                testCircle = nowCircle;
                testCircle *= 2;
                if (testCircle == 1) {
                    found = true;
                }
                if (!found) {
                    while (testCircle <= 26) { // 2 should follow 4's Circle
                        if (circle[testCircle] != NO_CIRCLE) {
                            useCount = circle[testCircle];
                            found = true;
                        }
                        testCircle *= 2;
                    }                }
                if (!found) {
                    if (nowCircle > 2) {
                        for (testCircle = (char)(nowCircle - 1); testCircle > 1; testCircle--) {
                            // for 4 , use some 2's Circle
                            if (nowCircle % testCircle == 0) {
                                if (circle[testCircle] != NO_CIRCLE) {
                                    char testCount = useCount = circle[testCircle];
                                    char smallestChar = theChar[nowChar][testCount];
                                    testCount += testCircle;
                                    while (testCount <= 26) {
                                        if (theChar[nowChar][testCount] < smallestChar) {
                                            useCount = testCount;
                                            smallestChar = theChar[nowChar][testCount];
                                        }
                                        testCount += testCircle;
                                    }
                                    break;
                                }
                            }
                        }
                        
                    }
                }
                circle[nowCircle] = useCount;
            }
            choice[stri[i]] = theChar[nowChar][circle[nowCircle]];
            printf("%c",choice[stri[i]]);
        }
        printf("\n");
    }
    return 0;
}