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

int a[110][110], b[110][110];
bool flag = true;
signed main()
{
    //freopen("in.txt","r",stdin);
    //freopen("data.txt","w",stdout);
    //std::ios::sync_with_stdio(false);
    //std::cin.tie(0);
    //std::cout.tie(0);
    int n, m;
    scanf("%d%d", &n, &m);
    for (int i = 1; i <= n; ++i)
        for (int j = 1; j <= m; ++j)
            scanf("%d", &a[i][j]);
    for (int i = 1; i <= n; ++i)
        for (int j = 1; j <= m; ++j)
            b[i][j] = 1;
    for (int i = 1; i <= n; ++i)
        for (int j = 1; j <= m; ++j)
            if (!a[i][j])
            {
                for (int z = 1; z <= m; ++z)
                    b[i][z] = 0;
                for (int z = 1; z <= n; ++z)
                    b[z][j] = 0;
            }
    for (int i = 1; i <= n; ++i)
        for (int j = 1; j <= m; ++j)
            if (a[i][j])
            {
                bool flag = false;
                for (int z = 1; z <= m; ++z)
                    if (b[i][z])
                        flag = true;
                if (!flag)
                {
                    for (int z = 1; z <= n; ++z)
                        if (b[z][j])
                            flag = true;
                }
                if (!flag)
                {
                    puts("NO");
                    return 0;
                }
            }
    puts("YES");
    for (int i = 1; i <= n; ++i)
    {
        for (int j = 1; j <= m; ++j)
            printf("%d ", b[i][j]);
        puts("");
    }
    //fclose(stdin);
    //fclose(stdout);
    return 0;
}
