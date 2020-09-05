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

int n, minans = inf, maxans, f1[309][309], f2[309][309], num[309];
int s[309];

inline int sum(int i, int j)
{
    return s[j] - s[i - 1];
}

signed main()
{
    //freopen("in.txt","r",stdin);
    //freopen("data.txt","w",stdout);
    //std::ios::sync_with_stdio(false);
    //std::cin.tie(0);
    //std::cout.tie(0);
    cin >> n;
    for (int i = 1; i <= n; ++i)
    {
        cin >> num[i];
        num[i + n] = num[i];
    }
    for (int i = 1; i <= n + n; ++i)
        s[i] = s[i - 1] + num[i];
    for (int p = 1; p < n; ++p)
    {
        for (int i = 1, j = i + p; (j < n + n) && (i < n + n); ++i, j = i + p)
        {
            f2[i][j] = inf;
            for (int k = i; k < j; ++k)
            {
                f1[i][j] = max(f1[i][j], f1[i][k] + f1[k + 1][j] + sum(i, j));
                f2[i][j] = min(f2[i][j], f2[i][k] + f2[k + 1][j] + sum(i, j));
            }
        }
    }
    for (int i = 1; i <= n; ++i)
    {
        maxans = max(maxans, f1[i][i + n - 1]);
        minans = min(minans, f2[i][i + n - 1]);
    }
    cout << minans << '\n'
         << maxans << '\n';
    //fclose(stdin);
    //fclose(stdout);
    return 0;
}