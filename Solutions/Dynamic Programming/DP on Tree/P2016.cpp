#include <bits/stdc++.h>
using namespace std;
typedef vector<int> vi;
const int maxn = 2009;
int n;
int dp[maxn][2], in[maxn];
vi g[maxn];
void dfs(int fa)
{
    dp[fa][1] = 1;
    for (auto i : g[fa])
    {
        dfs(i);
        dp[fa][0] += dp[i][1];
        dp[fa][1] += min(dp[i][0], dp[i][1]);
    }
}
int main()
{
    cin >> n;
    for (int i = 1, a, b; i <= n; ++i)
    {
        cin >> a >> b;
        a++;
        for (int j = 1, x; j <= b; ++j)
        {
            cin >> x;
            x++;
            g[a].push_back(x);
            in[x]++;
        }
    }
    int root;
    for (int i = 1; i <= n; ++i)
    {
        if (in[i] == 0)
        {
            root = i;
            break;
        }
    }
    dfs(root);
    cout << min(dp[root][0], dp[root][1]) << endl;

    return 0;
}