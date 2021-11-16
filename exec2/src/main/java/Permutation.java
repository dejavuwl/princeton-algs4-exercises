import edu.princeton.cs.algs4.StdIn;

import java.util.Iterator;

public class Permutation {
    public static void main(String[] args) {
        RandomizedQueue<String> strings = new RandomizedQueue<>();
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            strings.enqueue(s);
        }
        String n = args[0];
        int total = Integer.parseInt(n);
        Iterator<String> iterator = strings.iterator();
        int i = 0;
        total = total < strings.size() ? total : strings.size();
        while (iterator.hasNext() && i < total) {
            System.out.println(iterator.next());
            i++;
        }
    }
}
