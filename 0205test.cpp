#include <bits/stdc++.h>
using namespace std;
const int maxn = 200005;
char s[maxn];
struct point1
{
    int x, y;
    bool operator<(const point1 b)
    {
        if (x != b.x)
            return x < b.x;
        else
            return y < b.y;
    }
} p, tp;
map<point1, int> m;
int main()
{
    freopen("in.txt", "r", stdin);
    freopen("data.txt", "w", stdout);
    unsigned int n, t, cl, cr, cu, cd, minn, ml, mr;
    scanf("%d", &t);
    while (t--)
    {
        scanf("%d%s", &n, s);
        tp.x = tp.y = 0;
        m[tp] = 0;
        minn = maxn;
        for (register int i = 0; i < n; i++)
        {
            if (s[i] == 'U')
            {
                p.x = tp.x;
                p.y = tp.y + 1;
            }
            if (s[i] == 'D')
            {
                p.x = tp.x;
                p.y = tp.y - 1;
            }
            if (s[i] == 'L')
            {
                p.x = tp.x - 1;
                p.y = tp.y;
            }
            if (s[i] == 'R')
            {
                p.x = tp.x + 1;
                p.y = tp.y;
            }
            if (m.count(p) > 0 && minn > m.count(p) - i - 1)
            {
                minn = m.count(p) - i - 1;
                ml = m.count(p) + 1;
                mr = i + 1;
            }
            m[p] = i + 1;
            tp.x = p.x, tp.y = p.y;
        }
        if (minn < maxn)
            printf("%d %d\n", ml, mr);
        else
            printf("-1\n");
    }
    return 0;
}
