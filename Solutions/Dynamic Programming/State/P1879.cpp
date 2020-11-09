#include <bits/stdc++.h>
using namespace std;

typedef long long ll;
typedef unsigned long long ull;
typedef pair<int, int> pii;
typedef vector<int> vi;
const int inf = 0x3f3f3f3f;
const double eps = 1e-8;
const int maxn = 1e5 + 9;
const ll mod = 1e8;
int a[25][25], f[30], g[maxn], dp[25][10000];
signed main()
{
    //freopen("in.txt","r",stdin);
    //freopen("data.txt","w",stdout);
    //std::ios::sync_with_stdio(false);
    //std::cin.tie(0);
    //std::cout.tie(0);
    int n, m;
    cin >> m >> n;
    for (int i = 1; i <= m; ++i)
    {
        for (int j = 1; j <= n; ++j)
        {
            cin >> a[i][j];
            f[i] = (f[i] << 1) + a[i][j];
        }
    }
    for (int i = 0; i < (1 << n); ++i)
        g[i] = (!(i & (i << 1))) && (!(i & (i >> 1)));
    dp[0][0] = 1;
    for (int i = 1; i <= m; i++)
    {
        for (int j = 0; j < (1 << n); j++)
        {
            if (g[j] && ((j & f[i]) == j))
            {
                for (int k = 0; k < (1 << n); k++)
                {
                    if ((k & j) == 0)
                    {
                        dp[i][j] = (dp[i][j] + dp[i - 1][k]) % mod;
                    }
                }
            }
        }
    }
    int ans = 0;
    for (int i = 0; i < (1 << n); i++)
        ans = (ans + dp[m][i]) % mod;
    cout << ans << endl;
    //fclose(stdin);
    //fclose(stdout);
    return 0;
}