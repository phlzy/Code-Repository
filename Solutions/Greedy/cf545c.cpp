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
struct node
{
    ll x, d;
};

signed main()
{
    //freopen("in.txt","r",stdin);
    //freopen("data.txt","w",stdout);
    //std::ios::sync_with_stdio(false);
    //std::cin.tie(0);
    //std::cout.tie(0);
    int n, cnt = 2, tmp;
    read(n);
    vector<node> v(n + 1);
    for (int i = 1; i <= n; ++i)
    {
        read(v[i].x);
        read(v[i].d);
    }
    if (n == 1)
    {
        cout << 1 << endl;
        return 0;
    }
    for (int i = 2; i < n; ++i)
    {
        if (v[i].x - v[i].d > v[i - 1].x)
            cnt++;
        else if (v[i].d + v[i].x < v[i + 1].x)
            cnt++, v[i].x += v[i].d;
    }
    cout << cnt << endl;
    //fclose(stdin);
    //fclose(stdout);
    return 0;
}