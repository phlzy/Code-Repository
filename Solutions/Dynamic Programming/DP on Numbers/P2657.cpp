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

const int inf = 0x3f3f3f3f;
const double eps = 1e-8;
const int maxn = 1e5 + 9;

ll dp[15][15];
int a[15], len;

ll dfs(
    int pos,   /* 当前位置 */
    int pre,   /* 上一位 */
    bool st,   /* 前导0 */
    bool limit /* 最高位限制 */
)
{
    if (pos > len)
        return 1; 
    if (!limit && dp[pos][pre] != -1)
        return dp[pos][pre]; //* 没有最高位限制，已经搜过了
    ll ret = 0;
    int maxx = limit ? a[len - pos + 1] : 9;
    for (int i = 0; i <= maxx; i++)
    {
        if (abs(i - pre) < 2)
            continue;
        else if (st && i == 0) //* 判断前导0
            ret += dfs(pos + 1, -2, 1, limit && i == maxx);
        else
            ret += dfs(pos + 1, i, 0, limit && i == maxx);
    }
    if (!limit && !st)
        dp[pos][pre] = ret; //* 没有最高位限制且没有前导0时记录结果
    return ret;
}

ll solve(ll x)
{
    len = 0;
    while (x)
        a[++len] = x % 10, x /= 10;
    memset(dp, -1, sizeof(dp));
    return dfs(1, -2, 1, 1);
}

signed main()
{
    //freopen("in.txt","r",stdin);
    //freopen("data.txt","w",stdout);
    //std::ios::sync_with_stdio(false);
    //std::cin.tie(0);
    //std::cout.tie(0);
    ll l, r;
    cin >> l >> r;
    cout << solve(r) - solve(l - 1) << endl;
    //fclose(stdin);
    //fclose(stdout);
    return 0;
}