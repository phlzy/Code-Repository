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
    T data = 0, dp = 1;
    char ch = getchar();
    while (!isdigit(ch))
    {
        if (ch == '-')
            dp = -1;
        ch = getchar();
    }
    while (isdigit(ch))
    {
        data = (data << 3) + (data << 1) + ch - '0';
        ch = getchar();
    }
    x = dp * data;
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
struct node
{
    int x, y, z;
    bool operator<(const node &b) const
    {
        return z > b.z;
    }
};
node f[maxn];
int fa[maxn], b[maxn];
int findfa(int x)
{
    return x == fa[x] ? x : fa[x] = findfa(fa[x]);
}
void join(int x, int y)
{
    int fx = findfa(x), fy = findfa(y);
    fa[fx] = fy;
}
bool check(int x, int y)
{
    int fx = findfa(x), fy = findfa(y);
    return fx == fy;
}
signed main()
{
    //freopen("in.txt","r",stdin);
    //freopen("data.txt","w",stdout);
    //std::ios::sync_with_stdio(false);
    //std::cin.tie(0);
    //std::cout.tie(0);
    int n, m;
    read(n, m);
    for (int i = 1; i <= n; ++i)
        fa[i] = i;
    for (int i = 1; i <= m; ++i)
    {
        read(f[i].x, f[i].y, f[i].z);
    }
    sort(f + 1, f + m + 1);
    for (int i = 1; i <= m; ++i)
    {
        if (check(f[i].x, f[i].y))
        {
            printf("%d\n", f[i].z);
            return 0;
        }
        else
        {
            if (!b[f[i].x])
                b[f[i].x] = f[i].y;
            else
            {
                join(b[f[i].x], f[i].y);
            }
            if (!b[f[i].y])
                b[f[i].y] = f[i].x;
            else
            {
                join(b[f[i].y], f[i].x);
            }
        }
    }
    printf("0\n");

    //fclose(stdin);
    //fclose(stdout);
    return 0;
}