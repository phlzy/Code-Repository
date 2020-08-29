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

signed main()
{
    //freopen("in.txt","r",stdin);
    //freopen("data.txt","w",stdout);
    //std::ios::sync_with_stdio(false);
    //std::cin.tie(0);
    //std::cout.tie(0);
    ll n;
    cin >> n;
    if (n < 3)
        cout << n << endl;
    else if (n & 1)
        cout << n * (n - 1) * (n - 2) << endl;
    else if (n % 3)
        cout << n * (n - 1) * (n - 3) << endl;
    else
        cout << (n - 1) * (n - 2) * (n - 3) << endl;
    //fclose(stdin);
    //fclose(stdout);
    return 0;
}