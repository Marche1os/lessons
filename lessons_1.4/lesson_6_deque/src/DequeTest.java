import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DequeTest {
    private Deque<Integer> deque;
    private Deque<Integer> emptyDeque;

    @BeforeEach
    void generateTestData() {
        deque = new Deque<>();
        emptyDeque = new Deque<>();

        for (int i = 0; i < 10; i++) {
            deque.addTail(i);
        }
    }

    @AfterEach
    void clearTestData() {
        deque = new Deque<>();
        emptyDeque = new Deque<>();
    }

    @Test
    void addFront() {
        final int size = deque.size();
        deque.addFront(512);

        assertEquals(deque.size(), size + 1);
        assertEquals(512, deque.removeFront());
        assertEquals(deque.size(), size);

        assertNotEquals(512, deque.removeFront());
    }

    @Test
    void addFrontInEmpty() {
        assertEquals(0, emptyDeque.size());

        assertNull(emptyDeque.removeFront());
        assertNull(emptyDeque.removeTail());

        emptyDeque.addFront(128);

        assertEquals(1, emptyDeque.size());
        assertEquals(128, emptyDeque.removeFront());

        assertEquals(0, emptyDeque.size());
    }

    @Test
    void addTail() {
        final int size = deque.size();
        deque.addTail(512);

        assertEquals(size + 1, deque.size());

        assertNotEquals(512, deque.removeFront());
        assertEquals(512, deque.removeTail());

        assertEquals(size - 1, deque.size());
    }

    @Test
    void addTailInEmpty() {
        final int size = emptyDeque.size();

        emptyDeque.addTail(512);

        assertEquals(size + 1, emptyDeque.size());

        assertEquals(512, emptyDeque.removeTail());

        emptyDeque.addTail(128);
        emptyDeque.addTail(256);
        emptyDeque.addTail(512);

        assertNotEquals(512, emptyDeque.removeFront());
        assertEquals(512, emptyDeque.removeTail());

    }

    @Test
    void removeFront() {
        final int size = deque.size();

        assertEquals(0, deque.removeFront());
        assertEquals(size - 1, deque.size());

        deque.addFront(128);

        assertEquals(128, deque.removeFront());
        assertEquals(size - 1, deque.size());
    }

    @Test
    void removeFrontFromEmpty() {
        assertNull(emptyDeque.removeFront());
    }

    @Test
    void removeTail() {
        final int size = deque.size();

        assertEquals(9, deque.removeTail());
        assertEquals(size - 1, deque.size());

        deque.addTail(128);

        assertEquals(128, deque.removeTail());
        assertEquals(size - 1, deque.size());
    }

    @Test
    void removeTailFromEmpty() {
        assertNull(emptyDeque.removeTail());
    }
}