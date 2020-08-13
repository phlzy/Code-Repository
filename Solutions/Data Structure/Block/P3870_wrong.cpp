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

int ans[maxn], fa[maxn], a[maxn];
int st[maxn], ed[maxn], sz[maxn], tag[maxn];
int n, m, sq;

void change(int x, int y)
{
    int l = fa[x], r = fa[y];
    for (int i = x; i <= min(y, ed[l]); ++i)
    {
        ans[l] -= (a[i] ^ tag[l]);
        a[i] ^= 1;
        ans[l] += (a[i] ^ tag[l]);
    }
    if (l != r)
    {
        for (int i = st[r]; i <= y; ++i)
        {
            ans[r] -= (a[i] ^ tag[r]);
            a[i] ^= 1;
            ans[r] += (a[i] ^ tag[r]);
        }
    }
    for (int i = l + 1; i < r; ++i)
    {
        tag[i] ^= 1;
        ans[i] = sq - ans[i];
    }
}

int query(int x, int y)
{
    int res = 0, l = fa[x], r = fa[y];
    for (int i = x; i <= min(y, ed[l]); ++i)
        res += (a[i] ^ tag[l]);
    if (l != r)
    {
        for (int i = st[r]; i <= y; ++i)
            res += (a[i] ^ tag[r]);
    }
    for (int i = l + 1; i < r; ++i)
    {
        res += ans[i];
    }
    return res;
}

void check()
{
    for (int i = 1; i <= n; ++i)
    {
        int f = fa[i];
        cout << i << ' ' << f << ' ' << tag[f] << ' ' << ans[f] << endl;
    }
}

signed main()
{
    //freopen("in.txt","r",stdin);
    //freopen("data.txt","w",stdout);
    //std::ios::sync_with_stdio(false);
    //std::cin.tie(0);
    //std::cout.tie(0);
    read(n), read(m);
    sq = sqrt(n);
    /*
    for (int i = 1; i < +n; ++i)
        read(s[i]);
    */
    for (int i = 1; i <= sq; ++i)
    {
        st[i] = n / sq * (i - 1) + 1;
        ed[i] = n / sq * i;
    }
    ed[sq] = n;
    for (int i = 1; i <= sq; ++i)
        for (int j = st[i]; j <= ed[i]; ++j)
            fa[j] = i;
    //check();
    /*
    for (int i = 1; i <= sq; ++i)
        sz[i] = ed[i] - st[i] + 1;
    */
    for (int t = 1; t <= m; ++t)
    {
        int op, x, y;
        read(op), read(x), read(y);
        if (op == 0)
        {
            change(x, y);
            //check();
        }
        else
        {
            cout << query(x, y) << '\n';
        }
    }
    //fclose(stdin);
    //fclose(stdout);
    return 0;
}