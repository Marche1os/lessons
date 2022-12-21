import java.util.*;

public class LinkedList2 {
    public Node head;
    public Node tail;

    private int size;

    public LinkedList2() {
        head = null;
        tail = null;
        size = 0;
    }

    public void addInTail(Node _item) {
        if (head == null) {
            this.head = _item;
            this.head.next = null;
            this.head.prev = null;
        } else {
            this.tail.next = _item;
            _item.prev = tail;
        }
        this.tail = _item;
        size++;
    }

    public Node find(int _value) {
        if (head == null || tail == null)
            return null;

        if (head.value == _value)
            return head;
        else if (tail.value == _value)
            return tail;

        Node left = head.next;
        Node right = tail.prev;
        while (left != right) {
            if (left.value == _value)
                return left;
            else if (right.value == _value)
                return right;

            left = left.next;
            right = right.prev;
        }

        return null;
    }

    public ArrayList<Node> findAll(int _value) {
        ArrayList<Node> nodes = new ArrayList<Node>();

        if (head == null || tail == null)
            return nodes;

        if (head == tail && _value == head.value) {
            nodes.add(head);
            return nodes;
        } else if (head == tail)
            return nodes;

        Node left = head;
        Node right = tail;

        while (left != right) {
            if (_value == left.value)
                nodes.add(left);
            if (_value == right.value)
                nodes.add(right);

            left = left.next;
            if (left == right) break;
            right = right.prev;
        }

        return nodes;
    }

    public boolean remove(int _value) {
        if (head == null || tail == null) {
            return false;
        }

        if (head == tail) {
            if (_value == head.value) {
                clear();
                return true;
            }
            return false;
        }

        Node left = head;

        while (left != null) {
            if (_value == left.value) {
                if (left.prev != null) {
                    left.prev.next = left.next;
                } else {
                    head = left.next;
                    if (head != null)
                        head.prev = null;
                }
                if (left.next != null) {
                    left.next.prev = left.prev;
                } else {
                    tail = left.prev;
                }

                size--;
                return true;
            }

            left = left.next;
        }
        return false;
    }

    public void removeAll(int _value) {
        if (head == null || tail == null) {
            return;
        }

        if (head == tail) {
            if (_value == head.value)
                clear();
            return;
        }

        Node left = head;

        while (left != null) {
            if (_value == left.value) {
                if (left.prev != null) {
                    left.prev.next = left.next;
                } else {
                    head = left.next;
                    if (head != null) {
                        head.prev = null;
                    }
                }
                if (left.next != null) {
                    left.next.prev = left.prev;
                } else {
                    tail = left.prev;
                }

                size--;
            }

            left = left.next;
        }
    }

    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    public int count() {
        return size;
    }

    public void insertAfter(Node _nodeAfter, Node _nodeToInsert) {
        if (_nodeAfter == null && _nodeToInsert == null) {
            clear();
            return;
        }

        if (_nodeAfter == null) {
            if (head != null) {
                final Node temp = head.next;
                head = _nodeToInsert;
                head.next = temp;
                size++;
            } else {
                addInTail(_nodeToInsert);
            }
            return;
        }

        if (_nodeAfter == tail && _nodeToInsert != null) {
            addInTail(_nodeToInsert);
            return;
        }


        final Node next = _nodeAfter.next;
        if (next != null) {
            _nodeAfter.next.prev = _nodeToInsert;
            _nodeAfter.next = _nodeToInsert;

            if (_nodeToInsert != null) {
                _nodeToInsert.prev = _nodeAfter;
                _nodeToInsert.next = next;
                size++;
            } else {
                tail = _nodeAfter;
                tail.next = null;

                int newSize = 0;
                Node node = head;

                while (node != null) {
                    newSize++;
                    node = node.next;
                }

                size = newSize;
            }
        }
    }

    public void insertAsFirst(Node _nodeToInsert) {
        insertAfter(null, _nodeToInsert);
    }
}

class Node {
    public int value;
    public Node next;
    public Node prev;

    public Node(int _value) {
        value = _value;
        next = null;
        prev = null;
    }
}