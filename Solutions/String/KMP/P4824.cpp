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
template <typename T, typename... Args>
inline void read(T &t, Args &... args)
{
    read(t);
    read(args...);
}
const int inf = 0x3f3f3f3f;
const double eps = 1e-8;
const int maxn = 1e6 + 9;
char s[maxn], t[maxn];
int nxt[maxn], f[maxn];
int len1, len2;
void get_next()
{
    for (int i = 2, j = 0; i <= len2; ++i)
    {
        while (j && t[i] != t[j + 1])
            j = nxt[j];
        j += t[i] == t[j + 1];
        nxt[i] = j;
    }
}
deque<int> dq;
signed main()
{
    //freopen("in.txt","r",stdin);
    //freopen("data.txt","w",stdout);
    //std::ios::sync_with_stdio(false);
    //std::cin.tie(0);
    //std::cout.tie(0);
    scanf("%s", s + 1);
    scanf("%s", t + 1);
    len1 = strlen(s + 1);
    len2 = strlen(t + 1);
    get_next();
    /*
    for (int i = 0; i <= len2; ++i)
        cout << nxt[i] << ' ';
    cout << endl;
    */
    for (int i = 1, j = 0; i <= len1; ++i)
    {
        while (j && s[i] != t[j + 1])
            j = nxt[j];
        if (s[i] == t[j + 1])
            j++;
        f[i] = j;
        dq.push_back(i);
        if (j == len2)
        {
            for (int k = 1; k <= len2; ++k)
                if (!dq.empty())
                    dq.pop_back();
            j = dq.empty() ? 0 : f[dq.back()];
        }
    }
    for (auto i : dq)
        printf("%c", s[i]);
    //fclose(stdin);
    //fclose(stdout);
    return 0;
}