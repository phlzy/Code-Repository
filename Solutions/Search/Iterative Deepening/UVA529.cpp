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
ll n, maxdep, ans[maxn];

bool dfs(int dep)
{
    if (dep > maxdep)
        return ans[dep - 1] == n;
    if (ans[dep - 1] * (1LL << (maxdep - dep + 1)) < n)
        return false;
    for (int i = 0; i < dep; ++i)
    {
        for (int j = i; j < dep; ++j)
        {
            ll t = ans[i] + ans[j];
            if (t > n)
                break;
            if (t <= ans[dep - 1])
                continue;
            ans[dep] = t;
            if (dfs(dep + 1))
                return true;
        }
    }
    return false;
}

signed main()
{
    //freopen("in.txt","r",stdin);
    //freopen("data.txt","w",stdout);
    //std::ios::sync_with_stdio(false);
    //std::cin.tie(0);
    //std::cout.tie(0);
    ans[0] = 1;
    while (scanf("%lld", &n) && n)
    {
        printf("1");
        for (maxdep = 0;; ++maxdep)
        {
            if (dfs(1))
            {
                for (int i = 1; i <= maxdep; ++i)
                    printf(" %lld", ans[i]);
                printf("\n");
                break;
            }
        }
    }
    //fclose(stdin);
    //fclose(stdout);
    return 0;
}