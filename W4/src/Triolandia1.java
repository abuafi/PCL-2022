import java.util.*;

public class Triolandia1 {

    static class City{
        int x, y;
        String name;
        long area;

        public City(String name, int x, int y) {
            this.name = name;
            this.area = 0;
            this.x = x;
            this.y = y;
        }
//        @Override
//        public String toString() {
//            return name;
//        }
    }

    public static long area(City a, City b, City c) {
        return Math.abs((a.x *(b.y - c.y)) + (b.x *(c.y - a.y)) + (c.x *(a.y - b.y)));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int n = scanner.nextInt();
//        if (n == 0) return;
        City[] cities = new City[m];
        for (int i = 0; i < m; i++) {
            cities[i] = new City(scanner.next(), scanner.nextInt(), scanner.nextInt());
        }
        for (int i = 0; i < n; i++) {
            int a = scanner.nextInt()-1;
            int b = scanner.nextInt()-1;
            int c = scanner.nextInt()-1;
            long area = area(cities[a], cities[b], cities[c]);
            cities[a].area += area;
            cities[b].area += area;
            cities[c].area += area;
        }

        Arrays.stream(cities).mapToLong(i -> i.area).forEach(System.out::println);
        long max = Arrays.stream(cities).mapToLong(i -> i.area).max().orElse(-1);
        Arrays.stream(cities).filter(c -> c.area == max).map(i -> i.name).sorted().forEach(System.out::println);
//        max.stream().sorted(Comparator.comparing((City a) -> a.name)).forEach(System.out::println);
    }
}
