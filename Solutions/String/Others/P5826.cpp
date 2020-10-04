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
const int maxn = 1e5 + 9;

vi v[maxn];
signed main()
{
    //freopen("in.txt","r",stdin);
    //freopen("data.txt","w",stdout);
    //std::ios::sync_with_stdio(false);
    //std::cin.tie(0);
    //std::cout.tie(0);
    int n, q, x;
    read(x, n, q, x);
    for (int i = 1; i <= n; ++i)
    {
        read(x);
        v[x].emplace_back(i);
    }
    while (q--)
    {
        read(x);
        int pos = 0, tmp;
        bool flag = true;
        for (int i = 1; i <= x; ++i)
        {
            read(tmp);
            if (!flag)
                continue;
            auto it = upper_bound(v[tmp].begin(), v[tmp].end(), pos);
            if (it == v[tmp].end())
                flag = false;
            else
                pos = *it;
        }
        puts(flag ? "Yes" : "No");
    }

    //fclose(stdin);
    //fclose(stdout);
    return 0;
}