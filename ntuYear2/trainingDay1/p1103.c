#include <stdio.h>
int main() {
    int l, m, i, j, ans, start, end;
    int a[10000];
    
    scanf("%d %d", &l, &m);
    for (i = 0; i <= l; i++) {
        a[i] = 1;
    }
    
    for (i = 0; i < m; i++) {
        scanf("%d %d", &start, &end);
        for (j = start; j <= end; j++) {
            a[j] = 0;
        }
    }
    ans = 0;
    for (i = 0; i<=l; i++) {
        if (a[i]) {
            ans++;
        }
    }
    printf("%d\n", ans);
}
