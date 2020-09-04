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
struct node
{
    int ls, rs, val, size, rnd, freq;
} treap[maxn];
int n, cnt, root;
inline void update(int k)
{
    treap[k].size = treap[treap[k].ls].size + treap[treap[k].rs].size + treap[k].freq;
}
inline void rotate_l(int &k)
{
    int t = treap[k].rs;
    treap[k].rs = treap[t].ls;
    treap[t].ls = k;
    treap[t].size = treap[k].size;
    update(k);
    k = t;
}
inline void rotate_r(int &k)
{
    int t = treap[k].ls;
    treap[k].ls = treap[t].rs;
    treap[t].rs = k;
    treap[t].size = treap[k].size;
    update(k);
    k = t;
}
void insert(int &k, int x)
{
    if (k == 0)
    {
        cnt++;
        k = cnt;
        treap[k].size = treap[k].freq = 1;
        treap[k].val = x;
        treap[k].rnd = rand();
        return;
    }
    treap[k].size++;
    if (treap[k].val == x)
        treap[k].freq++;
    else if (x > treap[k].val)
    {
        insert(treap[k].rs, x);
        if (treap[treap[k].rs].rnd < treap[k].rnd)
            rotate_l(k);
    }
    else
    {
        insert(treap[k].ls, x);
        if (treap[treap[k].ls].rnd < treap[k].rnd)
            rotate_r(k);
    }
}
void del(int &k, int x)
{
    if (k == 0)
        return;
    if (treap[k].val == x)
    {
        if (treap[k].freq > 1)
        {
            treap[k].freq--;
            treap[k].size--;
            return;
        }
        if (treap[k].ls * treap[k].rs == 0)
            k = treap[k].ls + treap[k].rs;
        else if (treap[treap[k].ls].rnd < treap[treap[k].rs].rnd)
            rotate_r(k), del(k, x);
        else
            rotate_l(k), del(k, x);
    }
    else if (x > treap[k].val)
        treap[k].size--, del(treap[k].rs, x);
    else
        treap[k].size--, del(treap[k].ls, x);
}
int query_rank(int k, int x)
{
    if (k == 0)
        return 0;
    if (treap[k].val == x)
        return treap[treap[k].ls].size + 1;
    else if (x > treap[k].val)
        return treap[treap[k].ls].size + treap[k].freq + query_rank(treap[k].rs, x);
    else
        return query_rank(treap[k].ls, x);
}
int query_val(int k, int x)
{
    if (k == 0)
        return 0;
    if (x <= treap[treap[k].ls].size)
        return query_val(treap[k].ls, x);
    else if (x > treap[treap[k].ls].size + treap[k].freq)
        return query_val(treap[k].rs, x - treap[treap[k].ls].size - treap[k].freq);
    else
        return treap[k].val;
}
int query_pre(int k, int x)
{
    int t = k, ans = 0;
    while (t)
    {
        if (treap[t].val < x)
            ans = treap[t].val, t = treap[t].rs;
        else
            t = treap[t].ls;
    }
    return ans;
}
int query_suf(int k, int x)
{
    int t = k, ans = 0;
    while (t)
    {
        if (treap[t].val > x)
            ans = treap[t].val, t = treap[t].ls;
        else
            t = treap[t].rs;
    }
    return ans;
}
int main()
{
    std::ios::sync_with_stdio(false);
    std::cin.tie(0);
    cin >> n;
    insert(root, -2147483647);
    insert(root, 2147483647);
    int opt, x;
    for (int i = 1; i <= n; i++)
    {
        cin >> opt >> x;
        switch (opt)
        {
        case 1:
            cout << query_rank(root, x) << '\n';
            break;
        case 2:
            cout << query_val(root, x + 1) << '\n';
            break;
        case 3:
            cout << query_pre(root, x) << '\n';
            break;
        case 4:
            cout << query_suf(root, x) << '\n';
            break;
        case 5:
            insert(root, x);
            break;
        default:
            break;
        }
    }
    return 0;
}
