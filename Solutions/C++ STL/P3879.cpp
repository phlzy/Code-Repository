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
bool vis[maxn];
string s;
map<string, vector<int>> a;
int main()
{
    //freopen("in.txt","r",stdin);
    //freopen("data.txt","w",stdout);
    std::ios::sync_with_stdio(false);
    //std::cin.tie(0);
    //std::cout.tie(0);
    int n, m, num;
    cin >> n;
    for (int i = 1; i <= n; i++)
    {
        cin >> num;
        for (int j = 1; j <= num; j++)
        {
            cin >> s;
            a[s].emplace_back(i);
        }
    }
    cin >> m;
    for (int i = 1; i <= m; i++)
    {
        cin >> s;
        memset(vis, 0, sizeof(vis));
        for (auto j : a[s])
        {
            if (!vis[j])
            {
                cout << j << ' ';
                vis[j] = true;
            }
        }
        cout << '\n';
    }
    //fclose(stdin);
    //fclose(stdout);
    return 0;
}