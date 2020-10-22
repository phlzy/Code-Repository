#include <bits/stdc++.h>
using namespace std;

typedef long long ll;
typedef unsigned long long ull;
typedef pair<int, int> pii;
typedef vector<int> vi;
typedef vector<ll> vll;
const int Max = 1e4 + 9;
int tian[Max], king[Max];

int main()
{
    int n, ans = 0;
    cin >> n;
    for (int i = 1; i <= n; i++)
        cin >> tian[i];
    for (int i = 1; i <= n; i++)
        cin >> king[i];
    sort(tian + 1, tian + 1 + n, greater<int>());
    sort(king + 1, king + 1 + n, greater<int>());
    for (int i = 1, j = 1, ii = n, jj = n; i <= ii;)
    {
        if (tian[i] > king[j])
        {
            ans += 200;
            i++, j++;
        }
        else if (tian[i] < king[j])
        {
            ans -= 200;
            j++, ii--;
        }
        else
        {
            if (tian[ii] > king[jj])
            {
                ans += 200;
                ii--, jj--;
            }
            else
            {
                if (tian[ii] < king[j])
                    ans -= 200;
                ii--, j++;
            }
        }
    }
    cout << ans << endl;
    return 0;
}