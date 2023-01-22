import java.util.Stack;

public class QueueStack<T> {
    private final Stack<T> left; //stack for enqueue operations
    private final Stack<T> right; //stack for dequeue operations
    private int size;

    public QueueStack() {
        left = new Stack<>();
        right = new Stack<>();
        size = 0;
    }

    public void enqueue(T t) {
        left.push(t);
        size++;
    }

    public T dequeue() {
        if (size == 0)
            return null;

        if (right.isEmpty()) {
            while (left.size() > 0) {
                right.push(left.pop());
            }
        }

        final T output = right.pop();
        size--;

        return output;
    }

    public int size() {
        return size;
    }

}
