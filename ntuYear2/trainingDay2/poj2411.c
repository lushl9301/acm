#include <stdio.h>
#include <stdint.h>

int h, w;
int64_t f[12][4096];

int judge(int now) {
    int count0 = 0;
    int i;
    for (i = 0; i < w; i++) {
        if (now & 1) {
            if (count0) {
                return 0;
            }
        } else count0 ^= 1;
        now >>= 1;
    }
    if (count0) {
        return 0;
    } else {
        return 1;
    }
}


int main() {
    int i, j, k;
    int limit;
    
    while (scanf("%d %d", &h, &w), h + w) {
        if (h & w & 1) {
            printf("0\n");
            continue;
        }
        f[0][0] = 1;
        limit = 1 << w;
        for (i = 1; i <= h; i++) {
            for (j = 0; j < limit; j++) {
                f[i][j] = 0;
                for (k = 0; k < limit; k++) {
                    if (!(k & j) && judge(k^j)) {
                        f[i][j] += f[i-1][k];
                    }
                }
            }
        }
        printf("%I64d\n", f[h][0]);
    }
}
