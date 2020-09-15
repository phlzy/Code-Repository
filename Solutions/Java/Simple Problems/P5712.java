import java.util.Scanner;

public class P5712 {
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        int a = cin.nextInt();
        if (a < 2)
            System.out.println("Today, I ate " + a + " apple.");
        else
            System.out.println("Today, I ate " + a + " apples.");
        return;
    }
}
