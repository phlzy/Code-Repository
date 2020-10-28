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
template <typename T, typename... Args>
inline void read(T &t, Args &... args)
{
    read(t);
    read(args...);
}
const int inf = 0x3f3f3f3f;
const double eps = 1e-8;
const int maxn = 1e6 + 9;

struct E
{
    int to, w, next;
} edge[maxn];
int tot;
int head[3005];
void add(int u, int v, int w)
{
    edge[++tot].to = v;
    edge[tot].w = w;
    edge[tot].next = head[u];
    head[u] = tot;
}
int dp[3005][3005], val[3005], t[3005];
int n, m;
int dfs(int fa)
{
    if (fa > n - m)
    {
        dp[fa][1] = val[fa];
        return 1;
    }
    int sum = 0;
    for (int i = head[fa]; i; i = edge[i].next)
    {
        int v = edge[i].to;
        int tmp = dfs(v);
        for (int j = 0; j <= sum; ++j)
            t[j] = dp[fa][i];
        for (int j = 0; j <= sum; ++j)
            for (int k = 0; k <= tmp; ++k)
                dp[fa][j + k] = max(dp[fa][j + k], t[j] + dp[v][k] - edge[i].w);
        sum += tmp;
    }
    return sum;
}

signed main()
{
    //freopen("in.txt","r",stdin);
    //freopen("data.txt","w",stdout);
    //std::ios::sync_with_stdio(false);
    //std::cin.tie(0);
    //std::cout.tie(0);
    read(n, m);
    memset(dp, 0x3f, sizeof(dp));
    for (int i = 1, x; i <= n - m; ++i)
    {
        read(x);
        for (int j = 1, a, b; j <= x; ++j)
        {
            read(a, b);
            add(i, a, b);
        }
    }
    for (int i = n - m + 1; i <= n; i++)
        read(val[i]);
    for (int i = 1; i <= n; i++)
        dp[i][0] = 0;
    dfs(1);
    for (int i = m; i > 0; i--)
        if (dp[1][i] >= 0)
        {
            printf("%d", i);
            break;
        }
    //fclose(stdin);
    //fclose(stdout);
    return 0;
}