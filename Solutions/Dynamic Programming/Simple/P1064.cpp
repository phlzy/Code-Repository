#include <bits/stdc++.h>
using namespace std;

typedef long long ll;
typedef unsigned long long ull;
typedef pair<int, int> pii;
typedef vector<int> vi;
const int inf = 0x3f3f3f3f;
const double eps = 1e-8;
const int maxn = 4e4;
const ll mod = 998244353;
int n, m;
int mw[maxn], mv[maxn], fw[maxn][3], fv[maxn][3], dp[maxn];
// struct Edge
// {
//     int from, to;
//     Edge() = default;
//     Edge(int a, int b) : from(a), to(b) {}
// };
signed main()
{
    //freopen("in.txt","r",stdin);
    //freopen("data.txt","w",stdout);
    //std::ios::sync_with_stdio(false);
    //std::cin.tie(0);
    //std::cout.tie(0);
    cin >> n >> m;
    for (int i = 1; i <= m; ++i)
    {
        int v, p, q;
        cin >> v >> p >> q;
        if (q == 0)
        {
            mw[i] = v;
            mv[i] = v * p;
        }
        else
        {
            fw[q][0]++;
            fw[q][fw[q][0]] = v;
            fv[q][fw[q][0]] = v * p;
        }
    }
    for (int i = 1; i <= m; ++i)
    {
        for (int j = n; j >= mw[i]; --j)
        {
            dp[j] = max(dp[j], dp[j - mw[i]] + mv[i]);
            if (j >= mw[i] + fw[i][1])
                dp[j] = max(dp[j], dp[j - mw[i] - fw[i][1]] + mv[i] + fv[i][1]);
            if (j >= mw[i] + fw[i][2])
                dp[j] = max(dp[j], dp[j - mw[i] - fw[i][2]] + mv[i] + fv[i][2]);
            if (j >= mw[i] + fw[i][1] + fw[i][2])
                dp[j] = max(dp[j], dp[j - mw[i] - fw[i][1] - fw[i][2]] + mv[i] + fv[i][1] + fv[i][2]);
        }
    }
    cout << dp[n] << endl;
    //fclose(stdin);
    //fclose(stdout);
    return 0;
}
