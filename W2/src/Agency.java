import java.util.Scanner;

public class Agency {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int n = scanner.nextInt();
        scanner.nextLine();
        for (int i = 1; i <= n; i++) {
            String agency = scanner.nextLine();
            String msg = scanner.nextLine();
            assert agency.length() == 3;
            char[] a = agency.toCharArray();
            int ia = 0;
            for (char c : msg.toCharArray()) {
                if (c == a[ia]) {
                    ia++;
                    if (ia == 3) {
                        System.out.println(i);
                        break;
                    }
                }
            }
        }
    }
}
