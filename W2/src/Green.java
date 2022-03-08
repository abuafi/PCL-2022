import java.util.*;

class Car implements Comparable<Car> {
    private final String name;
    private final float fuel;

    private static final float CONVERSION = (float) (1.609 / 378.5);

    public Car(String user, String name, float fuel) {
        this.name = name;
        if (user.equals("Alice")) this.fuel = fuel;
        else this.fuel = 1/(fuel * CONVERSION);
    }

    @Override
    public int compareTo(Car o) {
        return fuel - o.fuel > 0 ? 1 : fuel - o.fuel < 0 ? -1 : name.compareTo(o.name);
    }

    @Override
    public String toString() {
        return name;
    }
}

public class Green {
    static Scanner s = new Scanner(System.in);

    public static void main(String[] args) {
        List<Car> list = new ArrayList<>();
        int n = s.nextInt();
        for (int i = 0; i < n; i++) {
            list.add(new Car(s.next(), s.next(), s.nextFloat()));
        }
        list.stream().sorted().forEach(System.out::println);
    }
}
