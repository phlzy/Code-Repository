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
