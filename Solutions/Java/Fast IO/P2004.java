import java.io.*;
import java.util.*;

public class P2004 {
    public static int[][] arr = new int[1005][1005];

    public static void main(String[] args) {
        MyScanner sc = new MyScanner();
        int n = sc.nextInt();
        int m = sc.nextInt();
        int c = sc.nextInt();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                arr[i][j] = sc.nextInt();
                arr[i][j] = arr[i][j] - arr[i - 1][j - 1] + arr[i - 1][j] + arr[i][j - 1];
            }
        }
        int max = Integer.MIN_VALUE;
        int a = 0, b = 0;
        for (int j = c; j <= n; j++) {
            for (int j2 = c; j2 <= m; j2++) {
                int ans = arr[j][j2] - arr[j - c][j2] - arr[j][j2 - c] + arr[j - c][j2 - c];
                if (ans > max) {
                    max = ans;
                    a = j - c + 1;
                    b = j2 - c + 1;
                }
            }
        }
        System.out.println(a + " " + b);
    }

    public static class MyScanner {
        BufferedReader br;
        StringTokenizer st;

        public MyScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }

    }
}