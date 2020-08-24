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
const int maxn = 1e6 + 9;

int n, cnt;
ll x_1, x_2, y_1, y_2, a[maxn << 1];
struct scanline
{
    ll l, r, h;
    int mark;
    bool operator<(const scanline &b) const
    {
        return h < b.h;
    }
};
scanline line[maxn << 1];

struct segtree
{
    int l, r, sum;
    ll len;
};
segtree tree[maxn << 2];
void build(int p, int l, int r)
{
    tree[p].l = l, tree[p].r = r;
    tree[p].sum = 0, tree[p].len = 0;
    if (l == r)
        return;
    int mid = (l + r) >> 1;
    build(p << 1, l, mid);
    build(p << 1 | 1, mid + 1, r);
}
void push_up(int p)
{
    int l = tree[p].l, r = tree[p].r;
    if (tree[p].sum > 0)
        tree[p].len = a[r + 1] - a[l];
    else
        tree[p].len = tree[p << 1].len + tree[p << 1 | 1].len;
}

void update(int p, ll L, ll R, int c)
{
    int l = tree[p].l, r = tree[p].r;
    if (a[r + 1] <= L || R <= a[l])
        return;
    if (L <= a[l] && a[r + 1] <= R)
    {
        tree[p].sum += c;
        push_up(p);
        return;
    }
    update(p << 1, L, R, c);
    update(p << 1 | 1, L, R, c);
    push_up(p);
}

signed main()
{
    //freopen("in.txt","r",stdin);
    //freopen("data.txt","w",stdout);
    //std::ios::sync_with_stdio(false);
    //std::cin.tie(0);
    //std::cout.tie(0);
    cin >> n;
    for (int i = 1; i <= n; ++i)
    {
        cin >> x_1 >> y_1 >> x_2 >> y_2;
        a[2 * i - 1] = x_1, a[2 * i] = x_2;
        line[2 * i - 1] = (scanline){x_1, x_2, y_1, 1};
        line[2 * i] = (scanline){x_1, x_2, y_2, -1};
    }
    n *= 2;
    sort(line + 1, line + n + 1);
    sort(a + 1, a + n + 1);
    int tot = unique(a + 1, a + n + 1) - a - 1;
    build(1, 1, tot - 1);
    ll ans = 0;
    for (int i = 1; i < n; ++i)
    {
        update(1, line[i].l, line[i].r, line[i].mark);
        ans += tree[1].len * (line[i + 1].h - line[i].h);
    }
    cout << ans << '\n';
    //fclose(stdin);
    //fclose(stdout);
    return 0;
}