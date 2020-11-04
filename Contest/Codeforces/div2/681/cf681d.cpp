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
const int maxn = 1e5 + 9;
const ll mod = 998244353;
int a[maxn], v[maxn];
signed main()
{
    //freopen("in.txt","r",stdin);
    //freopen("data.txt","w",stdout);
    //std::ios::sync_with_stdio(false);
    //std::cin.tie(0);
    //std::cout.tie(0);
    int T;
    read(T);
    while (T--)
    {
        int n, tot = 0;
        memset(v, 0, sizeof(v));
        read(n);
        for (int i = 1; i <= n; ++i)
        {
            read(a[i]);
            if (a[i] != a[i - 1])
                v[++tot] = a[i];
        }
        bool flag = true;
        int tmp = inf, pos = -1;
        for (int i = 1; i <= tot; ++i)
        {
            if (tmp >= v[i])
            {
                tmp = v[i];
                v[i] = 0;
            }
            else
            {
                v[i] -= tmp;
                tmp = min(tmp, max(0, v[i + 1] - v[i]));
            }
            //cout << i << ' ' << tmp << ' ' << pos << endl;
        }
        tmp = v[tot];
        for (int i = tot; i > 0; --i)
        {
            if (tmp >= v[i])
            {
                tmp = v[i];
                v[i] = 0;
            }
            else
                flag = false;
        }
        puts(flag ? "YES" : "NO");
    }
    //fclose(stdin);
    //fclose(stdout);
    return 0;
}