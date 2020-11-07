#include <bits/stdc++.h>
using namespace std;
int dp[32];
inline int read()
{
    int data = 0;
    char ch = getchar();
    while (!isdigit(ch))
        ch = getchar();
    while (isdigit(ch))
    {
        data = data * 10 + ch - '0';
        ch = getchar();
    }
    return data;
}
int main()
{
    int n = read(), x, k, ans = 0;
    for (register int i = 1; i <= n; ++i)
    {
        x = read();
        k = 1;
        for (register int j = 0; j <= 30; ++j)
            if ((1 << j) & x)
                k = max(k, dp[j] + 1);
        for (register int j = 0; j <= 30; ++j)
            if ((1 << j) & x)
                dp[j] = max(dp[j], k);
        ans = max(ans, k);
    }
    cout << ans << '\n';
    return 0;
}