import java.util.*;

public class Stack<T> {
    private final List<T> array;

    public Stack() {
        array = new ArrayList<>();
    }

    public int size() {
        return array.size();
    }

    public T pop() {
        if (array.isEmpty())
            return null;

        final int lastIndex = array.size() - 1;
        return array.remove(lastIndex);
    }

    public void push(T val) {
        if (val == null)
            return;

        array.add(val);
    }

    public T peek() {
        if (array.isEmpty())
            return null;

        final int lastIndex = array.size() - 1;
        return array.get(lastIndex);
    }
}