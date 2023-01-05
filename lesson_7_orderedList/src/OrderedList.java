import java.util.ArrayList;


class Node<T> {
    public T value;
    public Node<T> next;
    public Node prev;

    public Node(T _value) {
        value = _value;
        next = null;
        prev = null;
    }
}

public class OrderedList<T> {
    public Node<T> head, tail;
    private boolean _ascending;
    private int size;

    public OrderedList(boolean asc) {
        head = null;
        tail = null;
        _ascending = asc;
        size = 0;
    }

    public int compare(T v1, T v2) {
        final Integer num1 = (Integer) v1;
        final Integer num2 = (Integer) v2;

        if (num1 < num2)
            return -1;
        else if (num1 > num2)
            return 1;

        return 0;
    }

    public void add(T value) {
        final Node<T> nodeInsert = new Node<>(value);

        if (head == null) {
            addInTail(nodeInsert);
            return;
        }

        final int compareHead = compare(value, head.value);
        final int compareTail = compare(value, tail.value);

        if (_ascending && (compareTail == 1 || compareTail == 0)) {
            addInTail(nodeInsert);
            return;
        }

        if (_ascending && (compareHead == -1)) {
            addInHead(nodeInsert);
            return;
        }

        if (!_ascending && (compareTail == -1)) {
            addInTail(nodeInsert);
            return;
        }

        if (!_ascending && (compareHead == 1 || compareHead == 0)) {
            addInHead(nodeInsert);
            return;
        }

        if (_ascending)
            addInAscOrderedList(nodeInsert);
        else
            addInDescOrderedList(nodeInsert);

    }

    private void addInAscOrderedList(final Node<T> insert) {
        Node node = tail;
        final T value = insert.value;

        while (node != null) {
            final int compareNode = compare(value, (T) node.value);
            final boolean found = (compareNode == 1 || compareNode == 0);

            if (found) {
                insert.prev = node;
                insert.next = node.next;
                insert.prev.next = insert;
                insert.next.prev = insert;

                size++;

                return;
            }

            node = node.prev;
        }
    }

    private void addInDescOrderedList(final Node<T> insert) {
        Node node = head;
        final T value = insert.value;

        while (node != null) {
            final int compareNode = compare(value, (T) node.value);
            final boolean found = (compareNode == 1 || compareNode == 0);

            if (found) {
                insert.prev = node.prev;
                insert.next = node;
                insert.prev.next = insert;
                insert.next.prev = insert;

                size++;

                return;
            }

            node = node.next;
        }
    }

    private void addInHead(final Node<T> insert) {
        if (head == null)
            addInTail(insert);
        else {
            insert.next = head;
            head.prev = insert;
            head = insert;
            head.prev = null;
            size++;
        }
    }

    private void addInTail(final Node<T> insert) {
        if (head == null) {
            head = insert;
            head.next = null;
            head.prev = null;
        } else {
            tail.next = insert;
            insert.prev = tail;
        }
        tail = insert;
        size++;
    }

    public Node<T> find(T val) {
        if (head == null)
            return null;

        if (head.value.equals(val))
            return head;
        if (tail.value.equals(val))
            return tail;

        Node node = null;
        if (_ascending)
            node = tail.prev;
        if (!_ascending)
            node = head.next;

        while (node != null) {
            final int compareResult = compare(val, (T) node.value);

            if (compareResult == 0)
                return node;

            if (compareResult == 1)
                break;

            if (_ascending)
                node = node.prev;

            if (!_ascending)
                node = node.next;
        }

        return null;
    }

    public void delete(T val) {
        if (head == null)
            return;

        if (head.value.equals(val)) {
            deleteFromHead();
            return;
        }

        if (tail.value.equals(val)) {
            deleteFromTail();
            return;
        }

        Node left = head.next;
        Node right = tail.prev;

        for (int i = 0; i < size / 2; i++) {
            if (left.value.equals(val)) {
                left.prev.next = left.next;
                left.next.prev = left.prev;

                size--;
                return;
            }
            if (right.value.equals(val)) {
                right.prev.next = right.next;
                right.next.prev = right.prev;

                right.next = null;
                right.prev = null;

                size--;

                return;
            }

            left = left.next;
            right = right.prev;
        }
    }

    private void deleteFromHead() {
        if (size == 1) {
            clear(_ascending);
            return;
        }

        head = head.next;
        if (head != null)
            head.prev = null;
        size--;
    }

    private void deleteFromTail() {
        if (size == 1) {
            clear(_ascending);
            return;
        }
        tail = tail.prev;
        tail.next = null;
        size--;
    }

    public void clear(boolean asc) {
        _ascending = asc;
        head = null;
        tail = null;
        size = 0;
    }

    public int count() {
        return size;
    }

    ArrayList<Node<T>> getAll() {
        ArrayList<Node<T>> r = new ArrayList<Node<T>>();
        Node<T> node = head;
        while (node != null) {
            r.add(node);
            node = node.next;
        }
        return r;
    }
}