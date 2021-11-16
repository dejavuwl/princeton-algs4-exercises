import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;
public class RandomizedQueue<Item> implements Iterable<Item> {
    private int n;
    private Item[] a;
    private static final int INIT_CAPACITY = 8;
    // construct an empty randomized queue
    public RandomizedQueue() {
        n = 0;
        a = (Item[]) new Object[INIT_CAPACITY];
    }
    // is the randomized queue empty?
    public boolean isEmpty() {
        return n == 0;
    }
    // return the number of items on the randomized queue
    public int size() {
        return n;
    }
    private void resize(int capacity) {
        assert capacity >= n;
        // textbook implementation
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            copy[i] = a[i];
        }
        a = copy;
    }
    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (n == a.length) resize(2 * a.length);    // double size of array if necessary
        a[n++] = item;
    }
    // remove and return a random item
    public Item dequeue() {
        if (n == 0) {
            throw new NoSuchElementException();
        }
        int x = (int) (StdRandom.uniform() * n);
        Item t = a[x];
        if (n - x > 0) {
            System.arraycopy(a, x + 1, a, x, n - x);
        }
        n--;
        return t;
    }
    // return a random item (but do not remove it)
    public Item sample() {
        if (n == 0) {
            throw new NoSuchElementException();
        }
        int x = (int) (StdRandom.uniform() * n);
        Item t = a[x];
        return t;
    }
    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new LinkedIterator();
    }
    private class LinkedIterator implements Iterator<Item> {
        private int current;
        private int count;
        int[] indexes;
        public LinkedIterator() {
            count = n;
            indexes = new int[n];
            current = (int) (StdRandom.uniform() * n);
        }
        public boolean hasNext() {
            return count > 0;
        }
        public void remove() {
            throw new UnsupportedOperationException();
        }
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = null;
            int i = 0;
            for (int j = 0; j < n; j++) {
                if (indexes[j] != 1) {
                    if (i == current) {
                        item = a[j];
                        indexes[j] = 1;
                        break;
                    } else {
                        i++;
                    }
                }
            }
            count--;
            current = (int) (StdRandom.uniform() * count);
            return item;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<String> strings = new RandomizedQueue<>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) {
                strings.enqueue(item);
                Iterator<String> iterator = strings.iterator();
                while (iterator.hasNext()) {
                    StdOut.print(iterator.next());
                }
                StdOut.println("(" + strings.size() + " left on queue)");
                StdOut.println("sample---" + strings.sample());
            } else if (!strings.isEmpty()) {
                StdOut.println(strings.dequeue());
                Iterator<String> iterator = strings.iterator();
                while (iterator.hasNext()) {
                    StdOut.print(iterator.next());
                }
                StdOut.println("(" + strings.size() + " left on queue)");
                StdOut.println("sample---" + strings.sample());
            }
        }
        StdOut.println("(" + strings.size() + " left on queue)");
    }

}
