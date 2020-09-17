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
const int maxn = 1e5 + 9;

vi g[maxn], ans;
bool vis[maxn];
int dfn[maxn], low[maxn], fa[maxn];
int cnt;
set<int> ans2;
void dfs(int pos)
{
    low[pos] = dfn[pos] = ++cnt;
    vis[pos] = true;
    int children = 0;
    for (auto i : g[pos])
    {
        if (!vis[i])
        {
            children++;
            fa[i] = pos;
            dfs(i);
            low[pos] = min(low[pos], low[i]);
            if (fa[pos] == -1)
            {
                if (children >= 2)
                {
                    ans.emplace_back(pos);
                    ans2.insert(pos);
                }
            }
            else
            {
                if (low[i] >= dfn[pos])
                {
                    ans.emplace_back(pos);
                    ans2.insert(pos);
                }
            }
        }
        else if (i != fa[pos])
            low[pos] = min(low[pos], dfn[i]);
    }
}

signed main()
{
    freopen("in.txt", "r", stdin);
    //freopen("data.txt","w",stdout);
    //std::ios::sync_with_stdio(false);
    //std::cin.tie(0);
    //std::cout.tie(0);
    int m, n;
    read(n, m);
    for (int i = 0, x, y; i < m; i++)
    {
        read(x, y);
        g[x].emplace_back(y);
        g[y].emplace_back(x);
    }
    // memset vis
    memset(fa, -1, sizeof(fa));
    memset(dfn, 0, sizeof(dfn));
    memset(low, 0, sizeof(low));
    for (int i = 1; i <= n; ++i)
        if (!vis[i])
            dfs(i);
    /*
    sort(ans.begin(), ans.end());
    auto it = unique(ans.begin(), ans.end());
    cout << ans.size() << '\n';
    for (auto i = ans.begin(); i != it; ++i)
        cout << *i << ' ';
    cout << '\n';
    for (auto i : ans)
        cout << i << ' ';
    cout << '\n';
    */
    cout << ans2.size() << '\n';
    for (auto i : ans2)
        cout << i << ' ';
    //fclose(stdin);
    //fclose(stdout);
    return 0;
}