import java.util.*;

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

    public Set<Map.Entry<T, Integer>> entrySet() {
        return super.entrySet();
    }
}

class Event implements Comparable<Event> {
    public boolean isStart;
    public int pos;

    public Event(boolean isStart, int pos) {
        this.isStart = isStart;
        this.pos = pos;
    }

    @Override
    public int compareTo(Event o) {
        int out = pos - o.pos;
        return out != 0 ? 2*out : isStart == o.isStart ? 0 : isStart ? -1 : 1;
    }

    @Override
    public String toString() {
        return (isStart ? "S" : "E") + " " + pos;
    }

}

public class Highway {

    private static TreeList<Event> vehicles = new TreeList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            String type = scanner.next();
            int pos = scanner.nextInt();
            int distance = type.equals("T") ? 100 : 50;
            vehicles.add(new Event(true, pos - distance));
            vehicles.add(new Event(false, pos + distance));
        }

        int congStart = 0;
        boolean isCongested = false;
        int counter = 0;
        for (Map.Entry<Event, Integer> entry : vehicles.entrySet()) {
            Event event = entry.getKey();
            if (event.isStart) {
                counter += entry.getValue();
            } else {
                counter -= entry.getValue();
            }
            if (counter >= 3 && !isCongested) {
                isCongested = true;
                congStart = event.pos;
            }
            if (counter < 3 && isCongested) {
                if (congStart != event.pos) System.out.println(congStart + " " + event.pos);
                isCongested = false;
            }
        }
    }
}
