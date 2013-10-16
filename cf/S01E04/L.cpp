#include <cstdio>
#include <iostream>

using namespace std;


char NO_CHOICE = 128;
int NO_CIRCLE = 128;

int test, size;
char stri[1000001], strd[128];
int dict[26], choice[26], theCount[26], theCircle[26], circle[27];
char theChar[26][27];

int getGCD(int u, int v) {
    while ( v != 0) {
        int r = u % v;
        u = v;
        v = r;
    }
    return u;
}

int main() {
    scanf("%d", &test);
    while (test-- > 0) {
        scanf("%d", &size);
        scanf("%s", stri);
        scanf("%s", strd);
        for (int i = 0; i < 26; i ++) {
            dict[strd[i] - 'a'] = i;
        }
        for (int i = 0; i < 26; i ++) {
            theCircle[i] = 0;
            choice[i] = NO_CHOICE;
            char min = i;
            int current=i, num = 0;
            theChar[i][0] = current;
            for (int k = 1; k <= 26; k++) {
                current = dict[current];
                theChar[i][k] = current;
                if (theCircle[i] == 0 && current == i) {
                    theCircle[i] = k;
                    break;
                }
                if (current < min) {
                    min = current;
                    num = k;
                }
            }
            theCount[i] = num;
        }

        int nowChar, nowCircle, useCount = 0, testCircle;
        for (int i = 0; i < 27; i++) {
            circle[i] = NO_CIRCLE;
        }
        for (int i = 0; i < size; i ++ ) {
            nowChar = stri[i] - 'a';
            if (choice[nowChar] != NO_CHOICE) {
                printf("%c", choice[nowChar] + 'a');
                continue;
            }
            nowCircle = theCircle[nowChar];
            if (circle[nowCircle] == NO_CIRCLE) {
                useCount = theCount[nowChar];
                testCircle = nowCircle;
                bool possibleCount[27];
                for (int j = 0; j < 27; j ++) possibleCount[j] = true;
                for (testCircle = 0; testCircle < 27; testCircle++) {
                    int gcd = getGCD(nowCircle, testCircle);
                    if (gcd > 1 && circle[testCircle] != NO_CIRCLE) {
                        int pc = 0;
                        while (pc <= nowCircle) {
                            if (pc % gcd != circle[testCircle] % gcd) {
                                possibleCount[pc] = false;
                            }
                            pc += 1;
                        }
                    }
                }
                int minChar = 27;
                for (int j = 0; j < nowCircle; j ++) {
                    if (possibleCount[j] && theChar[nowChar][j] < minChar) {
                        minChar = theChar[nowChar][j];
                        useCount = j;
                    }
                }
                circle[nowCircle] = useCount;
            }
            choice[nowChar] = theChar[nowChar][circle[nowCircle]];

            printf("%c",choice[nowChar] + 'a');
        }
        printf("\n");
    }
    return 0;
}