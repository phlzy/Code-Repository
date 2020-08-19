#include <bits/stdc++.h>
using namespace std;

typedef long long ll;
typedef unsigned long long ull;
typedef pair<int, int> pii;
typedef vector<int> vi;
typedef vector<ll> vll;
#define int ll
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

int n, m, root, mod;

struct segtree
{
    int tree[maxn << 2], tag[maxn << 2];
    int a[maxn];
    void push_up(int p)
    {
        tree[p] = (tree[p << 1] + tree[p << 1 | 1]) % mod;
    }
    void build(int p, int l, int r)
    {
        tag[p] = 0;
        if (l == r)
        {
            tree[p] = a[l] % mod;
            return;
        }
        int mid = (l + r) >> 1;
        build(p << 1, l, mid);
        build(p << 1 | 1, mid + 1, r);
        push_up(p);
    }
    void flag(int p, int l, int r, int k)
    {
        //tag[p] = (tag[p] + k) % mod;
        tag[p] = tag[p] + k;
        tree[p] += k * (r - l + 1);
        tree[p] %= mod;
    }
    void push_down(int p, int l, int r)
    {
        int mid = (l + r) >> 1;
        flag(p << 1, l, mid, tag[p]);
        flag(p << 1 | 1, mid + 1, r, tag[p]);
        tag[p] = 0;
    }
    void update(int p, int l, int r, int ul, int ur, int k)
    {
        if (ul <= l && ur >= r)
        {
            tree[p] += k * (r - l + 1);
            tag[p] += k;
            tree[p] %= mod;
            //tag[p] %= mod;
            return;
        }
        push_down(p, l, r);
        int mid = (l + r) >> 1;
        if (ul <= mid)
            update(p << 1, l, mid, ul, ur, k);
        if (ur > mid)
            update(p << 1 | 1, mid + 1, r, ul, ur, k);
        push_up(p);
    }
    int query(int p, int l, int r, int qx, int qy)
    {
        if (qx <= l && qy >= r)
            return tree[p] % mod;
        int ans = 0, mid = (l + r) >> 1;
        push_down(p, l, r);
        if (qx <= mid)
            ans += query(p << 1, l, mid, qx, qy), ans %= mod;
        if (qy > mid)
            ans += query(p << 1 | 1, mid + 1, r, qx, qy), ans %= mod;
        return ans;
    }
};

struct path_decomposition
{
    int head[maxn], to[maxn], nxt[maxn], tot = 0;
    int son[maxn], id[maxn], fa[maxn], cnt, dep[maxn], sz[maxn], top[maxn];
    int w[maxn];
    void add(int u, int v)
    {
        to[++tot] = v;
        nxt[tot] = head[u];
        head[u] = tot;
    }

    segtree tree;

    void dfs1(int p, int f, int d)
    {
        dep[p] = d;
        fa[p] = f;
        sz[p] = 1;
        int maxson = -1;
        for (int i = head[p]; i; i = nxt[i])
        {
            int y = to[i];
            if (y == f)
                continue;
            dfs1(y, p, d + 1);
            sz[p] += sz[y];
            if (sz[y] > maxson)
                son[p] = y, maxson = sz[y];
        }
    }

    void dfs2(int p, int topf)
    {
        id[p] = ++cnt;
        tree.a[cnt] = w[p];
        top[p] = topf;
        if (son[p] == 0)
            return;
        dfs2(son[p], topf);
        for (int i = head[p]; i; i = nxt[i])
        {
            int y = to[i];
            if (y == fa[p] || y == son[p])
                continue;
            dfs2(y, y);
        }
    }

    int query(int l, int r)
    {
        int ans = 0;
        while (top[l] != top[r])
        {
            if (dep[top[l]] < dep[top[r]])
                swap(l, r);
            ans += tree.query(1, 1, n, id[top[l]], id[l]);
            ans %= mod;
            l = fa[top[l]];
        }
        if (dep[l] > dep[r])
            swap(l, r);
        ans += tree.query(1, 1, n, id[l], id[r]);
        return ans % mod;
    }

    void update(int l, int r, int k)
    {
        k %= mod;
        while (top[l] != top[r])
        {
            if (dep[top[l]] < dep[top[r]])
                swap(l, r);
            tree.update(1, 1, n, id[top[l]], id[l], k);
            l = fa[top[l]];
        }
        if (dep[l] > dep[r])
            swap(l, r);
        tree.update(1, 1, n, id[l], id[r], k);
    }

    int qson(int p)
    {
        return tree.query(1, 1, n, id[p], id[p] + sz[p] - 1);
    }

    void upson(int p, int k)
    {
        tree.update(1, 1, n, id[p], id[p] + sz[p] - 1, k);
    }
};
path_decomposition pd;
signed main()
{
    read(n), read(m), read(root), read(mod);
    int op, u, v, k;
    for (int i = 1; i <= n; ++i)
        read(pd.w[i]);
    for (int i = 1; i < n; ++i)
    {
        read(u), read(v);
        pd.add(u, v), pd.add(v, u);
    }
    pd.dfs1(root, 0, 1);
    pd.dfs2(root, root);
    pd.tree.build(1, 1, n);
    while (m--)
    {
        read(op);
        if (op == 1)
        {
            read(u), read(v), read(k);
            pd.update(u, v, k);
        }
        else if (op == 2)
        {
            read(u), read(v);
            cout << pd.query(u, v) << '\n';
        }
        else if (op == 3)
        {
            read(u), read(k);
            pd.upson(u, k);
        }
        else
        {
            read(u);
            cout << pd.qson(u) << '\n';
        }
    }
    return 0;
}
