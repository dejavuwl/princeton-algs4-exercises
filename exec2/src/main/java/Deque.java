import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node<Item> first;

    private Node<Item> last;

    private int n;

    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
        n = 0;
        assert check();
    }

    private boolean check() {

        // check a few properties of instance variable 'first'
        /*if (n < 0) {
            return false;
        }
        if (n == 0) {
            if (first != null) return false;
        }
        else if (n == 1) {
            if (first == null)      return false;
            if (first.next != null) return false;
        }
        else {
            if (first == null)      return false;
            if (first.next == null) return false;
        }

        // check internal consistency of instance variable n
        int numberOfNodes = 0;
        for (Node x = first; x != null && numberOfNodes <= n; x = x.next) {
            numberOfNodes++;
        }
        if (numberOfNodes != n) return false;*/

        return true;
    }

    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
        private Node<Item> latter;
    }

    // is the deque empty?
    public boolean isEmpty() {
        assert check();
        return n == 0;
    }

    // return the number of items on the deque
    public int size() {
        assert check();
        return n;
    }

    // add the item to the front
    public void addFirst(Item item) {
        Node<Item> oldFirst = first;
        Node<Item> node = new Node<>();
        node.item = item;
        node.latter = null;
        if (n == 0) {
            node.next = null;
            last = node;
        } else {
            node.next = oldFirst;
            oldFirst.latter = node;
        }
        first = node;
        n++;
    }

    // add the item to the back
    public void addLast(Item item) {
        Node<Item> oldLast = last;
        Node<Item> node = new Node<>();
        node.item = item;
        node.next = null;
        if (n == 0) {
            node.latter = null;
            first = node;
        } else {
            node.latter = oldLast;
            oldLast.next = node;
        }
        last = node;
        n++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (n > 1) {
            Node<Item> oldFirst = first;
            Node<Item> second = first.next;
            second.latter = null;
            first = second;
            n--;
            return oldFirst.item;
        } else if (n > 0) {
            Node<Item> oldFirst = last;
            last = null;
            first = null;
            n--;
            return oldFirst.item;
        } else {
            throw new NoSuchElementException();
        }

    }

    // remove and return the item from the back
    public Item removeLast() {
        if (n > 1) {
            Node<Item> oldLast = last;
            Node<Item> second = last.latter;
            second.next = null;
            last = second;
            n--;
            return oldLast.item;
        } else if (n > 0) {
            Node<Item> oldLast = last;
            last = null;
            first = null;
            n--;
            return oldLast.item;
        } else {
            throw new NoSuchElementException();
        }


    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this) {
            s.append(item);
            s.append(' ');
        }
        return s.toString();
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new LinkedIterator(first);
    }

    private class LinkedIterator implements Iterator<Item> {
        private Node<Item> current;

        public LinkedIterator(Node<Item> first) {
            current = first;
        }

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<String> deque = new Deque<>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) {
                deque.addFirst(item);
                deque.addLast(item);
            } else if (!deque.isEmpty()) {
                StdOut.print(deque.removeFirst());
                StdOut.print(deque.removeLast());
                StdOut.print(deque + " ");
                Iterator<String> iterator = deque.iterator();
                while (iterator.hasNext()) {
                    StdOut.print(iterator.next());
                }
            }
        }
        StdOut.println("(" + deque.size() + " left on queue)");
    }

}
