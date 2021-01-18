#include <bits/stdc++.h>
//#include <ext/pb_ds/assoc_container.hpp>
//#include <ext/pb_ds/tree_policy.hpp>
//#include <ext/pb_ds/priority_queue.hpp>
//#include <ext/pb_ds/hash_policy.hpp>
using namespace std;
//using namespace __gnu_pbds;

typedef long long ll;
typedef unsigned long long ull;
typedef pair<int, int> pii;
typedef vector<int> vi;
typedef vector<ll> vll;
//typedef gp_hash_table<int, int> hashmap;
//typedef tree<pii, null_type, std::less<pii>, splay_tree_tag, tree_order_statistics_node_update> splaytree;
//typedef tree<pii, null_type, std::less<pii>, rb_tree_tag, tree_order_statistics_node_update> rbtree;
//typedef __gnu_pbds::priority_queue<int, std::greater<int>, __gnu_pbds::binary_heap_tag> binheap;
//typedef __gnu_pbds::priority_queue<int, std::greater<int>, __gnu_pbds::pairing_heap_tag> pairingheap;

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
inline void read(T &maxt, Args &...args)
{
    read(maxt);
    read(args...);
}

//mt19937 rnd(time(0));
//const int inf = 0x3f3f3f3f;
const int inf = 999999;

const double eps = 1e-8;
const int maxn = 1e4 + 9;
const int maxm = 1e6 + 9;
const ll mod = 998244353;

int h1, h2, m1, m2, n;
int t[maxn], c[maxn], p[maxn], dp[maxn];
int cnt, cost[maxm], val[maxm];
void init()
{
    for (int i = 1; i <= n; ++i)
    {
        int tot = 1;
        while (p[i] > 0)
        {
            cost[++cnt] = tot * t[i];
            val[cnt] = tot * c[i];
            p[i] -= tot;
            tot *= 2;
            if (p[i] < tot)
            {
                cost[++cnt] = t[i] * p[i];
                val[cnt] = c[i] * p[i];
                break;
            }
        }
    }
}

signed main()
{
    //freopen("in.txt","r",stdin);
    //freopen("data.txt","w",stdout);
    //std::ios::sync_with_stdio(false);
    //std::cin.tie(0);
    //std::cout.tie(0);
    scanf("%d:%d%d:%d%d", &h1, &m1, &h2, &m2, &n);
    int maxt = (h2 * 60 + m2) - (60 * h1 + m1);
    for (int i = 1; i <= n; ++i)
    {
        scanf("%d%d%d", &t[i], &c[i], &p[i]);
        if (p[i] == 0)
            p[i] = inf;
    }
    init();
    for (int i = 1; i <= cnt; ++i)
    {
        for (int j = maxt; j >= cost[i]; --j)
            dp[j] = max(dp[j], dp[j - cost[i]] + val[i]);
    }
    printf("%d\n", dp[maxt]);
    //fclose(stdin);
    //fclose(stdout);
    return 0;
}