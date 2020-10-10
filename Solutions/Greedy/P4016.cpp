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
const int maxn = 100 + 9;

ll a[maxn], s[maxn], avg;

signed main()
{
    //freopen("in.txt","r",stdin);
    //freopen("data.txt","w",stdout);
    //std::ios::sync_with_stdio(false);
    //std::cin.tie(0);
    //std::cout.tie(0);
    int n;
    read(n);
    for (int i = 1; i <= n; ++i)
    {
        read(a[i]);
        avg += a[i];
    }
    avg /= n;
    for (int i = 1; i <= n; ++i)
    {
        a[i] -= avg;
        s[i] = s[i - 1] + a[i];
    }
    sort(s + 1, s + n + 1);
    ll ans = 0;
    for (int i = 1; i <= n; ++i)
        ans += abs(s[n / 2 + 1] - s[i]);
    cout << ans << endl;
    //fclose(stdin);
    //fclose(stdout);
    return 0;
}