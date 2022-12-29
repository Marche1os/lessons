import java.util.*;

public class Queue<T> {
    private LinkedList<T> array;

    public Queue() {
        array = new LinkedList<>();
    }

    public void enqueue(T item) {
        if (item == null) return;
        array.addLast(item);
    }

    public T dequeue() {
        if (array.isEmpty()) return null;
        return array.removeFirst();
    }

    public int size() {
        return array.size();
    }

}