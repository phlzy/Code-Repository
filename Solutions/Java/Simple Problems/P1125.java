import java.util.*;
import java.math.*;
import java.io.*;

public class P1125 {
    private static int[] a = new int[27];

    public static boolean isPrime(int num) {
        if (num < 2)
            return false;
        boolean isPrime = true;
        int k = (int) Math.sqrt(num);
        for (int i = 2; i <= k; ++i) {
            if (num % i == 0) {
                isPrime = false;
                break;
            }
        }
        return isPrime;
    }

    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        String str = cin.nextLine();
        char[] c = str.toCharArray();
        /*
         * for (int i = 0; i <= 26; ++i) { a[i] = 0; }
         */
        for (int i = 0; i < str.length(); ++i) {
            a[c[i] - 'a' + 1]++;
        }
        int minn = 500, maxx = 0;
        for (int i = 0; i <= 26; ++i) {
            if (a[i] > 0)
                minn = Math.min(minn, a[i]);
            maxx = Math.max(maxx, a[i]);
        }
        int ans = maxx - minn;
        // System.out.println(maxx - minn);

        if (isPrime(ans)) {
            System.out.println("Lucky Word");
            System.out.println(maxx - minn);
        } else {
            System.out.println("No Answer");
            System.out.println(0);
        }
        cin.close();
        return;
    }
}
