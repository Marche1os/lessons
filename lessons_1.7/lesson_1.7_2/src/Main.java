import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        final BSTNode<Integer> root = new BSTNode<>(52, 100, null);
        BST<Integer> bst = new BST<>(root);

        bst.AddKeyValue(15, 50);
        bst.AddKeyValue(56, 50);
        bst.AddKeyValue(9, 50);
        bst.AddKeyValue(16, 50);
        bst.AddKeyValue(54, 50);
        bst.AddKeyValue(3, 50);
        bst.AddKeyValue(10, 50);
        bst.AddKeyValue(61, 50);




        final ArrayList<BSTNode> result = bst.DeepAllNodes(BST.POST_ORDER);

        for (BSTNode node : result) {
            System.out.println(node.NodeKey);
        }

    }
}