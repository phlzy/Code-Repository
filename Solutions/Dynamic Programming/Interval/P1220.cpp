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
const int maxn = 55;
const int mod = 1e9 + 7;

int a[maxn], b[maxn], sum[maxn], n, m;
int dp[maxn][maxn][2];

signed main()
{
    //freopen("in.txt","r",stdin);
    //freopen("data.txt","w",stdout);
    //std::ios::sync_with_stdio(false);
    //std::cin.tie(0);
    //std::cout.tie(0);
    scanf("%d%d", &n, &m);
    memset(dp, 127, sizeof(dp));
    for (int i = 1; i <= n; i++)
    {
        scanf("%d%d", &a[i], &b[i]);
        sum[i] = sum[i - 1] + b[i];
    }
    dp[m][m][0] = dp[m][m][1] = 0;
    for (int l = 2; l <= n; l++)
        for (int i = 1; i + l - 1 <= n; i++)
        {
            int j = i + l - 1;
            dp[i][j][0] = min(dp[i + 1][j][0] + (a[i + 1] - a[i]) * (sum[i] + sum[n] - sum[j]),dp[i + 1][j][1] + (a[j] - a[i]) * (sum[i] + sum[n] - sum[j]));
            dp[i][j][1] = min(dp[i][j - 1][0] + (a[j] - a[i]) * (sum[i - 1] + sum[n] - sum[j - 1]),dp[i][j - 1][1] + (a[j] - a[j - 1]) * (sum[i - 1] + sum[n] - sum[j - 1]));
        }
    int ans = min(dp[1][n][0], dp[1][n][1]);
    printf("%d", ans);
    //fclose(stdin);
    //fclose(stdout);
    return 0;
}