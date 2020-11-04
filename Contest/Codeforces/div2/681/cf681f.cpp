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
int a[maxn], b[maxn], mp[maxn];
struct node
{
    int val, l, r;
};
node s[maxn];
void del(int pos)
{
    mp[s[pos].val] = 0;

    s[s[pos].r].l = s[pos].l;
    s[s[pos].l].r = s[pos].r;
}
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
        int n, k;
        read(n, k);
        memset(mp, 0, sizeof(int) * (n + 2));
        memset(s, 0, sizeof(node) * (n + 2));
        a[n + 1] = n + 1;
        s[0].r = 1, s[n + 1].l = n;
        mp[0] = mp[n + 1] = n + 1;

        for (int i = 1; i <= n; ++i)
        {
            read(s[i].val);
            a[s[i].val] = i;
            s[i].l = i - 1;
            s[i].r = i + 1;
        }
        for (int i = 1; i <= k; ++i)
        {
            read(b[i]);
            mp[b[i]] = i;
        }
        ll ans = 1;
        bool flag = true;
        for (int i = 1; i <= k; ++i)
        {
            int now = b[i], pos = a[now];
            if (mp[s[s[pos].l].val] && mp[s[s[pos].r].val])
            {
                //cout << i << endl;
                flag = false;
                break;
            }
            else if (mp[s[s[pos].l].val] == 0 && mp[s[s[pos].r].val])
            {
                if (s[pos].l == 0)
                {
                    //cout << i << endl;
                    flag = false;
                    break;
                }
                del(pos);
            }
            else if (mp[s[s[pos].l].val] && mp[s[s[pos].r].val] == 0)
            {
                if (s[pos].r == n + 1)
                {
                    //cout << i << endl;
                    flag = false;
                    break;
                }
                del(pos);
            }
            else
            {
                if (s[pos].l == 0 || s[pos].r == n + 1)
                {
                    del(pos);
                }
                else
                {
                    del(pos);
                    ans = ans * 2 % mod;
                }
            }
            //cout << ans << endl;
        }
        if (!flag)
            printf("0\n");
        else
            printf("%lld\n", ans);
    }
    //fclose(stdin);
    //fclose(stdout);
    return 0;
}