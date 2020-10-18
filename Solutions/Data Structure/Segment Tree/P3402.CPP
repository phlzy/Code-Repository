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
const int maxn = 2e5 + 9;

int n, m, fa[maxn * 30], deep[maxn * 30];

struct node
{
    int l, r;
} tr[maxn * 30];
int ver[maxn], tot;

void build(int &rt, int l, int r)
{
    rt = ++tot;
    if (l == r)
    {
        fa[rt] = l;
        return;
    }
    int mid = l + r >> 1;
    build(tr[rt].l, l, mid);
    build(tr[rt].r, mid + 1, r);
}

void update(int &rt, int last, int l, int r, int pos, int ff)
{
    rt = ++tot;
    if (l == r)
    {
        fa[rt] = ff;
        deep[rt] = deep[last];
        return;
    }
    tr[rt].l = tr[last].l;
    tr[rt].r = tr[last].r;
    int mid = l + r >> 1;
    if (pos <= mid)
        update(tr[rt].l, tr[last].l, l, mid, pos, ff);
    else
        update(tr[rt].r, tr[last].r, mid + 1, r, pos, ff);
}

int query(int rt, int l, int r, int pos)
{
    if (l == r)
        return rt;
    int mid = l + r >> 1;
    if (pos <= mid)
        return query(tr[rt].l, l, mid, pos);
    else
        return query(tr[rt].r, mid + 1, r, pos);
}

void add(int rt, int l, int r, int pos)
{
    if (l == r)
    {
        ++deep[rt];
        return;
    }
    int mid = l + r >> 1;
    if (pos <= mid)
        add(tr[rt].l, l, mid, pos);
    else
        add(tr[rt].r, mid + 1, r, pos);
}

int find_fa(int ed, int x)
{
    int ff = query(ed, 1, n, x);
    if (x == fa[ff])
        return ff;
    return find_fa(ed, fa[ff]);
}

signed main()
{
    read(n, m);
    build(ver[0], 1, n);
    for (int opt, k, a, b, i = 1; i <= m; ++i)
    {
        read(opt);
        if (opt == 1)
        {
            ver[i] = ver[i - 1];
            read(a, b);
            int f1 = find_fa(ver[i], a);
            int f2 = find_fa(ver[i], b);
            if (fa[f1] == fa[f2])
                continue;
            if (deep[f1] > deep[f2])
                swap(f1, f2);
            update(ver[i], ver[i - 1], 1, n, fa[f1], fa[f2]);
            if (deep[f1] == deep[f2])
                add(ver[i], 1, n, fa[f2]);
        }
        else if (opt == 2)
        {
            read(k);
            ver[i] = ver[k];
        }
        else
        {
            ver[i] = ver[i - 1];
            read(a, b);
            int f1 = find_fa(ver[i], a);
            int f2 = find_fa(ver[i], b);
            puts(fa[f1] == fa[f2] ? "1" : "0");
        }
    }
    return 0;
}
