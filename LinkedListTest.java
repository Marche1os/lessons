import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListTest {

    private final LinkedList emptyList = new LinkedList();
    private LinkedList completedList = new LinkedList();
    @BeforeEach
    void fillTestData() {
        for (int i = 0; i < 50; i++) {
            Node node = new Node(i);
            completedList.addInTail(node);
        }
    }

    @AfterEach
    void resetTestData() {
        completedList = new LinkedList();
    }

    @Test
    void findAll() {
        for (int i = 10; i >= 0; i--)
            completedList.addInTail(new Node(i));

        assertEquals(2, completedList.findAll(5).size());
        assertEquals(1, completedList.findAll(49).size());
        assertEquals(0, completedList.findAll(50).size());
        assertEquals(61, completedList.count());

        for (Node item : completedList.findAll(49))
            assertEquals(49, item.value);

        for (Node item : completedList.findAll(5))
            assertEquals(5, item.value);
    }

    @Test
    void findAllOnEmptyList() {
        List<Node> nodes = emptyList.findAll(1);
        assertEquals(0, nodes.size());
    }

    @Test
    void findAllOnSingleNodeList() {
        LinkedList list = new LinkedList();
        list.addInTail(new Node(100));

        List<Node> nodes = list.findAll(100);
        assertEquals(1, nodes.size());

        nodes = list.findAll(0);
        assertTrue(nodes.isEmpty());
    }

    @Test
    void removeFromTail() {
        final Node tail = new Node(128);
        completedList.addInTail(tail);

        int size = completedList.count();

        assertSame(completedList.tail, tail);
        assertEquals(128, completedList.tail.value);

        completedList.remove(128);

        assertNotSame(completedList.tail, tail);
        assertNotEquals(128, completedList.tail.value);
        assertEquals(size - 1, completedList.count());
    }

    @Test
    void removeFromHead() {
        final Node node = new Node(128);
        completedList.insertAfter(null, node);

        int size = completedList.count();

        assertSame(completedList.head, node);
        assertEquals(128, completedList.head.value);

        completedList.remove(128);

        assertNotSame(completedList.head, node);
        assertNotEquals(128, completedList.head.value);
        assertEquals(size - 1, completedList.count());
    }

    @Test
    void removeFromEmptyList() {
        boolean isRemoved = emptyList.remove(10);
        assertFalse(isRemoved);
    }

    @Test
    void removFromSingleNodeList() {
        final LinkedList list = new LinkedList();
        list.addInTail(new Node(100));

        assertEquals(1, list.count());
        assertEquals(100, list.head.value);
        assertEquals(100, list.tail.value);

        boolean isRemoved = list.remove(100);

        assertEquals(0, list.count());
        assertTrue(isRemoved);
        assertNull(list.head);
        assertNull(list.tail);
    }

    @Test
    void removeAllFromCompletedList() {
        completedList.addInTail(new Node(200));
        completedList.addInTail(new Node(100));

        completedList.insertAfter(null, new Node(300));
        completedList.insertAfter(null, new Node(100));
        completedList.insertAfter(completedList.head.next.next, new Node(100));

        assertEquals(100, completedList.head.value);
        assertEquals(100, completedList.tail.value);
        assertNotSame(completedList.head, completedList.tail);

        completedList.removeAll(100);

        assertEquals(300, completedList.head.value);
        assertEquals(200, completedList.tail.value);

        Node node = completedList.head;
        while (node != null) {
            assertNotEquals(100, node.value);
            node = node.next;
        }
    }

    @Test
    void removeAllFromEmptyAndSingleList() {
        final LinkedList singleNodeList = new LinkedList();
        singleNodeList.addInTail(new Node(10));

        assertNotNull(singleNodeList.tail);
        assertNotNull(singleNodeList.head);
        assertEquals(1, singleNodeList.count());

        singleNodeList.removeAll(10);

        assertNull(singleNodeList.head);
        assertNull(singleNodeList.tail);
        assertEquals(0, singleNodeList.count());

        emptyList.removeAll(1024);
        assertNull(emptyList.head);
        assertNull(emptyList.tail);
        assertEquals(0, emptyList.count());
    }

    @Test
    void removeAllFromHeadAndTail() {
        final LinkedList list = new LinkedList();
        list.addInTail(new Node(0));
        list.addInTail(new Node(0));
        list.addInTail(new Node(1));
        list.addInTail(new Node(2));
        list.addInTail(new Node(0));
        list.addInTail(new Node(0));

        assertEquals(6, list.count());
        assertEquals(0, list.head.value);
        assertEquals(0, list.tail.value);

        list.removeAll(0);

        assertEquals(2, list.count());
        assertEquals(1, list.head.value);
        assertEquals(2, list.tail.value);
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
    }

    @Test
    void testCountOnAddition() {
        final LinkedList list = new LinkedList();
        assertEquals(0, list.count());

        final int iterationCount = 10;
        for (int i = 0; i < iterationCount; i++)
            list.addInTail(new Node(i));

        assertEquals(iterationCount, list.count());

        final Node head = list.head;
        final Node tail = list.tail;
        final Node node = list.head.next.next;

        list.insertAfter(null, new Node(100));
        list.insertAfter(node, new Node(100));
        list.insertAfter(head, new Node(100));
        list.insertAfter(tail, new Node(100));

        assertEquals(iterationCount + 4, list.count());
    }

    @Test
    @DisplayName("test insertAfter fun when both arguments are null")
    void insertAfterNullArguments() {
        assertNotNull(completedList.head);
        assertNotNull(completedList.tail);
        assertTrue(completedList.count() > 0);

        completedList.insertAfter(null, null);

        assertNull(completedList.tail);
        assertNull(completedList.head);
        assertEquals(0, completedList.count());
    }

    @Test
    @DisplayName("test insertAfter fun when _nodeAfter is null")
    void insertAfterNull() {
        int expectedSize = completedList.count() + 1;

        final Node node = new Node(100);

        assertNotEquals(100, completedList.head.value);

        completedList.insertAfter(null, node);

        assertEquals(100, completedList.head.value);
        assertEquals(expectedSize, completedList.count());
        assertEquals(node, completedList.head);
    }

    @Test
    @DisplayName("test insertAfter method when insertion is null")
    void insertNullAfter() {
        Node nodeAfter = completedList.head;
        int size = 1;
        while (size < completedList.count() / 2) {
            nodeAfter = nodeAfter.next;
            size++;
        }
        completedList.insertAfter(nodeAfter, null);

        assertEquals(size, completedList.count());
        assertNull(nodeAfter.next);
    }

    @Test
    void insertAfterIntoCompletedList() {
        Node insertNode = new Node(5);
        Node afterNode = completedList.head.next;

        int expectedSize = completedList.count() + 1;
        completedList.insertAfter(afterNode, insertNode);
        Node insertedNode = completedList.head.next.next;

        assertEquals(expectedSize, completedList.count());
        assertEquals(5, insertedNode.value);
    }

    @Test
    @DisplayName("test insertAfter fun into empty list")
    void insertAfterIntoEmptyList() {
        Node node1 = new Node(50);
        emptyList.insertAfter(null, node1);

        assertEquals(emptyList.head, node1);
        assertEquals(1, emptyList.count());
        assertSame(emptyList.tail, emptyList.head);

        Node node2 = new Node(500);
        emptyList.insertAfter(node1, node2);
        assertSame(emptyList.tail, node2);
        assertEquals(2, emptyList.count());
    }

    @Test
    void insertAfterIntoSingleNodeList() {
        final LinkedList list = new LinkedList();
        final Node node = new Node(5);
        list.addInTail(node);

        final Node insertAfter = new Node(55);
        list.insertAfter(node, insertAfter);

        assertEquals(2, list.count());
        assertEquals(list.tail, insertAfter);
        assertEquals(55, list.tail.value);
    }
}