#include <bits/stdc++.h>
using namespace std;

typedef long long ll;
typedef unsigned long long ull;
typedef pair<int, int> pii;
typedef vector<int> vi;
const int inf = 0x3f3f3f3f;
const double eps = 1e-8;
const int maxn = 1e5 + 9;
const ll mod = 998244353;

int n, m, ans;
bool vis[maxn], num[maxn];
int a[maxn];

signed main()
{
    //freopen("in.txt","r",stdin);
    //freopen("data.txt","w",stdout);
    //std::ios::sync_with_stdio(false);
    //std::cin.tie(0);
    //std::cout.tie(0);
    cin >> n >> m;
    for (int i = 0; i < n; ++i)
        cin >> a[i];
    for (int i = 0; i < (1 << n); ++i)
    {
        if (__builtin_popcount(i) != n - m)
            continue;
        bitset<2005> b;
        b[0] = 1;
        for (int j = 0; j < n; ++j)
            if (i & (1 << j))
                b |= b << a[j];
        ans = max(ans, (int)b.count());
    }
    cout << ans - 1 << endl;
    //fclose(stdin);
    //fclose(stdout);
    return 0;
}