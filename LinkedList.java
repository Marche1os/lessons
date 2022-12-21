import java.util.*;

public class LinkedList {
    public Node head;
    public Node tail;

    private int size;

    public LinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public void addInTail(Node item) {
        if (this.head == null)
            this.head = item;
        else
            this.tail.next = item;
        this.tail = item;
        size++;
    }

    public Node find(int value) {
        Node node = this.head;
        while (node != null) {
            if (node.value == value)
                return node;
            node = node.next;
        }
        return null;
    }

    public ArrayList<Node> findAll(int _value) {
        ArrayList<Node> nodes = new ArrayList<Node>();
        Node node = head;

        while (node != null) {
            if (node.value == _value)
                nodes.add(node);
            node = node.next;
        }

        return nodes;
    }

    public boolean remove(int _value) {
        if (head == null) {
            return false;
        }

        if (head == tail && head.value == _value) {
            clear();
            return true;
        }

        if (head.value == _value) {
            head = head.next;
            if (head.next == null)
                tail = head;
            size--;
            return true;
        }

        Node node = head.next;
        Node prev = head;

        while (node != null) {
            if (node == tail) {
                if (node.value == _value) {
                    prev.next = null;
                    tail = prev;
                    size--;
                    return true;
                }
            }

            if (node.value == _value) {
                prev.next = node.next;
                size--;
                return true;
            }
            prev = node;
            node = node.next;
        }

        return false;
    }

    public void removeAll(int _value) {
        while (head != null && head.value == _value) {
            if (head == tail) {
                clear();
                return;
            }
            head = head.next;
            size--;
        }

        if (head == null)
            return;

        Node temp = head.next;
        Node preview = head;

        while (temp != null) {
            if (temp == tail) {
                if (temp.value == _value) {
                    preview.next = null;
                    tail = preview;
                    size--;
                }
                break;
            }

            if (temp.value == _value) {
                preview.next = temp.next;
                temp.next = null;
                temp = preview.next;
                size--;
            } else {
                preview = preview.next;
                temp = temp.next;
            }
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
            _nodeToInsert.next = head;
            if (head == tail)
                tail = _nodeToInsert;
            head = _nodeToInsert;
            size++;
            return;
        } else if (_nodeAfter == tail) {
            tail.next = _nodeToInsert;
            tail = _nodeToInsert;
            size++;
            return;
        }

        Node node = head;
        int ind = 0;
        while (node != null) {
            ind++;
            if (node == _nodeAfter) {
                if (_nodeToInsert != null) {
                    _nodeToInsert.next = _nodeAfter.next;
                    node.next = _nodeToInsert;
                    size++;
                } else {
                    node.next = null;
                    size = ind;
                }
                break;
            }
            node = node.next;
        }
    }
}

class Node {
    public int value;
    public Node next;

    public Node(int _value) {
        value = _value;
        next = null;
    }
}