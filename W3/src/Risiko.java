import java.util.Scanner;

import static java.lang.Math.pow;

public class Risiko {

    static long choose(int n, int k) {
        if ((n == k) || (k == 0))
            return 1;
        else
            return choose(n - 1, k) + choose(n - 1, k - 1);
    }

    public static double totalPossibilities(int k, int m, int n) {
        return pow(k,m+n);
    }

    // sth highest face in n rolls is h (k-sided)
    public static double SHighestH(int k, int n, int h, int s) {
        if (s <= 0) return 0;
        double tot = 0;
        // j is largest
        int j = h;
        for (int i = s ; i <= n ; i++) {
            // i results of number j
            tot += choose(n, i) * pow(j-1, n-i);
        }
        for (j = h + 1 ; j <= k ; j++) {
            // j is largest
            for (int i = 1 ; i <= s-1 ; i++) {
                // i results of number j
                tot += choose(n, i) * SHighestH(j-1, n-i, h,s-i);
            }
        }
        return tot;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int k = scanner.nextInt();
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        double tot_chance = 0;
        double t = totalPossibilities(k, m, n);
        for (int s = 1 ; s <= Math.min(m, n) ; s++) {
//            System.out.println("soldier #" + s);
            double tot = 0;
            for (int h = 1 ; h <= k ; h++) {
//                System.out.println("    defence = " + h);
                double poss = SHighestH(k, n, h, s);
//                System.out.println("    possibilities: " + poss);
                for (int j = 1 ; j <= h ; j++) {
//                    System.out.println("        attack = " + j);
//                    System.out.println("        possibilities: " + SHighestH(k, m, j, s));
                    tot += SHighestH(k, m, j, s) * poss;
                }
            }
//            System.out.println("        In total: " + tot/t + " chance of win");
            tot_chance += tot/t;
        }
        String out = String.format("%.5f", tot_chance);
        String out2 = String.format("%.5f", Math.min(m, n) - tot_chance);
        System.out.println(out);
        System.out.println(out2);
    }
}
