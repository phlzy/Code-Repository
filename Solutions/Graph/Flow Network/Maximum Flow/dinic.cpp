#include <bits/stdc++.h>
using namespace std;
/* 
*我只是测一下模板
*/
typedef unsigned long long ull;
typedef pair<int, int> pii;
typedef vector<int> vi;
#define int long long
const int maxn = 1e5 + 9;
struct node
{
    int to;
    int next;
    int w;
} qxx[maxn << 1];
int h[maxn << 1];
int cnt = 1;
int n, m, st, en;
int x, y, z;
inline void add(int x, int y, int z)
{
    qxx[++cnt] = (node){y, h[x], z};
    h[x] = cnt;
}
inline void ad(int x, int y, int z)
{
    add(x, y, z);
    add(y, x, 0);
}
int d[maxn << 1];
bool bfs()
{
    memset(d, 0, sizeof(d));
    queue<int> q;
    while (!q.empty())
        q.pop();
    d[st] = 1;
    q.push(st);
    while (!q.empty())
    {
        int x = q.front();
        q.pop();
        for (int i = h[x]; i; i = qxx[i].next)
        {
            int v = qxx[i].to;
            if (!d[v] && qxx[i].w)
            {
                d[v] = d[x] + 1;
                q.push(v);
                if (v == en)
                    return true;
            }
        }
    }
    return false;
}
int dfs(int u, int flow)
{
    if (u == en)
        return flow;
    int rest = flow;
    for (int i = h[u]; i; i = qxx[i].next)
    {
        int v = qxx[i].to;
        if (d[v] == d[u] + 1 && qxx[i].w)
        {
            int tmp = dfs(v, min(qxx[i].w, rest));
            if (!tmp)
                d[v] = 0;
            rest -= tmp;
            qxx[i].w -= tmp;
            qxx[i ^ 1].w += tmp;
            if (!rest)
                break;
        }
    }
    return flow - rest;
}
int ans, sth;
signed main()
{
    scanf("%lld%lld%lld%lld", &n, &m, &st, &en);
    for (int i = 1; i <= m; i++)
    {
        scanf("%lld%lld%lld", &x, &y, &z);
        ad(x, y, z);
    }
    while (bfs())
        while (sth = dfs(st, 1e9))
            ans += sth;
    printf("%lld", ans);
    return 0;
}