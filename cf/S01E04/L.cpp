#include <iostream>
#include "stdio.h"

char NO_CHOICE = 128;

int size;
char dict[128], theCount[128], theCircle[128], circle[27], choice[128], stri[1000001], strd[128];
char theChar[128][27];

int main() {
    int test;
    scanf("%d", &test);
    while (test-- > 0) {
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
                    break;
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
        for (char i = 0; i < 27; i++) {
            circle[i] = NO_CHOICE;
        }
        for (int i = 0; i < size; i ++ ) {
            if (choice[stri[i]] != NO_CHOICE) {
                printf("%c",choice[stri[i]]);
                continue;
            }
            nowChar = stri[i];
            nowCircle = theCircle[nowChar];
            //printf(" nowCircle %d -> %d\n",nowChar, nowCircle );
            if (circle[nowCircle] == NO_CHOICE) {
                useCount = theCount[nowChar];
                found = false;
                testCircle = nowCircle;
                testCircle *= 2;
                if (testCircle == 1) {
                    found = true;
                }
                if (!found) {
                    while (testCircle <= 26) { // 2 should follow 4's Circle
                        if (circle[testCircle] != NO_CHOICE) {
                            useCount = circle[testCircle] % nowCircle;
                            found = true;
                        }
                        testCircle *= 2;
                    }
                }
                if (!found && nowCircle > 2) {
                    bool possibleCount[27];
                    for (char j = 0; j < 27; j ++) possibleCount[j] = false;
                    for (testCircle = (char)(nowCircle - 1); testCircle > 1; testCircle--) {
                        // for 4 , use some 2's Circle
                        if (nowCircle % testCircle == 0 && circle[testCircle] != NO_CHOICE) {
                            char pc = circle[testCircle];
                            while (pc <= nowCircle) {
                                possibleCount[pc] = true;
                                pc += testCircle;
                            }
                        }
                    }
                    char minChar = 'z' + 1;
                    for (char j = nowCircle; j > 0; j --) {
                        if (possibleCount[j] && theChar[nowChar][j] < minChar) {
                            minChar = theChar[nowChar][j];
                            useCount = j;
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