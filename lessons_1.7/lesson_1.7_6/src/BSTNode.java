import java.util.*;

class BSTNode {
    public int NodeKey;
    public BSTNode Parent;
    public BSTNode LeftChild;
    public BSTNode RightChild;
    public int Level = -1;

    public BSTNode(int key, BSTNode parent) {
        NodeKey = key;
        Parent = parent;
        LeftChild = null;
        RightChild = null;
    }
}

class BalancedBST {
    public BSTNode Root;

    public BalancedBST() {
        Root = null;
    }

    public void GenerateTree(int[] a) {
        if (null == a || a.length == 0)
            return;

        Arrays.sort(a);
        Root = generate(a, 0, a.length - 1, Root, 0);
    }

    private BSTNode generate(
            int[] array,
            int start,
            int end,
            BSTNode parent,
            int depth
    ) {
        if (start > end)
            return null;

        final int middle = (start + end) / 2;
        final int key = array[middle];
        final BSTNode node = new BSTNode(key, parent);
        node.Level = depth;

        node.LeftChild = generate(array, start, middle - 1, node, depth + 1);
        node.RightChild = generate(array, middle + 1, end, node, depth + 1);

        return node;
    }

    public boolean IsBalanced(BSTNode root_node) {
        if (null == root_node)
            return false;

        return depthOfSubtree(root_node) != -1;
    }

    private int depthOfSubtree(final BSTNode node) {
        if (null == node)
            return 0;

        int left = depthOfSubtree(node.LeftChild);
        int right = depthOfSubtree(node.RightChild);

        if (Math.abs(left - right) <= 1)
            return 1 + Math.max(left, right);
        else
            return -1;

    }

}  