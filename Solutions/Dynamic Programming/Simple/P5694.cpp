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
const double eps = 1e-8;
const int maxn = 1e5 + 9;
const ll mod = 11380;

ll dp[15][15][15][50];
int i, j, k, d;

signed main()
{
    //freopen("in.txt","r",stdin);
    //freopen("data.txt","w",stdout);
    //std::ios::sync_with_stdio(false);
    //std::cin.tie(0);
    //std::cout.tie(0);
    cin >> i >> j >> k >> d;
    for (int ii = 0; ii <= i; ++ii)
    {
        for (int jj = 0; jj <= j; ++jj)
        {
            for (int kk = 0; kk <= k; ++kk)
            {
                for (int dd = 0; dd <= d; ++dd)
                {
                    if (ii == 0 && jj == 0 && kk == 0)
                    {
                        dp[ii][jj][kk][dd] = 1;
                        continue;
                    }
                    if (dd == 0)
                        continue;
                    for (int a = 0; a < ii; ++a)
                    {
                        for (int b = 0; b <= jj; ++b)
                        {
                            for (int c = 0; c <= kk; ++c)
                            {
                                dp[ii][jj][kk][dd] += dp[a][b][c][dd - 1] * dp[ii - a - 1][jj - b][kk - c][dd] % mod;
                                dp[ii][jj][kk][dd] %= mod;
                            }
                        }
                    }
                    for (int b = 0; b < jj; ++b)
                    {
                        for (int c = 0; c <= kk; ++c)
                        {
                            dp[ii][jj][kk][dd] += dp[0][b][c][dd - 1] * dp[ii][jj - b - 1][kk - c][dd] % mod;
                            dp[ii][jj][kk][dd] %= mod;
                        }
                    }
                    for (int c = 0; c < kk; ++c)
                    {
                        dp[ii][jj][kk][dd] += dp[0][0][c][dd - 1] * dp[ii][jj][kk - c - 1][dd] % mod;
                        dp[ii][jj][kk][dd] %= mod;
                    }
                }
            }
        }
    }
    if (d == 0)
        cout << dp[i][j][k][d] << endl;
    else
        cout << (dp[i][j][k][d] - dp[i][j][k][d - 1] + mod) % mod << endl;
    //fclose(stdin);
    //fclose(stdout);
    return 0;
}