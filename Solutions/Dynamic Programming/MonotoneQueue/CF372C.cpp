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
inline void read(T &t, Args &...args)
{
    read(t);
    read(args...);
}

//mt19937 rnd(time(0));
const int inf = 0x3f3f3f3f;
const ll linf = 0x3f3f3f3f3f3f3f3f;
const double eps = 1e-8;
const int maxn = 150000 + 9;
const int maxm = 309;
const ll mod = 998244353;

ll a[maxm], b[maxm], t[maxm];
ll dp[maxn], tmp[maxn], que[maxn];

signed main()
{
    //freopen("in.txt","r",stdin);
    //freopen("data.txt","w",stdout);
    //std::ios::sync_with_stdio(false);
    //std::cin.tie(0);
    //std::cout.tie(0);
    ll n, m, d, ans = -linf;
    read(n, m, d);
    ll sz = max(n, m) + 1;
    for (int i = 1; i <= m; ++i)
        read(a[i], b[i], t[i]);
    for (int i = 1; i <= m; ++i)
    {
        ll l = 1, r = 0, pos = 1, x = (t[i] - t[i - 1]) * d;
        for (int j = 1; j <= n; ++j)
        {
            for (; pos <= min(n, x + j); ++pos)
            {
                while (l <= r && tmp[que[r]] <= tmp[pos])
                    r--;
                que[++r] = pos;
            }
            while (l <= r && que[l] < j - x)
                l++;
            dp[j] = tmp[que[l]] + b[i] - abs(a[i] - j);
        }
        memcpy(tmp, dp, sizeof(ll) * sz);
    }
    for (int i = 1; i <= n; ++i)
        ans = max(ans, dp[i]);
    cout << ans << endl;
    //fclose(stdin);
    //fclose(stdout);
    return 0;
}