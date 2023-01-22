import java.util.*;

public class Deque<T> {
    private ArrayList<T> arrayList;

    public Deque() {
        arrayList = new ArrayList<>();
    }

    public void addFront(T item) {
        arrayList.add(0, item);
    }

    public void addTail(T item) {
        arrayList.add(item);
    }

    public T removeFront() {
        if (arrayList.isEmpty())
            return null;
        return arrayList.remove(0);
    }

    public T removeTail() {
        if (arrayList.isEmpty())
            return null;
        return arrayList.remove(arrayList.size() - 1);
    }

    public int size() {
        return arrayList.size();
    }
}