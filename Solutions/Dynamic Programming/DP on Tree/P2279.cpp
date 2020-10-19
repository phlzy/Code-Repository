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
    T data = 0, dp = 1;
    char ch = getchar();
    while (!isdigit(ch))
    {
        if (ch == '-')
            dp = -1;
        ch = getchar();
    }
    while (isdigit(ch))
    {
        data = (data << 3) + (data << 1) + ch - '0';
        ch = getchar();
    }
    x = dp * data;
}
template <typename T, typename... Args>
inline void read(T &t, Args &... args)
{
    read(t);
    read(args...);
}
const int inf = 0x3f3f3f3f;
const double eps = 1e-8;
const int maxn = 1e5 + 9;

struct edge
{
    int next, to;
};
edge e[maxn];
int head[maxn], dp[maxn][5];
int tot;
int n;
inline void add(int u, int v)
{
    e[++tot].next = head[u];
    e[tot].to = v;
    head[u] = tot;
}
void dfs(int x, int fa)
{
    int sum2 = 0, sum3 = 0, cnt = 0;
    for (int i = head[x]; i; i = e[i].next)
        if (e[i].to != fa)
        {
            int y = e[i].to;
            dfs(y, x);
            sum2 += dp[y][2], sum3 += dp[y][3];
            cnt++;
        }
    if (!cnt)
    {
        dp[x][0] = dp[x][1] = dp[x][2] = 1;
        return;
    }
    dp[x][0] = 1, dp[x][1] = dp[x][2] = maxn;
    for (int i = head[x]; i; i = e[i].next)
        if (e[i].to != fa)
        {
            int y = e[i].to;
            dp[x][0] += dp[y][4];
            dp[x][1] = min(dp[x][1], dp[y][0] + sum3 - dp[y][3]);
            dp[x][2] = min(dp[x][2], dp[y][1] + sum2 - dp[y][2]);
            dp[x][3] += dp[y][2];
            dp[x][4] += dp[y][3];
        }
    for (int i = 1; i < 5; ++i)
        dp[x][i] = min(dp[x][i], dp[x][i - 1]);
}
signed main()
{
    read(n);
    for (int i = 2, x; i <= n; ++i)
        read(x), add(x, i), add(i, x);
    dfs(1, 0);
    printf("%d\n", dp[1][2]);
}
