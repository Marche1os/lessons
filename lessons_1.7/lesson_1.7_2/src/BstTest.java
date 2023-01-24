import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BstTest {
    private BST<Integer> emptyBinaryTree;
    private BST<Integer> completedTree;

    private static final int VALUE_1 = 148;

    @BeforeEach
    void generateTestData() {
        emptyBinaryTree = new BST<>(null);

        final BSTNode<Integer> rootForCompleted = new BSTNode<>(0, VALUE_1, null);
        completedTree = new BST<>(rootForCompleted);

        for (int i = 1; i < 15; i++) {
            completedTree.AddKeyValue(i, i + 100);
        }
    }

    @Test
    void findNodeByKey() {
        final BSTFind<Integer> founded = completedTree.FindNodeByKey(5);

        assertNotNull(founded);
        assertEquals(5, founded.Node.NodeKey);
        assertEquals(105, founded.Node.NodeValue);
        assertTrue(founded.NodeHasKey);
        assertFalse(founded.ToLeft);
    }

    @Test
    void findNodeByKeyInEmptyTree() {
        final int nonExistedKey = -1;

        final BSTFind<Integer> founded = emptyBinaryTree.FindNodeByKey(nonExistedKey);

        assertNotNull(founded);
        assertTrue(founded.ToLeft);
        assertFalse(founded.NodeHasKey);

    }

    @Test
    void findLeftNodeByNonExistedKey() {
        final BSTFind<Integer> founded = completedTree.FindNodeByKey(-100);

        assertNotNull(founded);
        assertNotEquals(-100, founded.Node.NodeKey);
        assertFalse(founded.NodeHasKey);
        assertTrue(founded.ToLeft);
    }

    @Test
    void findRightNodeByNonExistedKey() {
        final BSTFind<Integer> founded = completedTree.FindNodeByKey(100);

        assertNotNull(founded);
        assertNotEquals(100, founded.Node.NodeKey);
        assertFalse(founded.NodeHasKey);
        assertFalse(founded.ToLeft);
    }

    @Test
    void addNewNode() {
        assertNull(emptyBinaryTree.Root);
        assertEquals(0, emptyBinaryTree.Count());

        emptyBinaryTree.AddKeyValue(1, 1);
        emptyBinaryTree.AddKeyValue(2, 2);
        emptyBinaryTree.AddKeyValue(0, 0);
        emptyBinaryTree.AddKeyValue(3, 3);

        assertEquals(4, emptyBinaryTree.Count());

        assertEquals(0, emptyBinaryTree.Root.LeftChild.NodeKey);
        assertEquals(1, emptyBinaryTree.Root.NodeKey);
        assertEquals(2, emptyBinaryTree.Root.RightChild.NodeKey);
        assertEquals(3, emptyBinaryTree.Root.RightChild.RightChild.NodeKey);
        assertNull(emptyBinaryTree.Root.RightChild.LeftChild);

        emptyBinaryTree.AddKeyValue(-1, -1);
        assertEquals(-1, emptyBinaryTree.Root.LeftChild.LeftChild.NodeKey);

        assertEquals(5, emptyBinaryTree.Count());
    }

    @Test
    void searchMaxFromRoot() {
        assertNull(emptyBinaryTree.FinMinMax(emptyBinaryTree.Root, true));
        assertNull(emptyBinaryTree.FinMinMax(emptyBinaryTree.Root, false));

        BSTNode<Integer> founded = completedTree.FinMinMax(completedTree.Root, true);

        assertNotNull(founded);
        assertEquals(114, founded.NodeValue);
        assertEquals(14, founded.NodeKey);
    }

    @Test
    void searchMaxFromNode() {
        final BSTNode<Integer> root = new BSTNode<>(0, 0, null);
        emptyBinaryTree = new BST<>(root);

        emptyBinaryTree.AddKeyValue(10, 10);
        emptyBinaryTree.AddKeyValue(6, 10);
        emptyBinaryTree.AddKeyValue(14, 10);
        emptyBinaryTree.AddKeyValue(7, 10);
        emptyBinaryTree.AddKeyValue(8, 10);

        BSTNode<Integer> fromNode = emptyBinaryTree.Root.RightChild.LeftChild;
        BSTNode<Integer> founded = emptyBinaryTree.FinMinMax(fromNode, true);

        assertEquals(8, founded.NodeKey);
    }

    @Test
    void searchMinFromRoot() {
        assertNull(emptyBinaryTree.FinMinMax(emptyBinaryTree.Root, false));

        BSTNode<Integer> foundedMin = completedTree.FinMinMax(completedTree.Root, false);
        assertEquals(0, foundedMin.NodeKey);

        emptyBinaryTree.AddKeyValue(-1, -1);
        emptyBinaryTree.AddKeyValue(-4, -1);
        emptyBinaryTree.AddKeyValue(-5, -1);
        emptyBinaryTree.AddKeyValue(-10, -1);
        emptyBinaryTree.AddKeyValue(-6, -1);
        emptyBinaryTree.AddKeyValue(-8, -1);
        emptyBinaryTree.AddKeyValue(-20, -1);
        emptyBinaryTree.AddKeyValue(-30, -1);

        foundedMin = emptyBinaryTree.FinMinMax(emptyBinaryTree.Root, false);
        assertEquals(-30, foundedMin.NodeKey);
    }

    @Test
    void searchMinFromNode() {
        emptyBinaryTree.AddKeyValue(-1, -1);
        emptyBinaryTree.AddKeyValue(-5, -1);
        emptyBinaryTree.AddKeyValue(-4, -1);
        emptyBinaryTree.AddKeyValue(-10, -1);
        emptyBinaryTree.AddKeyValue(-6, -1);
        emptyBinaryTree.AddKeyValue(-8, -1);
        emptyBinaryTree.AddKeyValue(-7, -1);
        emptyBinaryTree.AddKeyValue(-20, -1);
        emptyBinaryTree.AddKeyValue(-30, -1);

        BSTNode<Integer> fromNode = emptyBinaryTree.Root.LeftChild.RightChild;
        BSTNode<Integer> foundedMin = emptyBinaryTree.FinMinMax(fromNode, false);
        assertEquals(-4, foundedMin.NodeKey);

        fromNode = emptyBinaryTree.Root.LeftChild.LeftChild.RightChild;
        foundedMin = emptyBinaryTree.FinMinMax(fromNode, false);
        assertEquals(-8, foundedMin.NodeKey);
    }

    @Test
    void deleteWithLeafReplacing() {
        final BSTNode<Integer> root = new BSTNode<>(0, 0, null);
        emptyBinaryTree = new BST<>(root);

        emptyBinaryTree.AddKeyValue(5, 6);
        emptyBinaryTree.AddKeyValue(4, 6);
        emptyBinaryTree.AddKeyValue(7, 6);
        emptyBinaryTree.AddKeyValue(6, 6);
        emptyBinaryTree.AddKeyValue(8, 6);

        assertEquals(6, emptyBinaryTree.Count());

        assertEquals(5, emptyBinaryTree.Root.RightChild.NodeKey);

        assertTrue(emptyBinaryTree.DeleteNodeByKey(5));

        assertEquals(6, emptyBinaryTree.Root.RightChild.NodeKey);

        assertSame(emptyBinaryTree.Root, emptyBinaryTree.Root.RightChild.Parent);
        assertSame(emptyBinaryTree.Root.RightChild.LeftChild.Parent, emptyBinaryTree.Root.RightChild);

        assertSame(emptyBinaryTree.Root.RightChild, emptyBinaryTree.Root.RightChild.RightChild.Parent);

        assertEquals(7, emptyBinaryTree.Root.RightChild.RightChild.NodeKey);
        assertEquals(6, emptyBinaryTree.Root.RightChild.RightChild.Parent.NodeKey);
        assertNull(emptyBinaryTree.Root.RightChild.RightChild.LeftChild);
        assertEquals(8, emptyBinaryTree.Root.RightChild.RightChild.RightChild.NodeKey);

        assertEquals(5, emptyBinaryTree.Count());
    }

    @Test
    void deleteWithNodeReplacing() {
        assertFalse(emptyBinaryTree.DeleteNodeByKey(4));

        emptyBinaryTree.AddKeyValue(1, 6);
        emptyBinaryTree.AddKeyValue(5, 6);
        emptyBinaryTree.AddKeyValue(3, 6);
        emptyBinaryTree.AddKeyValue(6, 6);
        emptyBinaryTree.AddKeyValue(8, 6);

        assertEquals(5, emptyBinaryTree.Count());

        assertEquals(5, emptyBinaryTree.Root.RightChild.NodeKey);
        assertEquals(6, emptyBinaryTree.Root.RightChild.RightChild.NodeKey);

        assertFalse(emptyBinaryTree.DeleteNodeByKey(512));

        assertTrue(emptyBinaryTree.DeleteNodeByKey(5));

        assertEquals(6, emptyBinaryTree.Root.RightChild.NodeKey);
        assertSame(emptyBinaryTree.Root, emptyBinaryTree.Root.RightChild.Parent);

        assertEquals(4, emptyBinaryTree.Count());
    }
}
