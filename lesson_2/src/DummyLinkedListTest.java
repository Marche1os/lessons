import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class DummyLinkedListTest {
    private DummyLinkedList completedList = new DummyLinkedList();
    private DummyLinkedList emptyList = new DummyLinkedList();
    private DummyLinkedList singleNodeList = new DummyLinkedList();
    private DummyLinkedList duoNodeList = new DummyLinkedList();

    private static final int VALUE_1 = 7;
    private static final int VALUE_2 = 26;

    @BeforeEach
    void generateTestData() {
        singleNodeList.addInTail(new Node(VALUE_1));

        for (int i = 0; i < 10; i++)
            completedList.addInTail(new Node(i));

        duoNodeList.addInTail(new Node(VALUE_1));
        duoNodeList.addInTail(new Node(VALUE_2));
    }

    @AfterEach
    void resetTestData() {
        completedList = new DummyLinkedList();
        emptyList = new DummyLinkedList();
        singleNodeList = new DummyLinkedList();
        duoNodeList = new DummyLinkedList();
    }

    @Test
    void addInTailSingleNodeList() {
        assertEquals(VALUE_1, singleNodeList.getHead().value);
        assertEquals(VALUE_1, singleNodeList.getTail().value);
        assertEquals(1, singleNodeList.count());
    }

    @Test
    void addInTailEmptyList() {
        assertEquals(0, emptyList.count());
        assertNull(emptyList.getHead());

        emptyList.addInTail(new Node(128));
        assertEquals(1, emptyList.count());
        assertEquals(128, emptyList.getHead().value);
        assertSame(emptyList.getHead(), emptyList.getTail());

        emptyList.addInTail(new Node(256));
        assertEquals(2, emptyList.count());
        assertEquals(128, emptyList.getHead().value);
        assertEquals(256, emptyList.getTail().value);
        assertNotSame(emptyList.getHead(), emptyList.getTail());
    }

    @Test
    void addInCompletedList() {
        assertEquals(0, completedList.getHead().value);
        assertEquals(10, completedList.count());

        completedList.addInTail(new Node(256));
        assertEquals(256, completedList.getTail().value);
        assertEquals(11, completedList.count());
    }

    @Test
    void findInEmptyList() {
        final Node node = emptyList.find(5);
        assertNull(node);
    }

    @Test
    void findInSingleNodeList() {
        Node node = singleNodeList.find(VALUE_1);
        assertNotNull(node);
        assertEquals(VALUE_1, node.value);

        node = singleNodeList.find(VALUE_2);
        assertNull(node);
    }

    @Test
    void findInDuoNodeList() {
        Node node = duoNodeList.find(VALUE_2);
        assertNotNull(node);
        assertEquals(VALUE_2, node.value);

        node = duoNodeList.find(VALUE_1);
        assertNotNull(node);
        assertEquals(VALUE_1, node.value);

        node = duoNodeList.find(512);
        assertNull(node);
    }

    @Test
    void findInCompletedList() {
        Node node = completedList.find(5);
        assertNotNull(node);
        assertEquals(5, node.value);

        node = completedList.find(4);
        assertNotNull(node);
        assertEquals(4, node.value);

        node = completedList.find(6);
        assertNotNull(node);
        assertEquals(6, node.value);

        node = completedList.find(128);
        assertNull(node);
    }

    @Test
    void findAll() {
        ArrayList<Node> nodes;

        completedList.addInTail(new Node(512));
        completedList.addInTail(new Node(512));
        completedList.addInTail(new Node(512));

        nodes = completedList.findAll(512);

        assertEquals(3, nodes.size());
        for (Node node : nodes) assertEquals(512, node.value);

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
    void removeFromCompletedList() {
        final int value = completedList.count() / 2 + 1;
        final int value2 = completedList.count() / 2 - 1;

        int size = completedList.count();
        boolean isRemoved = completedList.remove(value);
        assertTrue(isRemoved);
        assertEquals(size - 1, completedList.count());

        isRemoved = completedList.remove(value2);
        assertTrue(isRemoved);
        assertEquals(size - 2, completedList.count());

        int curSize = 0;
        Node node = completedList.getHead();
        while (node != completedList.getTail().next) {
            if (!(node instanceof DummyNode)) {
                curSize++;
                assertNotEquals(value, node.value);
                assertNotEquals(value2, node.value);
            }

            node = node.next;
        }
        assertEquals(size - 2, curSize);
        assertEquals(curSize, completedList.count());
    }

    @Test
    void removeFromEmptyList() {
        boolean isRemoved = emptyList.remove(Integer.MIN_VALUE);
        assertFalse(isRemoved);
        assertEquals(0, emptyList.count());

        Node node = emptyList.getHead();
        while (node != null) {
            assertFalse(node instanceof Node);
            node = node.next;
        }
    }

    @Test
    void removeFromSingleNodeList() {
        boolean isRemoved = singleNodeList.remove(Integer.MIN_VALUE);
        assertFalse(isRemoved);
        assertEquals(1, singleNodeList.count());
    }

    @Test
    void removeAllFromCompletedList() {
        final DummyLinkedList list = new DummyLinkedList();
        list.addInTail(new Node(0));
        list.addInTail(new Node(1));
        list.addInTail(new Node(0));
        list.addInTail(new Node(1));
        list.addInTail(new Node(0));

        list.removeAll(0);
        assertEquals(2, list.count());

        Node node = list.getHead();
        while (node != null) {
            assertNotEquals(0, node.value);
            node = node.next;
        }
    }

    @Test
    void removeAllFromEmptyList() {
        emptyList.removeAll(128);
        assertNull(emptyList.getHead());
        assertEquals(0, emptyList.count());
    }

    @Test
    void removeAllFromSingleList() {
        assertEquals(VALUE_1, singleNodeList.getHead().value);
        singleNodeList.removeAll(VALUE_1);
        singleNodeList.addInTail(new Node(512));
        assertEquals(512, singleNodeList.getHead().value);
        assertEquals(512, singleNodeList.getTail().value);

        assertEquals(1, singleNodeList.count());
    }

    @Test
    void insertAfterHead() {
        int size = completedList.count();
        Node insert = new Node(512);
        completedList.insertAfter(null, insert);
        assertEquals(size + 1, completedList.count());
        assertEquals(512, completedList.getHead().value);
        assertEquals(0, completedList.getHead().next.value);
    }

    @Test
    void insertAfterTail() {
        int size = completedList.count();
        Node insert = new Node(512);
        completedList.insertAfter(completedList.getTail(), insert);
        assertEquals(size + 1, completedList.count());
        assertEquals(512, completedList.getTail().value);
        assertEquals(9, completedList.getTail().prev.value);
    }

    @Test
    void insertAfterIntoMiddle() {
        Node insert = new Node(512);
        Node nodeAfter = completedList.getHead().next.next.next;
        completedList.insertAfter(nodeAfter, insert);
        assertEquals(512, completedList.getHead().next.next.next.next.value);
        assertEquals(3, completedList.getHead().next.next.next.next.prev.value);
    }

    @Test
    void insertAfterTestNullInsert() {
        completedList.insertAfter(completedList.getHead().next.next, null);
        assertEquals(3, completedList.count());
        assertEquals(completedList.getTail().value, completedList.getHead().next.next.value);
    }

    @Test
    void insertAfterEmptyList() {
        emptyList.insertAfter(null, new Node(512));
        assertEquals(1, emptyList.count());
        assertEquals(512, emptyList.getHead().value);
        assertEquals(512, emptyList.getTail().value);
    }

    @Test
    void insertAfterSingleNodeList() {
        int size = singleNodeList.count();
        singleNodeList.insertAfter(singleNodeList.getHead(), new Node(512));
        assertEquals(512, singleNodeList.getHead().next.value);
        assertEquals(512, singleNodeList.getTail().value);
        assertEquals(size + 1, singleNodeList.count());
    }

    @Test
    void insertAfterDuoNodeList() {
        int size = duoNodeList.count();
        duoNodeList.insertAfter(duoNodeList.getHead(), new Node(512));
        assertEquals(VALUE_1, duoNodeList.getHead().value);
        assertEquals(VALUE_2, duoNodeList.getTail().value);
        assertEquals(512, duoNodeList.getHead().next.value);
        assertEquals(size + 1, duoNodeList.count());
    }

    @Test
    void clear() {
        emptyList.clear();

        completedList.clear();
        assertNull(completedList.getHead());
        assertNull(completedList.getTail());
    }
}
