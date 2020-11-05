#include <bits/stdc++.h>
using namespace std;
const int maxn = 2e5 + 9;
int h[maxn], cost[maxn];
signed main()
{
    int n, a, b, k, ans = 0;
    cin >> n >> a >> b >> k;
    for (int i = 1; i <= n; ++i)
    {
        cin >> h[i];
        h[i] = (h[i] - 1) % (a + b);
        cost[i] = h[i] / a;
    }
    sort(cost + 1, cost + n + 1);
    for (int i = 1; i <= n; ++i)
    {
        k -= cost[i];
        if (k < 0)
            break;
        ans++;
    }
    cout << ans << '\n';
    return 0;
}