#include <bits/stdc++.h>
using namespace std;

typedef long long ll;
typedef unsigned long long ull;
typedef pair<int, int> pii;
typedef vector<int> vi;
const int inf = 0x3f3f3f3f;
const double eps = 1e-8;
const int maxn = 1e5 + 9;
const ll mod = 998244353;

int n, m, ans;
bool vis[maxn], num[maxn];
int a[maxn];
void work()
{
    memset(num, 0, sizeof(num));
    num[0] = true;
    int tmp = 0, tot = 0;
    for (int i = 1; i <= n; ++i)
    {
        if (vis[i])
            continue;
        for (int j = tot; j >= 0; --j)
            if (num[j] && !num[j + a[i]])
                num[j + a[i]] = true, tmp++;
        tot += a[i];
    }
    ans = max(ans, tmp);
}
void dfs(int now, int cnt)
{
    if (cnt > m)
        return;
    if (now == n)
    {
        if (cnt == m)
            work();
        return;
    }
    dfs(now + 1, cnt);
    vis[now + 1] = true;
    dfs(now + 1, cnt + 1);
    vis[now + 1] = false;
}

signed main()
{
    //freopen("in.txt","r",stdin);
    //freopen("data.txt","w",stdout);
    //std::ios::sync_with_stdio(false);
    //std::cin.tie(0);
    //std::cout.tie(0);
    cin >> n >> m;
    for (int i = 1; i <= n; ++i)
        cin >> a[i];
    dfs(0, 0);
    cout << ans << endl;
    //fclose(stdin);
    //fclose(stdout);
    return 0;
}