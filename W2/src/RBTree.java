import java.util.Comparator;
import java.util.TreeMap;

public class RBTree<T extends Comparable<T>> {

    private static final boolean RED   = false;
    private static final boolean BLACK = true;

    static final class Entry<T> {
        T elem;
        Entry<T> left;
        Entry<T> right;
        Entry<T> parent;
        boolean color = BLACK;

        public Entry(T elem, Entry<T> parent) {
            this.elem = elem;
            this.parent = parent;
        }

        public T get() {
            return elem;
        }
    }

    private transient Entry<T> root;
    private transient int size = 0;
    public int size() {
        return size;
    }

    public void put(T elem) {
        Entry<T> t = root;
        if (t == null) {
            root = new Entry<>(elem, null);
            size = 1;
            return;
        }
        int cmp;
        Entry<T> parent;
            if (elem == null) throw new NullPointerException();
            do {
                parent = t;
                cmp = elem.compareTo(t.get());
                if (cmp < 0)
                    t = t.left;
                else
                    t = t.right;
            } while (t != null);
        Entry<T> e = new Entry<>(elem, parent);
        if (cmp < 0)
            parent.left = e;
        else
            parent.right = e;
        fixAfterInsertion(e);
        size++;
    }

    private static <T> boolean colorOf(Entry<T> p) {
        return (p == null ? BLACK : p.color);
    }

    private static <T> Entry<T> parentOf(Entry<T> p) {
        return (p == null ? null: p.parent);
    }

    private static <T> void setColor(Entry<T> p, boolean c) {
        if (p != null)
            p.color = c;
    }

    private static <T> Entry<T> leftOf(Entry<T> p) {
        return (p == null) ? null: p.left;
    }

    private static <T> Entry<T> rightOf(Entry<T> p) {
        return (p == null) ? null: p.right;
    }

    private void rotateLeft(Entry<T> p) {
        if (p != null) {
            Entry<T> r = p.right;
            p.right = r.left;
            if (r.left != null)
                r.left.parent = p;
            r.parent = p.parent;
            if (p.parent == null)
                root = r;
            else if (p.parent.left == p)
                p.parent.left = r;
            else
                p.parent.right = r;
            r.left = p;
            p.parent = r;
        }
    }

    private void rotateRight(Entry<T> p) {
        if (p != null) {
            Entry<T> l = p.left;
            p.left = l.right;
            if (l.right != null) l.right.parent = p;
            l.parent = p.parent;
            if (p.parent == null)
                root = l;
            else if (p.parent.right == p)
                p.parent.right = l;
            else p.parent.left = l;
            l.right = p;
            p.parent = l;
        }
    }


    private void fixAfterInsertion(Entry<T> x) {
        x.color = RED;

        while (x != null && x != root && x.parent.color == RED) {
            if (parentOf(x) == leftOf(parentOf(parentOf(x)))) {
                Entry<T> y = rightOf(parentOf(parentOf(x)));
                if (colorOf(y) == RED) {
                    setColor(parentOf(x), BLACK);
                    setColor(y, BLACK);
                    setColor(parentOf(parentOf(x)), RED);
                    x = parentOf(parentOf(x));
                } else {
                    if (x == rightOf(parentOf(x))) {
                        x = parentOf(x);
                        rotateLeft(x);
                    }
                    setColor(parentOf(x), BLACK);
                    setColor(parentOf(parentOf(x)), RED);
                    rotateRight(parentOf(parentOf(x)));
                }
            } else {
                Entry<T> y = leftOf(parentOf(parentOf(x)));
                if (colorOf(y) == RED) {
                    setColor(parentOf(x), BLACK);
                    setColor(y, BLACK);
                    setColor(parentOf(parentOf(x)), RED);
                    x = parentOf(parentOf(x));
                } else {
                    if (x == leftOf(parentOf(x))) {
                        x = parentOf(x);
                        rotateRight(x);
                    }
                    setColor(parentOf(x), BLACK);
                    setColor(parentOf(parentOf(x)), RED);
                    rotateLeft(parentOf(parentOf(x)));
                }
            }
        }
        root.color = BLACK;
    }
}
