import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class P1932 {
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        BigInteger a = cin.nextBigInteger();
        BigInteger b = cin.nextBigInteger();
        System.out.println(a.add(b));
        System.out.println(a.subtract(b));
        System.out.println(a.multiply(b));
        System.out.println(a.divide(b));
        System.out.println(a.remainder(b));
        return;
    }
}
