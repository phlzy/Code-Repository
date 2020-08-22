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
const int maxn = 3e5 + 9;
map<pii, int> mp;
int row[maxn], col[maxn];
signed main()
{
    //freopen("in.txt", "r", stdin);
    //freopen("data.txt", "w", stdout);
    //std::ios::sync_with_stdio(false);
    //std::cin.tie(0);
    //std::cout.tie(0);
    int n, m, s;
    read(n), read(m), read(s);
    for (int i = 1; i <= s; ++i)
    {
        int x, y;
        read(x), read(y);
        mp[make_pair(x, y)]++;
        row[x]++, col[y]++;
    }
    int maxh = *max_element(row + 1, row + n + 1);
    int maxw = *max_element(col + 1, col + m + 1);
    vi h, w;
    for (int i = 1; i <= n; ++i)
    {
        if (row[i] == maxh)
            h.emplace_back(i);
    }
    for (int i = 1; i <= m; ++i)
    {
        if (col[i] == maxw)
            w.emplace_back(i);
    }
    for (int i = 0; i < h.size(); ++i)
        for (int j = 0; j < w.size(); ++j)
            if (mp[make_pair(h[i], w[j])] == 0)
            {
                cout << maxh + maxw << endl;
                return 0;
            }
    cout << maxh + maxw - 1 << endl;

    //fclose(stdin);
    //fclose(stdout);
    return 0;
}