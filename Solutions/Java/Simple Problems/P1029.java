
// import java.io.*;
import java.util.*;
// import java.math.*;

public class P1029 {
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        int x = cin.nextInt();
        int y = cin.nextInt();
        if (y % x != 0)
            System.out.println(0);
        else {
            int q = y / x;
            int cnt = 0, cur = 2;
            while (q > 1) {
                if (q % cur == 0) {
                    cnt++;
                    while (q % cur == 0) {
                        q /= cur;
                    }
                }
                cur++;
            }
            int ans = 1;
            /*
             * for (int i = 1; i <= cnt; ++i) ans *= 2;
             */
            ans = (1 << cnt);
            System.out.println(ans);
            cin.close();

        }
    }

}