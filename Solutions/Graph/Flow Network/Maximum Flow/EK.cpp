

#include <bits/stdc++.h>
using namespace std;

typedef long long ll;
typedef unsigned long long ull;
typedef pair<int, int> pii;
typedef vector<int> vi;
typedef vector<ll> vll;

template <typename T>
inline void read(T &x)
{
    T data = 0, f = 1;
    char ch = getchar();
    while (!isdigit(ch))
    {
        if (ch == '-')
            f = -1;
        ch = getchar();
    }
    while (isdigit(ch))
    {
        data = (data << 3) + (data << 1) + ch - '0';
        ch = getchar();
    }
    x = f * data;
}

const int inf = 0x3f3f3f3f;
const double eps = 1e-8;
const int maxn = 1e5 + 9;

struct edge
{
    int from, to, cap, flow;
    edge(int u, int v, int c, int f) : from(u), to(v), cap(c), flow(f) {}
};
struct EK
{
    int n, m;
    vector<edge> e;
    vi g[maxn];
    int a[maxn], p[maxn];
    void init(int n)
    {
        for (int i = 1; i <= n; ++i)
        {
            g[i].clear();
        }
        e.clear();
    }
    void add(int from, int to, int cap)
    {
        e.push_back(edge(from, to, cap, 0));
        e.push_back(edge(to, from, 0, 0));
        m = e.size();
        g[from].push_back(m - 2);
        g[to].push_back(m - 1);
    }
    ll maxflow(int s, int t)
    {
        ll flow = 0;
        for (;;)
        {
            memset(a, 0, sizeof(a));
            queue<int> q;
            q.push(s);
            a[s] = inf;
            int cnt = 0;
            while (!q.empty())
            {
                int x = q.front();
                q.pop();
                for (int i = 0; i < g[x].size(); ++i)
                {
                    edge &eg = e[g[x][i]];
                    if (!a[eg.to] && eg.cap > eg.flow)
                    {
                        p[eg.to] = g[x][i];
                        a[eg.to] = min(a[x], eg.cap - eg.flow);
                        q.push(eg.to);
                    }
                }
                if (a[t])
                    break;
            }
            if (!a[t])
                break;
            for (int u = t; u != s; u = e[p[u]].from)
            {
                e[p[u]].flow += a[t];
                e[p[u] ^ 1].flow -= a[t];
            }
            flow += a[t];
        }
        return flow;
    }
};
EK solve;
signed main()
{
    //freopen("in.txt","r",stdin);
    //freopen("data.txt","w",stdout);
    //std::ios::sync_with_stdio(false);
    //std::cin.tie(0);
    //std::cout.tie(0);
    int m, s, t;
    read(solve.n), read(m), read(s), read(t);
    solve.init(solve.n);
    for (int i = 1; i <= m; ++i)
    {
        int u, v, w;
        read(u), read(v), read(w);
        solve.add(u, v, w);
    }
    cout << solve.maxflow(s, t) << '\n';
    //fclose(stdin);
    //fclose(stdout);
    return 0;
}