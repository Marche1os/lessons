import java.util.LinkedList;

public class StackBackwards<T> {
    private final LinkedList<T> array;


    public StackBackwards() {
        this.array = new LinkedList<>();
    }

    public void push(T e) {
        array.addFirst(e);
    }

    public T pop() {
        if (array.isEmpty())
            return null;

        return array.removeFirst();
    }

    public T peek() {
        if (array.isEmpty())
            return null;

        return array.getFirst();
    }

    public int size() {
        return array.size();
    }
}
