import java.util.Arrays;
import java.util.Scanner;

public class AntiAny {
    static Scanner scanner = new Scanner(System.in);
    static final int MOD = (int) (Math.pow(10, 9) + 7);

    static class ModInt {
        int x;

        public ModInt(int x) {
            this.x = x % MOD;
        }

        public ModInt add(ModInt y) {
            return new ModInt((x + y.x) % MOD);
        }

        @Override
        public String toString() {
            return String.valueOf(x);
        }

        public static ModInt pow2(int e) {
            return new ModInt((int) Math.pow(2, e));
        }
    }

    public static ModInt calc(int n, int y, ModInt[][] table, int size, int[] sv) {
        if (y >= size) return new ModInt(0);
        if (table[n][y] == null) {
            if (n + y < size) table[n][y] = ModInt.pow2(n);
                // todo replace 0 with sv value
            else table[n][y] = calc(n-1, sv[y], table, size, sv).add(calc(n-1, y+1, table, size, sv));
        }
        return table[n][y];
    }

    public static void main(String[] args) {
        int T = scanner.nextInt();
        for (int i = 0; i < T; i++) {
            int n = scanner.nextInt();
            String s = scanner.next();
            int size = s.length();
            int[] sv = new int[size];

            for (int j = 0; j < size; j++) {
                int p = s.length()-1;
                String c = s.substring(0, p) + (s.charAt(p) == '0' ? '1' : '0');

                int counter = 0;
                for (int k = 1; k <= p+1; k++) {
                    String s1 = c.substring(p-k+1);
                    String s2 = s.substring(0,k);
                    if (s1.equals(s2)) {
                        counter = k;
                    }
                }
                sv[size-j-1] = counter;
                s = s.substring(0,p);
            }

            ModInt[][] table = new ModInt[n+1][size];

            System.out.println(calc(n,0, table, size, sv));
        }
    }
}
