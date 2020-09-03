#include <bits/stdc++.h>
using namespace std;

typedef long long ll;
typedef unsigned long long ull;
typedef pair<int, int> pii;
typedef vector<int> vi;
typedef vector<ll> vll;

const int inf = 0x3f3f3f3f;
const double eps = 1e-8;
const int maxn = 1e6 + 9;

ll n, t, k, maxdep, flag;
ll ans[maxn], ma, mb, w[maxn];
set<ll> p;

bool check()
{
    for (int i = maxdep; i > 0; i--)
    {
        if (ans[i] != w[i])
        {
            if (ans[i] == 0 || ans[i] > w[i])
                return 0;
            return 1;
        }
    }
    return 1;
}

void dfs(int dep, ll a, ll b, ll flr)
{
    if (dep >= maxdep)
    {
        if (b % a || p.count(b / a))
            return;
        w[dep] = b / a;
        if (!check())
            memcpy(ans, w, sizeof(w));
        flag = 1;
    }
    else
    {
        for (ll i = max(flr, b / a + 1);; i++)
        {
            if (p.count(i))
                continue;
            if (a * i >= b * (maxdep - dep + 1))
                return;
            ll na = a * i - b, nb = b * i;
            ll g = __gcd(na, nb);
            w[dep] = i;
            dfs(dep + 1, na / g, nb / g, i + 1);
        }
    }
}

signed main()
{
    //freopen("in.txt","r",stdin);
    //freopen("data.txt","w",stdout);
    //std::ios::sync_with_stdio(false);
    //std::cin.tie(0);
    //std::cout.tie(0);
    int T;
    scanf("%lld", &T);
    for (int i = 1; i <= T; i++)
    {
        ll x;
        scanf("%lld %lld %lld", &ma, &mb, &k);
        for (int j = 1; j <= k; j++)
        {
            scanf("%lld", &x);
            p.insert(x);
        }
        for (maxdep = 2;; maxdep++)
        {
            memset(ans, 0, sizeof(ans));
            dfs(1, ma, mb, mb / ma + 1);
            if (flag)
                break;
        }
        printf("Case %d: %lld/%lld=1/%lld", i, ma, mb, ans[1]);
        for (int j = 2; j <= maxdep; j++)
            printf("+1/%lld", ans[j]);
        printf("\n");
        flag = 0;
        p.clear();
    }
    //fclose(stdin);
    //fclose(stdout);
    return 0;
}