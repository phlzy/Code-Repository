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
const int maxn = 1e6 + 9;

string s;
int len;
int n[maxn];
void getnext()
{
    int i = 0, j = -1;
    n[0] = -1;
    while (i < len)
    {
        if (j == -1 || s[i] == s[j])
            n[++i] = ++j;
        else
            j = n[j];
    }
}

signed main()
{
    //freopen("in.txt","r",stdin);
    //freopen("data.txt","w",stdout);
    std::ios::sync_with_stdio(false);
    std::cin.tie(0);
    std::cout.tie(0);
    cin >> len >> s;
    getnext();
    cout << len - n[len] << endl;
    //fclose(stdin);
    //fclose(stdout);
    return 0;
}