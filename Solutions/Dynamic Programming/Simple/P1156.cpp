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
const int maxn = 155;
const int mod = 1e9 + 7;

struct trash
{
    int t, h, l;
    bool operator<(const trash &b) const
    {
        return t < b.t;
    }
};
trash a[maxn];
int n, m;
int t[maxn], dp[maxn];
signed main()
{
    //freopen("in.txt","r",stdin);
    //freopen("data.txt","w",stdout);
    //std::ios::sync_with_stdio(false);
    //std::cin.tie(0);
    //std::cout.tie(0);
    scanf("%d%d", &n, &m);
    for (int i = 1; i <= m; ++i)
    {
        scanf("%d%d%d", &a[i].t, &a[i].l, &a[i].h);
    }
    sort(a + 1, a + m + 1);
    dp[0] = 10;
    for (int i = 1; i <= m; ++i)
    {
        for (int j = n; j >= 0; --j)
        {
            if (dp[j] >= a[i].t)
            {
                if (j + a[i].h >= n)
                {
                    printf("%d\n", a[i].t);
                    return 0;
                }
                dp[j + a[i].h] = max(dp[j + a[i].h], dp[j]);
                dp[j] += a[i].l;
            }
        }
    }
    printf("%d\n", dp[0]);
    //fclose(stdin);
    //fclose(stdout);
    return 0;
}