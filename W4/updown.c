#include <stdio.h>
#include <stdlib.h>

enum status {up, down, idle};

int main() {
    int s, n, m; 
    scanf("%d %d %d", &s, &n, &m);
    int p;
    scanf("%d", &p);
    int peak_len, valley_len, peak_count = 0, valley_count = 0;
    enum status peak_status = idle, valley_status = idle;
    for (int i = 0; i < s-1; i++) {
        int v;
        scanf("%d", &v);
        if (v > p) {
            switch (peak_status)
            {
            case idle:
                peak_status = up;
                peak_len = 2; break;
            case up:
                peak_len++; break;
            case down:
                if (peak_len >= n) {
                    peak_count++;
                }
                peak_status = up;
                peak_len = 2; break;
            }
            switch (valley_status)
            {
            case idle: break;
            case up: 
                valley_len++; break;
            case down:
                if (valley_len >= m) {
                    valley_len = 2;
                    valley_status = up;
                } else {
                    valley_status = idle;
                } break;
            }
        } else {
            switch (peak_status)
            {
            case idle: break;
            case up:
                if (peak_len >= n) {
                    peak_len = 2;
                    peak_status = down;
                } else {
                    peak_status = idle;
                } break;
            case down:
                peak_len++; break;
            }
            switch (valley_status)
            {
            case idle: 
                valley_status = down;
                valley_len = 2; break;
            case up:
                if (valley_len >= m) {
                    valley_count++;
                }
                valley_status = down;
                valley_len = 2; break;
            case down:
                valley_len++; break;
            }
        }
        p = v;
    }
    if (peak_status == down && peak_len >= n) peak_count++;
    if (valley_status == up && valley_len >= m) valley_count++;
    printf("%d %d\n", peak_count, valley_count);
}