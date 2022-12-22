import java.util.ArrayList;

public class DummyLinkedList {
    private final DummyNode dummyHead;
    private final DummyNode dummyTail;
    private int size;

    public DummyLinkedList() {

        dummyHead = new DummyNode();
        dummyTail = new DummyNode();
        dummyTail.prev = dummyHead;

        size = 0;
    }

    public void addInTail(Node _item) {
        if (_item == null)
            return;

        dummyTail.prev.next = _item;
        _item.prev = dummyTail.prev;
        _item.next = dummyTail;
        dummyTail.prev = _item;
        dummyTail.next = null;

        size++;
    }

    public Node find(int _value) {
        Node left = dummyHead.next;
        while (left != null) {
            if (left.value == _value)
                return left;

            left = left.next;
        }

        return null;
    }

    public ArrayList<Node> findAll(int _value) {
        final ArrayList<Node> nodes = new ArrayList<>();

        Node left = dummyHead.next;
        while (left != null) {
            if (left.value == _value) {
                nodes.add(left);
            }
            left = left.next;
        }

        return nodes;
    }

    public boolean remove(int _value) {
        if (size == 0)
            return false;

        Node node = getHead();
        while (node != null && !(node instanceof DummyNode)) {
            if (_value == node.value) {
                node.next.prev = node.prev;
                node.prev.next = node.next;
                size--;
                return true;
            }
            node = node.next;
        }
        return false;
    }

    public void removeAll(int _value) {
        if (size == 0)
            return;

        Node node = getHead();
        while (node != null && !(node instanceof DummyNode)) {
            if (_value == node.value) {
                node.prev.next = node.next;
                node.next.prev = node.prev;
                size--;
            }

            node = node.next;
        }
    }

    public void insertAfter(Node _nodeAfter, Node _nodeToInsert) {
        if (_nodeAfter == null && _nodeToInsert == null) {
            clear();
            return;
        }

        if (_nodeAfter == null && size == 0) {
            addInTail(_nodeToInsert);
            return;
        }

        if (_nodeAfter == dummyTail.prev && _nodeToInsert != null) {
            addInTail(_nodeToInsert);
            return;
        }

        if (_nodeAfter == null) {
            _nodeToInsert.next = dummyHead.next;
            _nodeToInsert.prev = dummyHead;
            dummyHead.next = _nodeToInsert;
            size++;
            return;
        }

        if (_nodeToInsert != null) {
            _nodeToInsert.prev = _nodeAfter;
            _nodeToInsert.next = _nodeAfter.next;
            _nodeToInsert.next.prev = _nodeToInsert;
            _nodeAfter.next = _nodeToInsert;
            size++;
        }

        if (_nodeToInsert == null) {
            _nodeAfter.next = null;
            dummyTail.prev = _nodeAfter;
            int newSize = 0;
            Node node = getHead();
            while (node != null && !(node instanceof DummyNode)) {
                newSize++;
                node = node.next;
            }
            size = newSize;
        }
    }

    public void clear() {
        dummyHead.next = null;
        dummyTail.prev = null;
        size = 0;
    }

    public int count() {
        return size;
    }

    public Node getHead() {
        return dummyHead.next;
    }

    public Node getTail() {
        return dummyTail.prev;
    }
}

class DummyNode extends Node {
    private static final int NO_VALUE = Integer.MIN_VALUE;

    public DummyNode() {
        super(NO_VALUE);
    }
}
