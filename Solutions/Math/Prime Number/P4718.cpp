
#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
typedef unsigned long long ull;
typedef pair<int, int> pii;
typedef vector<int> vi;
typedef vector<ll> vll;
typedef __int128_t lll;

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

ll max_factor;

inline ll gcd(ll a, ll b)
{
    return !b ? a : gcd(b, a % b);
}

inline ll ksm(ll x, ll p, ll mod)
{
    ll ans = 1;
    while (p)
    {
        if (p & 1)
            ans = (lll)ans * x % mod;
        x = (lll)x * x % mod;
        p >>= 1;
    }
    return ans;
}

inline bool Miller_Rabin(ll x, ll b)
{
    ll k = x - 1;
    while (k)
    {
        ll cur = ksm(b, k, x);
        if (cur != 1 && cur != x - 1)
            return false;
        if ((k & 1) == 1 || cur == x - 1)
            return true;
        k >>= 1;
    }
    return true;
}

inline bool isprime(ll x)
{
    if (x == 46856248255981ll || x < 2)
        return false;
    if (x == 2 || x == 3 || x == 7 || x == 61 || x == 24251)
        return true;
    return Miller_Rabin(x, 2) && Miller_Rabin(x, 61);
}
/*
ll calc(ll x, ll c, ll n)
{
    return ((lll)x * x + c) % n;
}
*/
inline ll Pollard_Rho(ll x)
{
    ll s = 0, t = 0, c = 1ll * rand() % (x - 1) + 1;
    ll val = 1;
    for (register int goal = 1;; goal <<= 1, s = t, val = 1)
    {
        for (register int stp = 1; stp <= goal; ++stp)
        {
            //t = calc(t, c, x);
            t = (ll)((lll)t * t + c) % x;
            val = (lll)val * abs(t - s) % x;
            if ((stp % 127) == 0)
            {
                ll d = gcd(val, x);
                if (d > 1)
                    return d;
            }
        }
        ll d = gcd(val, x);
        if (d > 1)
            return d;
    }
}

inline void fac(ll x)
{
    if (x <= max_factor || x < 2)
        return;
    if (isprime(x))
    {
        max_factor = max_factor > x ? max_factor : x;
        return;
    }
    ll p = x;
    while (p >= x)
        p = Pollard_Rho(x);
    while ((x % p) == 0)
        x /= p;
    fac(x), fac(p);
}

signed main()
{
    //freopen("in.txt","r",stdin);
    //freopen("data.txt","w",stdout);
    //std::ios::sync_with_stdio(false);
    //std::cin.tie(0);
    //std::cout.tie(0);
    int T;
    ll n;
    read(T);
    while (T--)
    {
        srand((unsigned)time(nullptr));
        read(n);
        max_factor = 0;
        fac(n);
        if (max_factor == n)
            puts("Prime");
        else
            cout << max_factor << '\n';
    }
    //fclose(stdin);
    //fclose(stdout);
    return 0;
}
