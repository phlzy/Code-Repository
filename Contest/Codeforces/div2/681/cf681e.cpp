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
inline void read(T &t, Args &... args)
{
    read(t);
    read(args...);
}

//mt19937 rnd(time(0));
const int inf = 0x3f3f3f3f;
const double eps = 1e-8;
const int maxn = 2e5 + 9;
const ll mod = 998244353;
ll frac[20];
ll s[maxn];
bool vis[maxn];
signed main()
{
    //freopen("in.txt","r",stdin);
    //freopen("data.txt","w",stdout);
    //std::ios::sync_with_stdio(false);
    //std::cin.tie(0);
    //std::cout.tie(0);
    int n, q;
    read(n, q);
    frac[0] = 1;
    for (ll i = 1; i <= 16; ++i)
        frac[i] = frac[i - 1] * i;
    int st = max(1, n - 15);
    ll cnt = 1;
    for (ll i = 1; i <= n; ++i)
    {
        s[i] = s[i - 1] + i;
    }
    int op, l, r, x;
    while (q--)
    {
        read(op);
        if (op == 1)
        {
            read(l, r);
            printf("%lld\n", s[r] - s[l - 1]);
        }
        else
        {
            read(x);
            cnt += x;
            ll now = cnt;
            for (int i = st; i <= n; ++i)
                vis[i] = false;
            for (int i = st; i < n; ++i)
            {
                ll tmp = 0;
                for (int j = st; j <= n; ++j)
                {
                    if (!vis[j])
                    {
                        tmp += frac[n - i];
                        if (tmp >= now)
                        {
                            now -= tmp - frac[n - i];
                            s[i] = s[i - 1] + j;
                            vis[j] = true;
                            break;
                        }
                    }
                }
            }
            for (int i = st; i <= n; ++i)
                if (!vis[i])
                    s[n] = s[n - 1] + i;
        }
    }
    //fclose(stdin);
    //fclose(stdout);
    return 0;
}