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

int a[maxn];

struct segtree
{
    int tree[maxn << 2], tag[maxn << 2];
    void push_up(int p)
    {
        tree[p] = tree[p << 1] + tree[p << 1 | 1];
    }
    void build(int p, int l, int r)
    {
        tag[p] = 0;
        if (l == r)
        {
            tree[p] = a[l];
            return;
        }
        int mid = (l + r) >> 1;
        build(p << 1, l, mid);
        build(p << 1 | 1, mid + 1, r);
        push_up(p);
    }
    void flag(int p, int l, int r, int k)
    {
        tag[p] += k;
        tree[p] += k * (r - l + 1);
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
            return tree[p];
        int ans = 0, mid = (l + r) >> 1;
        push_down(p, l, r);
        if (qx <= mid)
            ans += query(p << 1, l, mid, qx, qy);
        if (qy > mid)
            ans += query(p << 1 | 1, mid + 1, r, qx, qy);
        return ans;
    }
};
segtree t;
signed main()
{
    //freopen("in.txt","r",stdin);
    //freopen("data.txt","w",stdout);
    //std::ios::sync_with_stdio(false);
    //std::cin.tie(0);
    //std::cout.tie(0);
    int n, w;
    read(n), read(w);
    t.build(1, 1, 100000);
    for (int i = 1; i <= n; ++i)
    {
        int x, y;
        read(x), read(y);
        t.update(1, 1, 100000, x, x, y);
    }
    if (w == 0)
    {
        cout << 0 << endl;
    }
    else
    {
        int ans = 0;
        for (int i = 1; i <= 100001 - w; ++i)
        {
            ans = max(ans, t.query(1, 1, 100000, i, i + w - 1));
        }
        cout << ans << endl;
    }
    //fclose(stdin);
    //fclose(stdout);
    return 0;
}