#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
const int maxn = 1000 + 50;

struct point
{
    double x, y;
    point() {}
    point(double _x, double _y) { x = _x, y = _y; }
    point operator-(const point &b)
    {
        return point(x - b.x, y - b.y);
    }
    double operator^(const point &b)
    {
        return x * b.y - y * b.x;
    }
};
double polys(point p[], int n)
{
    double ans = 0.0;
    for (int i = 1; i < n - 1; ++i)
        ans += (p[i] - p[0]) ^ (p[i + 1] - p[0]);
    return fabs(ans / 2);
}
point a[maxn];
int main()
{
    int n, p;
    cin >> n;
    double ans = 0;
    while (n--)
    {
        cin >> p;
        for (int i = 0; i < p; i++)
            cin >> a[i].x >> a[i].y;
        ans += polys(a, p);
    }
    ll tot = ans;
    cout << tot << endl;
    return 0;
}