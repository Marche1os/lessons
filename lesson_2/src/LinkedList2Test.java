import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class LinkedList2Test {
    private final LinkedList2 emptyList = new LinkedList2();
    private LinkedList2 completedList = new LinkedList2();
    private LinkedList2 singleNodeList = new LinkedList2();
    private LinkedList2 duoNodeList = new LinkedList2();

    private static final int VALUE_1 = 149;
    private static final int VALUE_2 = 7;

    @BeforeEach
    void generateTestData() {
        completedList.addInTail(new Node(VALUE_1));
        completedList.addInTail(new Node(VALUE_2));

        for (int i = 0; i < 50; i++) {
            if (i % 10 == 0) {
                completedList.addInTail(new Node(VALUE_2));
                completedList.addInTail(new Node(VALUE_1));
            }
            completedList.addInTail(new Node(i));
        }

        completedList.addInTail(new Node(VALUE_1));
        completedList.addInTail(new Node(VALUE_2));

        singleNodeList.addInTail(new Node(VALUE_1));

        duoNodeList.addInTail(new Node(VALUE_1));
        duoNodeList.addInTail(new Node(VALUE_2));
    }

    @AfterEach
    void resetTestData() {
        completedList = new LinkedList2();
        singleNodeList = new LinkedList2();
        duoNodeList = new LinkedList2();
    }

    @Test
    void find() {
        Node node = duoNodeList.find(VALUE_1);
        assertEquals(VALUE_1, node.value);
        assertEquals(node, duoNodeList.head);

        node = duoNodeList.find(VALUE_2);
        assertEquals(VALUE_2, node.value);
        assertEquals(node, duoNodeList.tail);

        node = singleNodeList.find(VALUE_1);
        assertEquals(VALUE_1, node.value);
        assertEquals(node, singleNodeList.head);
        assertEquals(node, singleNodeList.tail);
    }

    @Test
    void findInEmptyList() {
        final Node node = emptyList.find(-124);
        assertNull(node);
    }

    @Test
    void findNotExistingValue() {
        Node node = duoNodeList.find(-124);
        assertNull(node);

        node = singleNodeList.find(-124);
        assertNull(node);

        node = completedList.find(-124);
        assertNull(node);
    }

    @Test
    void findAll() {
        ArrayList<Node> nodes;

        nodes = completedList.findAll(VALUE_1);

        assertEquals(7, nodes.size());
        for (Node node : nodes) assertEquals(VALUE_1, node.value);

        nodes = singleNodeList.findAll(VALUE_1);
        assertEquals(1, nodes.size());
        assertEquals(VALUE_1, nodes.get(0).value);

        nodes = duoNodeList.findAll(VALUE_2);
        assertEquals(1, nodes.size());
        assertEquals(VALUE_2, nodes.get(0).value);
    }

    @Test
    void findAllInEmptyList() {
        ArrayList<Node> nodes = emptyList.findAll(VALUE_1);
        assertTrue(nodes.isEmpty());
    }

    @Test
    void findNotExistedValue() {
        ArrayList<Node> nodes = completedList.findAll(-128);
        assertTrue(nodes.isEmpty());

        nodes = duoNodeList.findAll(-128);
        assertTrue(nodes.isEmpty());

        nodes = singleNodeList.findAll(-128);
        assertTrue(nodes.isEmpty());
    }

    @Test
    void remove() {
        boolean isRemoved = duoNodeList.remove(VALUE_1);
        assertTrue(isRemoved);
        assertSame(duoNodeList.head, duoNodeList.tail);
        assertEquals(1, duoNodeList.count());

        isRemoved = singleNodeList.remove(VALUE_1);
        assertTrue(isRemoved);
        assertNull(singleNodeList.head);
        assertNull(singleNodeList.tail);
        assertEquals(0, singleNodeList.count());

        int size = completedList.count();
        isRemoved = completedList.remove(0);
        assertTrue(isRemoved);

        isRemoved = completedList.remove(1);
        assertTrue(isRemoved);

        isRemoved = completedList.remove(49);
        assertTrue(isRemoved);
        assertEquals(size - 3, completedList.count());

        Node node = completedList.head;
        while (node != null) {
            int value = node.value;
            assertTrue(value != 0 && value != 1 && value != 59);

            node = node.next;
        }
    }

    @Test
    void removeFromEmptyList() {
        boolean isRemoved = emptyList.remove(10);
        assertFalse(isRemoved);
    }

    @Test
    void removeNotExistedValue() {
        int size = completedList.count();
        boolean isRemoved = completedList.remove(-128);

        assertFalse(isRemoved);
        assertEquals(size, completedList.count());

        size = singleNodeList.count();
        isRemoved = singleNodeList.remove(-128);

        assertFalse(isRemoved);
        assertEquals(size, singleNodeList.count());

        size = duoNodeList.count();
        isRemoved = duoNodeList.remove(-128);

        assertFalse(isRemoved);
        assertEquals(size, duoNodeList.count());
    }

    @Test
    void removeFromHeadInCompletedList() {
        int size = completedList.count();
        assertEquals(VALUE_1, completedList.head.value);

        boolean isRemoved = completedList.remove(VALUE_1);
        assertTrue(isRemoved);
        assertEquals(size - 1, completedList.count());
        assertTrue(completedList.head.value != VALUE_1);
    }

    @Test
    void removeFromTailInCompletedList() {
        completedList.addInTail(new Node(512));
        int size = completedList.count();

        assertEquals(512, completedList.tail.value);

        boolean isRemoved = completedList.remove(512);
        assertTrue(isRemoved);
        assertEquals(size - 1, completedList.count());
        assertTrue(completedList.tail.value != 512);
    }

    @Test
    void removeFromMiddleInCompletedList() {
        int size = completedList.count();

        boolean isRemoved = completedList.remove(40);
        assertTrue(isRemoved);
        assertEquals(size - 1, completedList.count());

        Node node = completedList.head;
        while (node != null) {
            assertTrue(node.value != 40);
            node = node.next;
        }

        isRemoved = completedList.remove(19);
        assertTrue(isRemoved);
        assertEquals(size - 2, completedList.count());

        node = completedList.head;
        while (node != null) {
            assertTrue(node.value != 19);
            node = node.next;
        }
    }

    @Test
    void removeAll() {
        completedList.removeAll(VALUE_1);
        completedList.removeAll(VALUE_2);

        Node node = completedList.head;
        while (node != null) {
            assertNotEquals(VALUE_1, node.value);
            assertNotEquals(VALUE_2, node.value);
            node = node.next;
        }
    }

    @Test
    void removeAllFromMiddle() {
        completedList.removeAll(VALUE_1);
        final int value1 = completedList.count() / 2 + 1;
        final int value2 = completedList.count() / 2 - 1;
        completedList.removeAll(value1);
        completedList.removeAll(value2);

        Node node = completedList.head;
        while (node != null) {
            assertNotEquals(VALUE_1, node.value);
            assertNotEquals(value1, node.value);
            assertNotEquals(value2, node.value);
            node = node.next;
        }
    }

    @Test
    void removeAllFromEmptyList() {
        emptyList.removeAll(-2);
        assertNull(emptyList.head);
        assertNull(emptyList.tail);
        assertEquals(0, emptyList.count());
    }

    @Test
    void removeAllFromCustomList() {
        final LinkedList2 list = new LinkedList2();
        list.addInTail(new Node(0));
        list.addInTail(new Node(1));
        list.addInTail(new Node(0));
        list.addInTail(new Node(1));

        list.removeAll(1);

        assertEquals(2, list.count());

        Node node = list.head;
        while (node != null) {
            assertNotEquals(1, node.value);
            node = node.next;
        }

        list.removeAll(0);

        node = list.head;
        while (node != null) {
            assertNotEquals(0, node.value);
            node = node.next;
        }

        assertEquals(0, list.count());

        list.addInTail(new Node(0));
        list.addInTail(new Node(1));
        list.addInTail(new Node(0));
        list.addInTail(new Node(1));
        list.addInTail(new Node(0));

        list.removeAll(0);

        assertEquals(2, list.count());
        assertEquals(1, list.head.value);
        assertEquals(1, list.tail.value);
        assertSame(list.head.next, list.tail);
    }

    @Test
    void removeAllFromDuoNodeList() {
        duoNodeList.removeAll(VALUE_1);

        assertSame(duoNodeList.head, duoNodeList.tail);

        Node node = duoNodeList.head;
        while (node != null) {
            assertTrue(node.value != VALUE_1);
            node = node.next;
        }
    }

    @Test
    void removeAllNodes() {
        singleNodeList.removeAll(VALUE_1);
        assertEquals(0, singleNodeList.count());

        assertNull(singleNodeList.head);
        assertNull(singleNodeList.tail);
    }

    @Test
    void clear() {
        assertNotNull(completedList.head);
        assertNotNull(completedList.tail);
        assertTrue(completedList.count() > 0);

        completedList.clear();

        assertNull(completedList.head);
        assertNull(completedList.tail);
        assertEquals(0, completedList.count());

        emptyList.clear();

        assertNull(emptyList.head);
        assertNull(emptyList.tail);
        assertEquals(0, emptyList.count());
    }

    @Test
    void count() {
        int size = completedList.count();

        completedList.addInTail(new Node(148));
        completedList.addInTail(new Node(148));
        completedList.addInTail(new Node(148));

        assertEquals(size + 3, completedList.count());

        completedList.removeAll(148);
        assertEquals(size, completedList.count());

        completedList.remove(0);
        completedList.remove(completedList.count() / 2);
        completedList.remove(0);

        assertEquals(size - 2, completedList.count());
    }

    @Test
    void insertAfterIntoHead() {
        Node insert = new Node(512);
        completedList.insertAfter(null, insert);

        assertSame(insert, completedList.head);
        assertEquals(512, completedList.head.value);
    }

    @Test
    void insertAfter() {
        int size = completedList.count();
        Node insert = new Node(512);
        completedList.insertAfter(completedList.head.next.next, insert);

        assertSame(insert, completedList.head.next.next.next);
        assertSame(insert, completedList.head.next.next.next.next.prev);
        assertEquals(size + 1, completedList.count());
        assertEquals(512, completedList.head.next.next.next.value);

        completedList.insertAfter(completedList.head.next.next, null);
        assertEquals(3, completedList.count());
        assertSame(completedList.head.next.next, completedList.tail);
    }

    @Test
    void insertAfterSingleListNull() {
        assertSame(singleNodeList.head, singleNodeList.tail);

        final Node insert = new Node(512);
        singleNodeList.insertAfter(null, insert);

        assertEquals(2, singleNodeList.count());
        assertEquals(512, singleNodeList.head.value);
        assertNotSame(singleNodeList.head, singleNodeList.tail);
    }

    @Test
    void insertAfterSingleListHead() {
        final int headValue = singleNodeList.head.value;
        final Node insert = new Node(512);

        singleNodeList.insertAfter(singleNodeList.head, insert);

        assertEquals(2, singleNodeList.count());
        assertEquals(headValue, singleNodeList.head.value);
        assertEquals(512, singleNodeList.head.next.value);

        assertSame(singleNodeList.head, singleNodeList.head.next.prev);
        assertSame(singleNodeList.head.next, singleNodeList.tail);
    }

    @Test
    void insertAfterDuoElementsList() {
        final Node insert = new Node(149);
        duoNodeList.insertAfter(insert, null);

        assertEquals(2, duoNodeList.count());
        assertEquals(VALUE_1, duoNodeList.head.value);
        assertEquals(VALUE_2, duoNodeList.tail.value);

        duoNodeList.insertAfter(duoNodeList.tail, new Node(512));
        assertEquals(512, duoNodeList.tail.value);
        assertSame(duoNodeList.head.next.next, duoNodeList.tail);
        assertEquals(3, duoNodeList.count());
    }

    @Test
    void insertAsFirst() {
        emptyList.insertAsFirst(new Node(1));
        assertEquals(1, emptyList.head.value);
        assertEquals(1, emptyList.tail.value);
        assertEquals(1, emptyList.count());
    }
}