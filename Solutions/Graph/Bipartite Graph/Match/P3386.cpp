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
const int maxn = 1e4 + 9;

int match[maxn], vis[maxn];
vi e[maxn];
bool dfs(int u, int tag)
{
    if (vis[u] == tag)
        return false;
    vis[u] = tag;
    for (auto i : e[u])
    {
        if (match[i] == 0 || dfs(match[i], tag))
        {
            match[i] = u;
            return true;
        }
    }
    return false;
}
int Hungarian(int n)
{
    int ans = 0;
    for (int i = 1; i <= n; ++i)
        if (dfs(i, i))
            ans++;
    return ans;
}
signed main()
{
    //freopen("in.txt","r",stdin);
    //freopen("data.txt","w",stdout);
    //std::ios::sync_with_stdio(false);
    //std::cin.tie(0);
    //std::cout.tie(0);
    int n, m, t;
    read(n, m, t);
    for (int i = 1, u, v; i <= t; ++i)
    {
        read(u, v);
        e[u].emplace_back(v);
    }
    cout << Hungarian(n) << endl;
    //fclose(stdin);
    //fclose(stdout);
    return 0;
}