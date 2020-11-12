#include <bits/stdc++.h>
using namespace std;
#define int ll
typedef long long ll;
typedef unsigned long long ull;
typedef pair<int, int> pii;
typedef vector<int> vi;
const int inf = 0x3f3f3f3f;
const double eps = 1e-8;
const int maxn = 1e5 + 9;
const ll mod = 9999973;
ll n, m, ans;
ll dp[120][120][120];
inline ll calc(ll x)
{
    return x * (x - 1) / 2 % mod;
}
signed main()
{
    //freopen("in.txt","r",stdin);
    //freopen("data.txt","w",stdout);
    //std::ios::sync_with_stdio(false);
    //std::cin.tie(0);
    //std::cout.tie(0);
    cin >> n >> m;
    dp[0][0][0] = 1;
    for (int i = 1; i <= n; ++i)
    {
        for (int j = 0; j <= m; ++j)
        {
            for (int k = 0; k <= m - j; ++k)
            {
                dp[i][j][k] = dp[i - 1][j][k] % mod;
                if (k >= 1)
                    dp[i][j][k] = (dp[i][j][k] + dp[i - 1][j + 1][k - 1] * (j + 1)) % mod;
                if (j >= 1)
                    dp[i][j][k] = (dp[i][j][k] + dp[i - 1][j - 1][k] * (m - j - k + 1)) % mod;
                if (k >= 2)
                    dp[i][j][k] = (dp[i][j][k] + dp[i - 1][j + 2][k - 2] * (((j + 2) * (j + 1)) / 2)) % mod;
                if (k >= 1)
                    dp[i][j][k] = (dp[i][j][k] + dp[i - 1][j][k - 1] * j * (m - j - k + 1)) % mod;
                if (j >= 2)
                    dp[i][j][k] = (dp[i][j][k], dp[i - 1][j - 2][k] * calc(m - j - k + 2)) % mod;
                
                //cout << i << ' ' << dp[i][j][k] << endl;
            }
        }
    }
    for (int i = 0; i <= m; ++i)
    {
        for (int j = 0; j <= m; ++j)
        {
            ans = (ans + dp[n][i][j]) % mod;
        }
    }
    cout << (ans + mod) % mod << endl;
    //fclose(stdin);
    //fclose(stdout);
    return 0;
}