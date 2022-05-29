import java.util.Scanner;

public class Anti11 {
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

    public static ModInt calc(int n, int y, ModInt[][] table) {
        if (y >= 2) return new ModInt(0);
        if (table[n][y] == null) {
            if (n + y < 2) table[n][y] = ModInt.pow2(n);
            else table[n][y] = calc(n-1, 0, table).add(calc(n-1, y+1, table));
        }
        return table[n][y];
    }

    public static void main(String[] args) {
        int T = scanner.nextInt();
        for (int i = 0; i < T; i++) {
            int n = scanner.nextInt();
            ModInt[][] table = new ModInt[n+1][2];
            System.out.println(calc(n,0, table));
        }
    }
}
