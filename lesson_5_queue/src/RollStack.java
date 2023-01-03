import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public abstract class RollStack {

    public static void rotate(final Queue queue, final int depth) {
        if (queue.size() == 0 || depth > queue.size())
            throw new IllegalArgumentException("Queue is empty or depth > queue.size");

        if (depth == queue.size() || depth == 0)
            return;

        List temp = new ArrayList(queue);

        Object obj = temp.get(0);

        for (int i = depth;;i+= depth) {
            if (i >= queue.size())
                i -= queue.size();
            obj = temp.set(i, obj);
            if (i == 0)
                break;
        }

        queue.clear();
        queue.addAll(temp);
    }
}
