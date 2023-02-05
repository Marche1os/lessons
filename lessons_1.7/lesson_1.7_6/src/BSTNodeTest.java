import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BSTNodeTest {

    @Test
    void generateBSTTest() {
        BalancedBST bst = new BalancedBST();
        final int[] array = {0, 1, 2, 3, 4, 5, 6, 8, 9, 10, 11, 12, 13, 14};
        bst.GenerateTree(array);

        BSTNode node = bst.Root.LeftChild;
        while (node != null) {
            assertTrue(node.NodeKey < node.Parent.NodeKey);
            node = node.LeftChild;
        }

        node = bst.Root.RightChild;
        while (node != null) {
            assertTrue(node.NodeKey >= node.Parent.NodeKey);
            node = node.RightChild;
        }
    }

    @Test
    void testBalanced() {
        BalancedBST bst = new BalancedBST();
        int[] array = {0, 1, 2, 3, 4, 5, 6, 8, 9, 10, 11, 12, 13, 14};
        bst.GenerateTree(array);

        assertTrue(bst.IsBalanced(bst.Root));
        bst.Root.RightChild = null;
        assertFalse(bst.IsBalanced(bst.Root));
    }

    @Test
    void generateOnCornerCases() {
        BalancedBST bst = new BalancedBST();
        bst.GenerateTree(null);
        assertNull(bst.Root);

        int[] array = {};
        bst.GenerateTree(array);
        assertNull(bst.Root);

        array = new int[]{0};
        bst.GenerateTree(array);

        assertNotNull(bst.Root);
        assertEquals(0, bst.Root.NodeKey);
        assertNull(bst.Root.Parent);
        assertEquals(0, bst.Root.Level);
    }

}
