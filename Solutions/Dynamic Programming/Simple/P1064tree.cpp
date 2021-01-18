#include <bits/stdc++.h>
//#include <ext/pb_ds/assoc_container.hpp>
//#include <ext/pb_ds/tree_policy.hpp>
//#include <ext/pb_ds/priority_queue.hpp>
//#include <ext/pb_ds/hash_policy.hpp>
using namespace std;
//using namespace __gnu_pbds;

typedef long long ll;
typedef unsigned long long ull;
typedef pair<int, int> pii;
typedef vector<int> vi;
typedef vector<ll> vll;
//typedef gp_hash_table<int, int> hashmap;
//typedef tree<pii, null_type, std::less<pii>, splay_tree_tag, tree_order_statistics_node_update> splaytree;
//typedef tree<pii, null_type, std::less<pii>, rb_tree_tag, tree_order_statistics_node_update> rbtree;
//typedef __gnu_pbds::priority_queue<int, std::greater<int>, __gnu_pbds::binary_heap_tag> binheap;
//typedef __gnu_pbds::priority_queue<int, std::greater<int>, __gnu_pbds::pairing_heap_tag> pairingheap;

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
inline void read(T &t, Args &...args)
{
    read(t);
    read(args...);
}

//mt19937 rnd(time(0));
const int inf = 0x3f3f3f3f;
const double eps = 1e-8;
const int maxn = 100;
const int maxv = 4e4;
const ll mod = 998244353;

struct element
{
    int v, w;
    element() {}
    element(int a, int b) : v(a), w(b) {}
};

struct edge
{
    int from, to;
    edge() {}
    edge(int a, int b) : from(a), to(b) {}
};

element d[maxn], t[maxn];
edge e[maxn];
int head[maxn], nxt[maxn], root[maxn], tot;
void add(int u, int v)
{
    e[++tot] = edge(u, v);
    nxt[tot] = head[u];
    head[u] = tot;
}

int pre[maxn], dp[maxn][maxv], p;
void dfs(int fa)
{
    int tmp = p;
    for (int i = head[fa]; i; i = nxt[i])
    {
        dfs(e[i].to);
    }
    d[++p] = t[fa];
    pre[p] = tmp;
}
int n, m;
signed main()
{
    //freopen("in.txt","r",stdin);
    //freopen("data.txt","w",stdout);
    //std::ios::sync_with_stdio(false);
    //std::cin.tie(0);
    //std::cout.tie(0);
    scanf("%d%d", &m, &n);
    for (int i = 1; i <= n; i++)
    {
        int v, w, fa;
        scanf("%d%d%d", &v, &w, &fa);
        t[i] = element(v, v * w);
        add(fa, i);
    }
    dfs(0);
    for (int i = 1; i <= n; i++)
    {
        for (int j = 0; j <= m; j++)
        {
            if (j >= d[i].v)
                dp[i][j] = max(dp[pre[i]][j], dp[i - 1][j - d[i].v] + d[i].w);
            else
                dp[i][j] = dp[pre[i]][j];
        }
    }
    printf("%d\n", dp[n][m]);
    //fclose(stdin);
    //fclose(stdout);
    return 0;
}
