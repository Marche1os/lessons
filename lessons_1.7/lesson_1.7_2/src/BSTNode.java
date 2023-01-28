import java.util.*;

class BSTNode<T> {
    public int NodeKey;
    public T NodeValue;
    public BSTNode<T> Parent;
    public BSTNode<T> LeftChild;
    public BSTNode<T> RightChild;

    public BSTNode(int key, T val, BSTNode<T> parent) {
        NodeKey = key;
        NodeValue = val;
        Parent = parent;
        LeftChild = null;
        RightChild = null;
    }
}

class BSTFind<T> {
    public BSTNode<T> Node;

    public boolean NodeHasKey;

    public boolean ToLeft;

    public BSTFind() {
        Node = null;
    }
}

class BST<T> {
    BSTNode<T> Root;

    private int count;

    public BST(BSTNode<T> node) {
        Root = node;
        count = node != null ? 1 : 0;
    }

    public BSTFind<T> FindNodeByKey(int key) {
        BSTFind<T> found = new BSTFind<>();

        if (Root == null) {
            found.Node = null;
            found.NodeHasKey = false;
            found.ToLeft = true;

            return found;
        }

        final BSTNode<T> nodeTriedToFind = findNodeByKeyFromNode(Root, key);

        found.Node = nodeTriedToFind;
        found.NodeHasKey = key == nodeTriedToFind.NodeKey;
        found.ToLeft = key < nodeTriedToFind.NodeKey;

        return found;
    }

    public boolean AddKeyValue(int key, T val) {
        if (Root == null) {
            Root = new BSTNode<>(key, val, null);
            count++;
            return true;
        }

        final BSTNode<T> node = findNodeByKeyFromNode(Root, key);

        if (key == node.NodeKey) {
            return false;
        }

        if (key > node.NodeKey) {
            node.RightChild = new BSTNode<>(key, val, node);
        } else {
            node.LeftChild = new BSTNode<>(key, val, node);
        }

        count++;
        return true;
    }

    public BSTNode<T> FinMinMax(BSTNode<T> FromNode, boolean FindMax) {
        if (null == Root || null == FromNode)
            return null;

        BSTNode<T> founded;
        if (FindMax)
            founded = findMax(FromNode);
        else
            founded = findMin(FromNode);

        if (FindMax && FromNode == Root && Root.NodeKey > founded.NodeKey) {
            founded = Root;
            return founded;
        }

        if (!FindMax && FromNode == Root && Root.NodeKey < founded.NodeKey) {
            founded = Root;
            return founded;
        }

        return founded;
    }

    private BSTNode<T> findMin(final BSTNode<T> fromNode) {
        if (null == fromNode.LeftChild)
            return fromNode;
        return findMin(fromNode.LeftChild);
    }

    private BSTNode<T> findMax(final BSTNode<T> fromNode) {
        if (null == fromNode.RightChild)
            return fromNode;
        return findMax(fromNode.RightChild);
    }

    public ArrayList<BSTNode> WideAllNodes() {
        if (Root == null) 
            return new ArrayList<>(0);

        final LinkedList<BSTNode> queueOfNodes = new LinkedList<>();
        final LinkedList<BSTNode> result = new LinkedList<>();

        queueOfNodes.push(Root);

        BSTNode<T> nodeVisitor;

        while (!queueOfNodes.isEmpty()) {
            nodeVisitor = queueOfNodes.poll();
            result.addLast(nodeVisitor);

            if (nodeVisitor.LeftChild != null)
                queueOfNodes.addLast(nodeVisitor.LeftChild);

            if (nodeVisitor.RightChild != null)
                queueOfNodes.addLast(nodeVisitor.RightChild);

        }

        return new ArrayList<>(result);
    }

    public boolean DeleteNodeByKey(int key) {
        if (Root == null)
            return false;

        final BSTNode<T> nodeToDelete = findNodeByKeyFromNode(Root, key);

        if (key != nodeToDelete.NodeKey) {
            return false;
        }

        if (Root == nodeToDelete) {
            Root = null;
            count = 0;
            return true;
        }

        if (nodeToDelete.RightChild == null) {
            swapParent(nodeToDelete, null);
            nodeToDelete.Parent = null;
            count--;
            return true;
        }

        BSTNode<T> descendantNode = findMin(nodeToDelete.RightChild);

        if (descendantNode.LeftChild == null && descendantNode.RightChild == null) {
            swapLinksForLeaf(nodeToDelete, descendantNode);
        } else {
            swapLinksForNode(nodeToDelete, descendantNode);
        }

        count--;
        return true;
    }

    private void swapParent(BSTNode<T> nodeToDelete, BSTNode<T> descendantNode) {
        boolean nodeToDeleteIsLeftToParent = (nodeToDelete.Parent.LeftChild == nodeToDelete);
        if (nodeToDeleteIsLeftToParent) {
            nodeToDelete.Parent.LeftChild = descendantNode;
        } else {
            nodeToDelete.Parent.RightChild = descendantNode;
        }
    }

    private void swapLinksForLeaf(BSTNode<T> nodeToDelete, BSTNode<T> descendantNode) {
        swapParent(nodeToDelete, descendantNode);
        if (nodeToDelete.LeftChild != null) {
            nodeToDelete.LeftChild.Parent = descendantNode;
            descendantNode.LeftChild = nodeToDelete.LeftChild;
        }

        descendantNode.Parent.Parent = descendantNode;
        descendantNode.Parent.LeftChild = null;
        descendantNode.Parent = nodeToDelete.Parent;
        descendantNode.RightChild = nodeToDelete.RightChild;

        nodeToDelete.LeftChild = null;
        nodeToDelete.Parent = null;
        nodeToDelete.RightChild = null;
    }

    private void swapLinksForNode(BSTNode<T> nodeToDelete, BSTNode<T> descendantNode) {
        descendantNode.Parent = nodeToDelete.Parent;
        swapParent(nodeToDelete, descendantNode);

        if (nodeToDelete.LeftChild != null) {
            descendantNode.LeftChild = nodeToDelete.LeftChild;
            nodeToDelete.LeftChild.Parent = descendantNode;
        }

        nodeToDelete.LeftChild = null;
        nodeToDelete.Parent = null;
        nodeToDelete.RightChild = null;
    }

    public int Count() {
        return count;
    }

    private BSTNode<T> findNodeByKeyFromNode(final BSTNode<T> fromNode, final int key) {
        if (key == fromNode.NodeKey)
            return fromNode;

        BSTNode<T> nextNode;

        if (key >= fromNode.NodeKey && fromNode.RightChild != null) {
            nextNode = fromNode.RightChild;
            return findNodeByKeyFromNode(nextNode, key);
        }

        if (key >= fromNode.NodeKey)
            return fromNode;

        if (fromNode.LeftChild != null) {
            nextNode = fromNode.LeftChild;
            return findNodeByKeyFromNode(nextNode, key);
        }

        return fromNode;
    }

}