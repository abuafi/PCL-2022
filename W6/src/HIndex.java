import java.util.Arrays;
import java.util.Scanner;

public class HIndex {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int n = scanner.nextInt();
        int[] papers = new int[n];
        for (int i = 0; i < n; i++) {
            papers[i] = scanner.nextInt();
        }
        papers = Arrays.stream(papers).sorted().toArray();
        int x = 0;
        for (int i = n-1; i >= 0; i-- ) {
            if (papers[i] > x) x++;
            else break;
        }
        System.out.println(x);
    }
}
