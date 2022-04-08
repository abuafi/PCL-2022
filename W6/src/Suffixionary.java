import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Suffixionary {
    static class Node {
        private final Node[] nodes = new Node[26];
        private final List<String> strings = new ArrayList<>();

        public void add(String s) {
            add(new StringBuffer(s).reverse().toString(), s, 0);
        }
        private void add(String s, String og, int idx) {
            strings.add(og);
            if (idx < s.length()) {
                Node n = nodes[s.charAt(idx) - 97];
                if (n == null) n = new Node();
                n.add(s, og, idx + 1);
                nodes[s.charAt(idx) - 97] = n;
            }
        }
        public List<String> get(String q) {
            return get(new StringBuffer(q).reverse().toString(), 0);
        }
        private List<String> get(String q, int idx) {
            if (idx < q.length()) {
                Node n = nodes[q.charAt(idx) - 97];
                if (n == null) return List.of();
                return n.get(q, idx + 1);
            } else {
                return strings;
            }
        }
    }

    static Scanner scanner = new Scanner(System.in);
    static Node dict = new Node();

    public static void main(String[] args) {
        int n = scanner.nextInt();
        int q = scanner.nextInt();
//        long time = System.nanoTime();
        for (int i = 0; i < n; i++) {
            dict.add(scanner.next());
        }
        for (int i = 0; i < q; i++) {
            String query = scanner.next();
            dict.get(query).stream().sorted().forEach(System.out::println);
        }
//        System.out.println(System.nanoTime() - time);
    }



}
