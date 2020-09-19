
// import java.io.*;
import java.util.*;

//import java.math.*;
public class P1049 {
    private static int[] dp = new int[20050];
    private static int[] w = new int[50];

    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        int m = cin.nextInt();
        int n = cin.nextInt();
        for (int i = 1; i <= n; ++i) {
            w[i] = cin.nextInt();
        }
        for (int i = 1; i <= n; ++i) {
            for (int j = m; j >= w[i]; --j) {
                dp[j] = Math.max(dp[j], dp[j - w[i]] + w[i]);
            }
        }
        System.out.println(m - dp[m]);
        cin.close();
    }
}
