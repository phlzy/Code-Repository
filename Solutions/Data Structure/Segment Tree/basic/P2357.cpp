#include <bits/stdc++.h>
using namespace std;
const int maxn = 2e5 + 9;
long long a[maxn], seg_tree[maxn << 2], tag[maxn << 2];
void push_up_sum(long long p)
{
    seg_tree[p] = seg_tree[p << 1] + seg_tree[p << 1 | 1];
}
void build(long long p, long long l, long long r)
{
    tag[p] = 0;
    if (l == r)
    {
        seg_tree[p] = a[l];
        return;
    }
    long long mid = (l + r) >> 1;
    build(p << 1, l, mid);
    build(p << 1 | 1, mid + 1, r);
    push_up_sum(p);
}
void flag(long long p, long long l, long long r, long long k)
{
    tag[p] = tag[p] + k;
    seg_tree[p] = seg_tree[p] + k * (r - l + 1);
}
void push_down(long long p, long long l, long long r)
{
    long long mid = (l + r) >> 1;
    flag(p << 1, l, mid, tag[p]);
    flag(p << 1 | 1, mid + 1, r, tag[p]);
    tag[p] = 0;
}
void update(long long newl, long long newr, long long l, long long r, long long p, long long k)
{
    if (newl <= l && r <= newr)
    {
        seg_tree[p] += k * (r - l + 1);
        tag[p] += k;
        return;
    }
    push_down(p, l, r);
    long long mid = (l + r) >> 1;
    if (newl <= mid)
        update(newl, newr, l, mid, p << 1, k);
    if (newr > mid)
        update(newl, newr, mid + 1, r, p << 1 | 1, k);
    push_up_sum(p);
}
long long query(long long q_x, long long q_y, long long l, long long r, long long p)
{
    if (q_x <= l && r <= q_y)
        return seg_tree[p];
    long long ans = 0, mid = (l + r) >> 1;
    push_down(p, l, r);
    if (q_x <= mid)
        ans += query(q_x, q_y, l, mid, p << 1);
    if (q_y > mid)
        ans += query(q_x, q_y, mid + 1, r, p << 1 | 1);
    return ans;
}
int main()
{
    std::ios::sync_with_stdio(false);
    std::cin.tie(nullptr);
    int n, m, k;
    long long b, c, d;
    cin >> n >> m;
    for (int i = 1; i <= n; i++)
        cin>> a[i];
    build(1, 1, n);
    while (m--)
    {
        cin >> k;
        if (k == 1)
        {
            cin >> b >> c >> d;
            update(b, c, 1, n, 1, d);
        }
        else if (k == 2)
        {
            cin >> d;
            update(1, 1, 1, n, 1, d);
        }
        else if (k == 3)
        {
            cin >> d;
            update(1, 1, 1, n, 1, -d);
        }
        else if (k == 4)
        {
            cin >> b >> c;
            cout << query(b, c, 1, n, 1) << '\n';
        }
        else if (k == 5)
        {
            cout << query(1, 1, 1, n, 1) << '\n';
        }
    }
    return 0;
}
