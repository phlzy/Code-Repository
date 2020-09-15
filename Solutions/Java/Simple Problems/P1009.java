import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class P1009 {
    private static BigInteger[] Frac = new BigInteger[51];
    private static BigInteger res = new BigInteger("1");

    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        int n = cin.nextInt();
        Frac[1] = new BigInteger("1");
        for (int i = 2; i <= n; ++i) {
            Frac[i] = Frac[i - 1].multiply(new BigInteger(String.valueOf(i)));
            res = res.add(Frac[i]);
        }
        System.out.println(res);
    }
}
