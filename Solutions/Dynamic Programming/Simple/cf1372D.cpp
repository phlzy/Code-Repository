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
const int maxn = 2e5 + 9;
int s[2 * maxn];
ll ans;
ll odd[maxn], even[maxn];
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
        read(s[i]);
        s[i + n] = s[i];
    }
    for (int i = 1; i <= 2 * n; ++i)
    {
        if (i & 1)
            odd[(1 + i) / 2] = odd[i / 2] + s[i];
        else
            even[i / 2] = even[i / 2 - 1] + s[i];
    }
    int gap = n / 2 + 1;
    for (int i = 0; i < gap; ++i)
        ans = max(ans, odd[i + gap] - odd[i]);
    for (int i = 0; i < gap; ++i)
        ans = max(ans, even[i + gap] - even[i]);
    cout << ans << '\n';
    //fclose(stdin);
    //fclose(stdout);
    return 0;
}