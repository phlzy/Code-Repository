/*
 * @Author: jiangly 
 * @Date: 2020-08-11 22:11:15 
 * @Last Modified by: Watson
 * @Last Modified time: 2020-08-11 22:14:57
 * C++17
 */
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

int main()
{
    std::ios::sync_with_stdio(false);
    std::cin.tie(nullptr);
    int t;
    cin >> t;
    while (t--)
    {
        int n;
        cin >> n;
        vector<vector<pair<int, int>>> e(n);
        int64_t S;
        cin >> S;
        for (int i = 0; i < n - 1; ++i)
        {
            int u, v, w;
            cin >> u >> v >> w;
            --u;
            --v;
            e[u].emplace_back(v, w);
            e[v].emplace_back(u, w);
        }
        vector<int> siz(n), weight(n);
        function<void(int, int)> dfs = [&](int u, int p) {
            if (p != -1 && e[u].size() == 1)
                siz[u] = 1;
            for (auto [v, w] : e[u])
            {
                if (v == p)
                    continue;
                weight[v] = w;
                dfs(v, u);
                siz[u] += siz[v];
            }
        };
        dfs(0, -1);
        for (int i = 1; i < n; ++i)
            S -= 1ll * siz[i] * weight[i];
        priority_queue<pair<int64_t, int>> h;
        for (int i = 1; i < n; ++i)
            h.emplace(1ll * siz[i] * ((weight[i] + 1) / 2), i);
        int ans = 0;
        while (S < 0)
        {
            auto [w, u] = h.top();
            h.pop();
            ++ans;
            S += w;
            weight[u] /= 2;
            h.emplace(1ll * siz[u] * ((weight[u] + 1) / 2), u);
        }
        cout << ans << "\n";
    }
    return 0;
}