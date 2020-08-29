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

signed main()
{
    //freopen("in.txt", "r", stdin);
    //freopen("data.txt","w",stdout);
    //std::ios::sync_with_stdio(false);
    //std::cin.tie(0);
    //std::cout.tie(0);
    int n, m;
    cin >> n >> m;
    int sum = m + n;
    if (n < sum / 3 || m < sum / 2)
    {
        puts("-1");
        return 0;
    }
    int max0 = sum / 2, min1 = sum / 2;
    int min0 = sum / 3, max1 = min0 * 2;
    //cout << min0 << ' ' << max0 << ' ' << min1 << ' ' << max1 << endl;
    if (n > max0 && m == min1)
    {
        for (int i = 1; i <= max0; ++i)
        {
            putchar('0');
            putchar('1');
        }
        putchar('0');
        return 0;
    }
    if (m > max1 && n == min0)
    {
        for (int i = 1; i <= min0; ++i)
        {
            putchar('1');
            putchar('1');
            putchar('0');
        }
        for (int i = 1; i <= m - max1; ++i)
            putchar('1');
        return 0;
    }
    int a = m - n, b = n - a;
    for (int i = 1; i <= a; ++i)
    {
        putchar('1');
        putchar('1');
        putchar('0');
    }
    for (int i = 1; i <= b; ++i)
    {
        putchar('1');
        putchar('0');
    }
    //fclose(stdin);
    //fclose(stdout);
    return 0;
}