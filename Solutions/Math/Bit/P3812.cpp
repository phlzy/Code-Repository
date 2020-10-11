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
const int maxn = 2e5 + 9;
int n;
ll ans;
ll a[maxn], p[maxn];
void get_base(ll x)
{
    for (ll i = 62; i >= 0; --i)
    {
        if (!(x >> i))
            continue;
        if (!p[i])
        {
            p[i] = x;
            break;
        }
        x ^= p[i];
    }
}
signed main()
{
    //freopen("in.txt","r",stdin);
    //freopen("data.txt","w",stdout);
    //std::ios::sync_with_stdio(false);
    //std::cin.tie(0);
    //std::cout.tie(0);
    read(n);
    for (int i = 1; i <= n; ++i)
    {
        read(a[i]);
        get_base(a[i]);
    }
    for (ll i = 62; i >= 0; --i)
    {
        if ((ans ^ p[i]) > ans)
            ans ^= p[i];
    }
    printf("%lld\n", ans);
    //fclose(stdin);
    //fclose(stdout);
    return 0;
}