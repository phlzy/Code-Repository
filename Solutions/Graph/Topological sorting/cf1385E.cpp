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
const int maxn = 2e5 + 9;

vi g[maxn];
int in[maxn], s[maxn];
vector<pii> und;
int n, m;
queue<int> q;

void toposort(int &cnt)
{
    int rnk = 0;
    for (int i = 1; i <= n; ++i)
        if (in[i] == 0)
        {
            q.push(i);
            cnt++;
        }
    while (!q.empty())
    {
        int u = q.front();
        q.pop();
        s[u] = ++rnk;
        for (int i = 0; i < g[u].size(); ++i)
        {
            in[g[u][i]]--;
            if (in[g[u][i]] == 0)
            {
                cnt++;
                q.push(g[u][i]);
                s[g[u][i]] = ++rnk;
            }
        }
    }
}
int main()
{
    //freopen("in.txt","r",stdin);
    //freopen("data.txt","w",stdout);
    //std::ios::sync_with_stdio(false);
    //std::cin.tie(0);
    //std::cout.tie(0);
    int T;
    read(T);
    while (T--)
    {
        read(n), read(m);
        for (int i = 1; i <= n; ++i)
        {
            g[i].clear();
            in[i] = 0, s[i] = -1;
        }
        und.clear();
        for (int i = 1; i <= m; ++i)
        {
            int x, u, v;
            read(x), read(u), read(v);
            if (x == 0)
                und.push_back(make_pair(u, v));
            else
            {
                g[v].push_back(u);
                in[u]++;
            }
        }
        int cnt = 0;
        toposort(cnt);
        if (cnt < n)
        {
            puts("NO");
            continue;
        }
        puts("YES");
        for (int i = 0; i < und.size(); ++i)
        {
            if (s[und[i].first] < s[und[i].second])
                cout << und[i].second << ' ' << und[i].first << '\n';
            else
                cout << und[i].first << ' ' << und[i].second << '\n';
        }
        for (int i = 1; i <= n; ++i)
            for (int j = 0; j < g[i].size(); ++j)
                cout << g[i][j] << ' ' << i << '\n';
    }
    //fclose(stdin);
    //fclose(stdout);
    return 0;
}