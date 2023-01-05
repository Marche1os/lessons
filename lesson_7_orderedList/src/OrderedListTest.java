import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OrderedListTest {
    private OrderedList<Integer> emptyListAsc = new OrderedList<>(true);
    private OrderedList<Integer> emptyListDesc = new OrderedList<>(false);

    private OrderedList<Integer> oneElementListAsc = new OrderedList<>(true);
    private OrderedList<Integer> oneElementListDesc = new OrderedList<>(false);

    private OrderedList<Integer> duoElementsListAsc = new OrderedList<>(true);
    private OrderedList<Integer> duoElementsListDesc = new OrderedList<>(false);

    private OrderedList<Integer> orderedListAsc = new OrderedList<>(true);
    private OrderedList<Integer> orderedListDesc = new OrderedList<>(false);

    private final static int VALUE_1 = 64;
    private final static int VALUE_2 = 128;
    private final static int SIZE = 20;

    @BeforeEach
    void generateTestData() {
        emptyListAsc = new OrderedList<>(true);
        emptyListDesc = new OrderedList<>(false);

        oneElementListAsc = new OrderedList<>(true);
        oneElementListDesc = new OrderedList<>(false);

        duoElementsListAsc = new OrderedList<>(true);
        duoElementsListDesc = new OrderedList<>(false);

        orderedListAsc = new OrderedList<>(true);
        orderedListDesc = new OrderedList<>(false);

        oneElementListAsc.add(VALUE_1);
        oneElementListDesc.add(VALUE_1);

        duoElementsListAsc.add(VALUE_1);
        duoElementsListAsc.add(VALUE_2);

        duoElementsListDesc.add(VALUE_1);
        duoElementsListDesc.add(VALUE_2);

        for (int i = 0; i < SIZE; i += 2) {
            orderedListAsc.add(i);
            orderedListDesc.add(i);
        }

    }

    @AfterEach
    void resetTestData() {
        emptyListAsc = new OrderedList<>(true);
        emptyListDesc = new OrderedList<>(false);

        oneElementListAsc = new OrderedList<>(true);
        oneElementListDesc = new OrderedList<>(false);

        duoElementsListAsc = new OrderedList<>(true);
        duoElementsListDesc = new OrderedList<>(false);

        orderedListAsc = new OrderedList<>(true);
        orderedListDesc = new OrderedList<>(false);
    }

    @Test
    void addInEmptyListAsc() {
        assertEquals(emptyListAsc.count(), 0);
        assertNull(emptyListAsc.head);
        assertNull(emptyListAsc.tail);

        emptyListAsc.add(VALUE_1);

        assertEquals(emptyListAsc.count(), 1);
        assertNotNull(emptyListAsc.head);
        assertEquals(emptyListAsc.head.value, VALUE_1);

        emptyListAsc.add(VALUE_2);

        assertEquals(emptyListAsc.count(), 2);
        assertEquals(VALUE_1, emptyListAsc.head.value);
        assertEquals(VALUE_2, emptyListAsc.tail.value);
        assertSame(emptyListAsc.head.next, emptyListAsc.tail);
        assertSame(emptyListAsc.head, emptyListAsc.tail.prev);
    }

    @Test
    void addInEmptyListDesc() {
        assertEquals(emptyListDesc.count(), 0);
        assertNull(emptyListDesc.head);
        assertNull(emptyListDesc.tail);

        emptyListDesc.add(VALUE_1);

        assertEquals(emptyListDesc.count(), 1);
        assertNotNull(emptyListDesc.head);
        assertEquals(emptyListDesc.head.value, VALUE_1);

        emptyListDesc.add(VALUE_2);

        assertEquals(emptyListDesc.count(), 2);
        assertEquals(VALUE_1, emptyListDesc.tail.value);
        assertEquals(VALUE_2, emptyListDesc.head.value);
        assertSame(emptyListDesc.head.next, emptyListDesc.tail);
        assertSame(emptyListDesc.head, emptyListDesc.tail.prev);

    }

    @Test
    void addInAscList() {
        final int count = orderedListDesc.count();

        orderedListAsc.add(SIZE / 2);
        orderedListAsc.add(-10);
        orderedListAsc.add(-19);
        orderedListAsc.add(18);
        orderedListAsc.add(22);
        orderedListAsc.add(20);

        assertEquals(count + 6, orderedListAsc.count());

        assertEquals(-19, orderedListAsc.head.value);
        assertEquals(22, orderedListAsc.tail.value);

        Node node = orderedListAsc.head.next;

        while (node != null) {
            final int currentVal = (Integer) node.value;
            final int previewVal = (Integer) node.prev.value;

            assertTrue(currentVal >= previewVal);

            node = node.next;
        }
    }

    @Test
    void addInDescList() {
        final int count = orderedListDesc.count();

        orderedListDesc.add(40);
        orderedListDesc.add(60);
        orderedListDesc.add(80);

        orderedListDesc.add(-10);
        orderedListDesc.add(-15);
        orderedListDesc.add(-150);
        orderedListDesc.add(-20);

        assertEquals(count + 7, orderedListDesc.count());

        assertEquals(80, orderedListDesc.head.value);
        assertEquals(-150, orderedListDesc.tail.value);

        Node node = orderedListDesc.head.next;

        while (node != null) {
            final int currentVal = (int) node.value;
            final int previewVal = (int) node.prev.value;

            assertTrue(previewVal >= currentVal);

            node = node.next;
        }

    }

    @Test
    void addInTheMiddleAsc() {
        final int delta = 14;
        final int count = duoElementsListAsc.count();

        duoElementsListAsc.add(VALUE_1 + delta);

        assertEquals(count + 1, duoElementsListAsc.count());
        assertEquals(VALUE_1, duoElementsListAsc.head.value);
        assertEquals(VALUE_2, duoElementsListAsc.tail.value);
        assertEquals(VALUE_1 + delta, duoElementsListAsc.head.next.value);
        assertEquals(VALUE_1 + delta, duoElementsListAsc.tail.prev.value);
    }

    @Test
    void addInTheMiddleDesc() {
        final int delta = 14;
        final int count = duoElementsListDesc.count();

        duoElementsListDesc.add(VALUE_1 + delta);

        assertEquals(count + 1, duoElementsListDesc.count());
        assertEquals(VALUE_1, duoElementsListDesc.tail.value);
        assertEquals(VALUE_2, duoElementsListDesc.head.value);
        assertEquals(VALUE_1 + delta, duoElementsListDesc.head.next.value);
        assertEquals(VALUE_1 + delta, duoElementsListDesc.tail.prev.value);
    }

    @Test
    void findInEmptyList() {
        assertEquals(0, emptyListAsc.count());
        assertNull(emptyListAsc.head);
        assertNull(emptyListAsc.tail);

        Node find = emptyListAsc.find(-1);
        assertNull(find);
    }

    @Test
    void findInSingleElementList() {
        assertEquals(1, oneElementListAsc.count());
        assertEquals(VALUE_1, oneElementListAsc.head.value);

        Node find = oneElementListAsc.find(VALUE_1);
        assertEquals(VALUE_1, find.value);

        assertEquals(VALUE_1, oneElementListAsc.head.value);
        assertEquals(1, oneElementListAsc.count());

        find = oneElementListAsc.find(VALUE_2);
        assertNull(find);

        assertEquals(VALUE_1, oneElementListAsc.head.value);
        assertEquals(1, oneElementListAsc.count());
    }

    @Test
    void findInDuoElementAscList() {
        assertEquals(2, duoElementsListAsc.count());
        assertEquals(VALUE_1, duoElementsListAsc.head.value);
        assertEquals(VALUE_2, duoElementsListAsc.tail.value);

        Node find = duoElementsListAsc.find(VALUE_1);
        assertEquals(VALUE_1, find.value);

        find = duoElementsListAsc.find(VALUE_2);
        assertEquals(VALUE_2, find.value);

        find = duoElementsListAsc.find(1000);
        assertNull(find);

        assertEquals(VALUE_1, duoElementsListAsc.head.value);
        assertEquals(VALUE_2, duoElementsListAsc.tail.value);

    }

    @Test
    void findInAscList() {
        Node find = orderedListAsc.find(0);
        assertEquals(0, find.value);

        find = orderedListAsc.find(10);
        assertEquals(10, find.value);

        find = orderedListAsc.find(18);
        assertEquals(18, find.value);

        find = orderedListAsc.find(1000);
        assertNull(find);
    }

    @Test
    void findInDescList() {
        Node find = orderedListDesc.find(0);
        assertEquals(0, find.value);

        find = orderedListDesc.find(10);
        assertEquals(10, find.value);

        find = orderedListDesc.find(18);
        assertEquals(18, find.value);

        find = orderedListDesc.find(1000);
        assertNull(find);
    }

    @Test
    void deleteFromOneElementList() {
        assertEquals(1, oneElementListAsc.count());
        assertEquals(VALUE_1, oneElementListAsc.head.value);

        oneElementListAsc.delete(VALUE_1);

        assertNull(oneElementListAsc.head);
        assertNull(oneElementListAsc.tail);
        assertEquals(0, oneElementListAsc.count());
    }

    @Test
    void deleteFromHeadDuoElementAscList() {
        assertEquals(2, duoElementsListAsc.count());

        assertEquals(VALUE_1, duoElementsListAsc.head.value);
        assertEquals(VALUE_2, duoElementsListAsc.tail.value);

        duoElementsListAsc.delete(VALUE_1);

        assertEquals(VALUE_2, duoElementsListAsc.head.value);
        assertSame(duoElementsListAsc.head, duoElementsListAsc.tail);
        assertEquals(1, duoElementsListAsc.count());
    }

    @Test
    void deleteFromTailDuoElementAscList() {
        assertEquals(2, duoElementsListAsc.count());

        assertEquals(VALUE_1, duoElementsListAsc.head.value);
        assertEquals(VALUE_2, duoElementsListAsc.tail.value);

        duoElementsListAsc.delete(VALUE_2);
        assertEquals(VALUE_1, duoElementsListAsc.head.value);
        assertSame(duoElementsListAsc.head, duoElementsListAsc.tail);
        assertEquals(1, duoElementsListAsc.count());
    }

    @Test
    void deleteFromAscList() {
        final int count = orderedListAsc.count();

        orderedListAsc.delete(10);
        orderedListAsc.delete(0);
        orderedListAsc.delete(18);

        assertEquals(count - 3, orderedListAsc.count());

        Node node = orderedListAsc.head;
        while (node != null) {
            assertTrue(!node.value.equals(10));
            assertTrue(!node.value.equals(0));
            assertTrue(!node.value.equals(18));

            node = node.next;
        }
    }

    @Test
    void deleteFromDescList() {
        final int count = orderedListDesc.count();

        orderedListDesc.delete(10);
        orderedListDesc.delete(0);
        orderedListDesc.delete(18);

        assertEquals(count - 3, orderedListDesc.count());

        Node node = orderedListDesc.head;
        while (node != null) {
            assertTrue(!node.value.equals(10));
            assertTrue(!node.value.equals(0));
            assertTrue(!node.value.equals(18));

            node = node.next;
        }
    }

    @Test
    void clear() {
        assertNotNull(orderedListAsc.head);
        assertNotNull(orderedListAsc.tail);
        assertTrue(orderedListAsc.count() > 0);

        orderedListAsc.clear(true);

        assertEquals(0, orderedListAsc.count());
        assertNull(orderedListAsc.head);
        assertNull(orderedListAsc.tail);

    }
}
