#include <bits/stdc++.h>
using namespace std;

const int inf = 0x3f3f3f3f;
const double eps = 1e-8;
const int maxn = 1e5 + 9;

int n, m;
int sq;
int q[maxn];
bool tag[maxn];
bool k[maxn];
int sum[maxn];

void opt1(int l, int r)
{
    for (int i = l; i <= min(r, q[l] * sq); i++)
    {
        sum[q[i]] -= (k[i] ^ tag[q[i]]);
        k[i] ^= 1;
        sum[q[i]] += (k[i] ^ tag[q[i]]);
    }
    if (q[l] != q[r])
    {
        for (int i = (q[r] - 1) * sq + 1; i <= r; i++)
        {
            sum[q[i]] -= (k[i] ^ tag[q[i]]);
            k[i] ^= 1;
            sum[q[i]] += (k[i] ^ tag[q[i]]);
        }
    }
    for (int i = q[l] + 1; i < q[r]; i++)
    {
        tag[i] ^= 1;
        sum[i] = sq - sum[i];
    }
}
int opt2(int l, int r)
{
    int s = 0;
    for (int i = l; i <= min(r, q[l] * sq); i++)
    {
        s += (k[i] ^ tag[q[i]]);
    }
    if (q[l] != q[r])
    {
        for (int i = (q[r] - 1) * sq + 1; i <= r; i++)
        {
            s += (k[i] ^ tag[q[i]]);
        }
    }
    for (int i = q[l] + 1; i < q[r]; i++)
    {
        s += sum[i];
    }
    return s;
}
int main()
{
    scanf("%d%d", &n, &m);
    sq = sqrt(n);
    for (int i = 1; i <= n; i++)
    {
        q[i] = (i - 1) / sq + 1;
    }
    while (m--)
    {
        int opt, l, r;
        scanf("%d%d%d", &opt, &l, &r);
        if (opt == 0)
        {
            opt1(l, r);
        }
        else
        {
            printf("%d\n", opt2(l, r));
        }
    }
    return 0;
}