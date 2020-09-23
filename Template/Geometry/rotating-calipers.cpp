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
const int maxn = 1e5 + 9;

int sgn(double x)
{
    if (fabs(x) < eps)
        return 0;
    if (x < 0)
        return -1;
    return 1;
}

struct point
{
    double x, y;
    point() {}
    point(double _x, double _y) : x(_x), y(_y) {}
    point operator+(const point &b) const
    {
        point ret(x + b.x, y + b.y);
        return ret;
    }
    point operator-(const point &b) const
    {
        point ret(x - b.x, y - b.y);
        return ret;
    }
    point operator|(const double &b) const
    {
        point ret(x * b, y * b);
        return ret;
    }
    point operator/(const double &b) const
    {
        if (!sgn(b))
            return point(0, 0);
        point ret(x / b, y / b);
        return ret;
    }
    bool operator==(const point &b) const
    {
        return sgn(x - b.x) == 0 && sgn(y - b.y) == 0;
    }
    double operator*(const point &b) const
    {
        return x * b.x + y * b.y;
    }
    double operator^(const point &b) const
    {
        return x * b.y - y * b.x;
    }
    double len()
    {
        return sqrt(x * x + y * y);
    }
};

inline double dist(const point &a, const point &b)
{
    return sqrt((a - b) * (a - b));
}

typedef point vec;

point pt[maxn];
int st[maxn], top;
bool cmp(point p1, point p2)
{
    double tmp = (p1 - pt[0]) ^ (p2 - pt[0]);
    if (sgn(tmp) > 0)
        return true;
    else if (sgn(tmp) == 0 && sgn(dist(p1, pt[0]) - dist(p2, pt[0])) <= 0)
        return true;
    else
        return false;
}
void graham(int n)
{
    point p0;
    int k = 0;
    p0 = pt[0];
    //找最下边的一个点
    for (int i = 1; i < n; i++)
    {
        if ((p0.y > pt[i].y) || (p0.y == pt[i].y && p0.x > pt[i].x))
        {
            p0 = pt[i];
            k = i;
        }
    }
    swap(pt[k], pt[0]);
    sort(pt + 1, pt + n, cmp);
    if (n == 1)
    {
        top = 1;
        st[0] = 0;
        return;
    }
    if (n == 2)
    {
        top = 2;
        st[0] = 0;
        st[1] = 1;
        return;
    }
    st[0] = 0;
    st[1] = 1;
    top = 2;
    for (int i = 2; i < n; i++)
    {
        while (top > 1 && sgn((pt[st[top - 1]] - pt[st[top - 2]]) ^ (pt[i] - pt[st[top - 2]])) <= 0)
            top--;
        st[top++] = i;
    }
    st[top] = st[0];
}
inline int dist2(const point &a, const point &b)
{
    return (int)((a - b) * (a - b));
}
inline int comp(point a, point b, point c)
{
    return (int)((b - a) ^ (c - a));
}
int getd()
{
    int ret = 0;
    if (top == 1)
        return dist2(pt[st[0]], pt[st[1]]);
    int j = 2;
    for (int i = 0; i < top; ++i)
    {
        while (comp(pt[st[i]], pt[st[i + 1]], pt[st[j]]) < comp(pt[st[i]], pt[st[i + 1]], pt[st[j + 1]]))
        {
            j = (j + 1) % top;
        }
        ret = max(ret, max(dist2(pt[st[i]], pt[st[j]]), dist2(pt[st[i + 1]], pt[st[j]])));
    }
    return ret;
}
signed main()
{
    //freopen("in.txt","r",stdin);
    //freopen("data.txt","w",stdout);
    //std::ios::sync_with_stdio(false);
    //std::cin.tie(0);
    //std::cout.tie(0);
    int n;
    cin >> n;
    for (int i = 0; i < n; ++i)
        cin >> pt[i].x >> pt[i].y;
    int ans = inf;
    graham(n);
    ans = getd();
    cout << ans << endl;
    //fclose(stdin);
    //fclose(stdout);
    return 0;
}