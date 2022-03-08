import java.util.Scanner;
import java.util.TreeMap;

class TreeList<T> extends TreeMap<T, Integer> {

    public Integer remove(Object o) {
        T el = (T) o;
        Integer amount = get(el);
        if (amount == null) return amount;
        if (amount == 1) super.remove(el);
        else {
            super.put(el, amount - 1);
        }
        return amount;
    }

    public void add(T el) {
        Integer amount = get(el);
        if (amount == null) super.put(el, 1);
        else super.put(el, amount + 1);
    }

    public T last() {
        return super.lastKey();
    }
}

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int d = in.nextInt();
        int n = in.nextInt();
        if (d > n) {
            System.out.println("impossible");
            return;
        }
        int[] tasks = new int[n];
        TreeList<Integer> current = new TreeList<>();
        for (int i = 0; i < n; i++) {
            tasks[i] = in.nextInt();
        }
        for (int i = 0; i < d; i++) {
            current.add(tasks[i]);
        }
        int best_idx = 0;
        int max = current.last();
        int best = max;
        for (int i = 0; i < n - d; i++) {
            current.remove(tasks[i]);
            current.add(tasks[i + d]);
            if (tasks[i + d] > max || tasks[i] == max) {
                max = current.last();
                if (max < best) {
                    best = max;
                    best_idx = i + 1;
                }
            }
        }
        System.out.println(best_idx + 1);
    }
}
