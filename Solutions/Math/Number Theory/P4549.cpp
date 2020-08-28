#include <bits/stdc++.h>
using namespace std;
signed main(){
    int n, tmp, ans = 0;
    cin >> n;
    while(n--){
        cin >> tmp;
        tmp = (tmp < 0 ? -tmp : tmp);
        ans = __gcd(ans, tmp);
    }
    cout << ans << endl;
    return 0;
}