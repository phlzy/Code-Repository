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
struct tnode
{
    int ch[2], cnt, end;
    tnode()
    {
        ch[0] = ch[1] = 0;
        cnt = 0;
        end = 0;
    }
};
tnode trie[maxn];
int tot = 0;
void insert(int len)
{
    int root = 0;
    for (int i = 1, x; i <= len; ++i)
    {
        read(x);
        if (trie[root].ch[x])
            root = trie[root].ch[x];
        else
        {
            trie[root].ch[x] = ++tot;
            root = tot;
        }
        trie[root].cnt++;
    }
    trie[root].end++;
}
int search(int len)
{
    bool flag = true;
    int root = 0, ret = 0;
    for (int i = 1, x; i <= len; ++i)
    {
        read(x);
        if (trie[root].ch[x] && flag)
            root = trie[root].ch[x];
        else
            flag = false;
        if (flag)
            ret += trie[root].end;
    }
    return flag ? ret + trie[root].cnt - trie[root].end : ret;
}
signed main()
{
    //freopen("in.txt","r",stdin);
    //freopen("data.txt","w",stdout);
    //std::ios::sync_with_stdio(false);
    //std::cin.tie(0);
    //std::cout.tie(0);
    int n, m, len;
    read(n, m);
    for (int i = 1; i <= n; ++i)
    {
        read(len);
        insert(len);
    }
    for (int i = 1; i <= m; ++i)
    {
        read(len);
        printf("%d\n", search(len));
    }
    //fclose(stdin);
    //fclose(stdout);
    return 0;
}