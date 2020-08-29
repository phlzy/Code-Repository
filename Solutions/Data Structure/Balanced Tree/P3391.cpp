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
    int fa, sz, cnt, val, tag;
    int son[2];
    //node(){}
};
node tree[maxn];
int ori[maxn], root, wz;
inline bool which(int x)
{
    return x == tree[tree[x].fa].son[1]; //* left:0 right:1
}
inline void update(int x)
{
    if (x == 0)
        return;
    tree[x].sz = tree[x].cnt;
    if (tree[x].son[0])
        tree[x].sz += tree[tree[x].son[0]].sz;
    if (tree[x].son[1])
        tree[x].sz += tree[tree[x].son[1]].sz;
}
inline void push_down(int x)
{
    if (x && tree[x].tag)
    {
        tree[tree[x].son[0]].tag ^= 1;
        tree[tree[x].son[1]].tag ^= 1;
        swap(tree[x].son[0], tree[x].son[1]);
        tree[x].tag = 0;
    }
}
inline void rotate(int x)
{
    int f = tree[x].fa, gf = tree[f].fa;
    push_down(x), push_down(f); //* push down the tags
    bool w = which(x);
    tree[f].son[w] = tree[x].son[w ^ 1];
    tree[tree[f].son[w]].fa = f; //* conect son
    tree[f].fa = x;
    tree[x].fa = gf;
    tree[x].son[w ^ 1] = f; //* conect father
    if (gf != 0)
        tree[gf].son[tree[gf].son[1] == f] = x; //* update grandpa
    update(f);
}
void splay(int x, int to)
{
    while (tree[x].fa != to)
    {
        int y = tree[x].fa;
        if (tree[y].fa != to)
            rotate(which(x) == which(y) ? y : x);
        rotate(x);
    }
    if (to == 0)
        root = x; //! very important
}
int build(int l, int r, int fa)
{
    if (l > r)
        return 0;
    int mid = (l + r) >> 1;
    int now = ++wz;
    tree[now].fa = fa;
    //tree[now].son[0]=tree[now].son[1]=0;
    tree[now].cnt = 1;
    tree[now].val = ori[mid];
    tree[now].sz = 1;
    tree[now].son[0] = build(l, mid - 1, now);
    tree[now].son[1] = build(mid + 1, r, now);
    update(now);
    return now;
}
int find_val(int x)
{
    int now = root;
    while (1)
    {
        push_down(now);
        if (x <= tree[tree[now].son[0]].sz)
            now = tree[now].son[0];
        else
        {
            x -= tree[tree[now].son[0]].sz + 1;
            if (x == 0)
                return now;
            now = tree[now].son[1];
        }
    }
}
void reverse(int x, int y)
{
    int l = find_val(x - 1), r = find_val(y + 1);
    splay(l, 0);
    splay(r, l);
    int pos = tree[root].son[1];
    pos = tree[pos].son[0];
    tree[pos].tag ^= 1;
}
void show(int now)
{
    push_down(now); //* dfs
    if (tree[now].son[0])
        show(tree[now].son[0]);
    if (tree[now].val != -inf && tree[now].val != inf)
        cout << tree[now].val << ' ';
    if (tree[now].son[1])
        show(tree[now].son[1]);
}
signed main()
{
    //freopen("in.txt","r",stdin);
    //freopen("data.txt","w",stdout);
    //std::ios::sync_with_stdio(false);
    //std::cin.tie(0);
    //std::cout.tie(0);
    int n, m, x, y;
    cin >> n >> m;
    ori[1] = -inf, ori[n + 2] = inf;
    for (int i = 1; i <= n; ++i)
        ori[i + 1] = i;
    root = build(1, n + 2, 0);
    for (int i = 1; i <= m; i++)
    {
        cin >> x >> y;
        reverse(x + 1, y + 1);
        //show(root);
        //cout << endl;
    }
    show(root);
    //fclose(stdin);
    //fclose(stdout);
    return 0;
}