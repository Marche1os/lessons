import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class QueueTest {

    @Test
    void removeFromEmptyQueue() {
        final Queue<Integer> queue = new Queue<>();
        final Integer value = queue.dequeue();
        assertNull(value);
    }

    @Test
    void addFewElements() {
        final Queue<Integer> queue = new Queue<>();

        queue.enqueue(128);
        queue.enqueue(256);
        queue.enqueue(512);

        assertEquals(3, queue.size());

        final Integer value1 = queue.dequeue();
        final Integer value2 = queue.dequeue();
        final Integer value3 = queue.dequeue();

        assertEquals(value1, 128);
        assertEquals(value2, 256);
        assertEquals(value3, 512);

        assertNull(queue.dequeue());
    }

    @Test
    void remove() {
        final Queue<Integer> queue = new Queue<>();
        queue.enqueue(128);

        final Integer value = queue.dequeue();
        assertEquals(128, value);
        assertEquals(0, queue.size());
    }

    @Test
    void size() {
        final Queue<Integer> queue = new Queue<>();
        assertEquals(0, queue.size());

        queue.enqueue(0);
        queue.enqueue(1);
        queue.enqueue(0);

        assertEquals(3, queue.size());

        queue.dequeue();
        queue.dequeue();

        assertEquals(1, queue.size());
    }
}
