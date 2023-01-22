import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SimpleTreeTest {
    private SimpleTree<Integer> emptyTree;

    @BeforeEach
    void generateTestTrees() {
        emptyTree = new SimpleTree<>(null);
    }

    @Test
    void addNewChild() {
        assertNull(emptyTree.Root);

        SimpleTreeNode<Integer> rootNode = new SimpleTreeNode<>(100, null);
        emptyTree.AddChild(rootNode.Parent, rootNode);

        SimpleTreeNode<Integer> newChild = new SimpleTreeNode<>(51, emptyTree.Root);
        emptyTree.AddChild(newChild.Parent, newChild);

        assertNotNull(emptyTree.Root);
        assertNull(emptyTree.Root.Parent);
        assertEquals(100, emptyTree.Root.NodeValue);

        SimpleTreeNode<Integer> newChild2 = new SimpleTreeNode<>(256, emptyTree.Root);
        emptyTree.AddChild(emptyTree.Root, newChild2);

        assertEquals(2, emptyTree.Root.Children.size());
        assertEquals(51, emptyTree.Root.Children.get(0).NodeValue);
        assertEquals(256, emptyTree.Root.Children.get(1).NodeValue);

        assertNull(emptyTree.Root.Parent);
    }

    @Test
    @DisplayName("deleting non-root node.")
    void deleteNode() {
        SimpleTreeNode<Integer> rootNode = new SimpleTreeNode<>(100, null);
        emptyTree.AddChild(rootNode.Parent, rootNode);

        SimpleTreeNode<Integer> newChild = new SimpleTreeNode<>(51, rootNode);
        SimpleTreeNode<Integer> newChild2 = new SimpleTreeNode<>(256, emptyTree.Root);
        SimpleTreeNode<Integer> newChild3 = new SimpleTreeNode<>(512, newChild2);

        emptyTree.AddChild(newChild.Parent, newChild);
        emptyTree.AddChild(newChild2.Parent, newChild2);
        emptyTree.AddChild(newChild3.Parent, newChild3);

        assertNotNull(emptyTree.Root.Children);
        assertEquals(512, emptyTree.Root.Children.get(1).Children.get(0).NodeValue);

        emptyTree.DeleteNode(newChild2);

        assertSame(emptyTree.Root, newChild2.Parent);
        assertEquals(1, emptyTree.Root.Children.size());
        assertNotEquals(512, emptyTree.Root.Children.get(0).NodeValue);

        emptyTree.DeleteNode(null);
        assertNotNull(emptyTree.Root);
        emptyTree.DeleteNode(emptyTree.Root);
        assertNotNull(emptyTree.Root);
    }

    @Test
    void deleteNodeFromOneElementTree() {
        final SimpleTreeNode<Integer> root = new SimpleTreeNode<>(100, null);
        emptyTree.DeleteNode(root);

        emptyTree.AddChild(root.Parent, root);
        assertEquals(100, emptyTree.Root.NodeValue);
        assertNull(emptyTree.Root.Children);

        emptyTree.DeleteNode(root);
        assertNotNull(emptyTree.Root);

        final SimpleTreeNode<Integer> child = new SimpleTreeNode<>(128, emptyTree.Root);
        emptyTree.AddChild(child.Parent, child);

        assertEquals(128, emptyTree.Root.Children.get(0).NodeValue);

        emptyTree.DeleteNode(child);

        assertTrue(emptyTree.Root.Children.isEmpty());
    }

    @Test
    @DisplayName("pass sequentially all tree to form all children")
    void getAllChildren() {
        assertTrue(emptyTree.GetAllNodes().isEmpty());

        SimpleTreeNode<Integer> rootNode = new SimpleTreeNode<>(100, null);
        emptyTree.AddChild(rootNode.Parent, rootNode);

        SimpleTreeNode<Integer> newChild = new SimpleTreeNode<>(51, rootNode);
        SimpleTreeNode<Integer> newChild2 = new SimpleTreeNode<>(256, emptyTree.Root);
        SimpleTreeNode<Integer> newChild3 = new SimpleTreeNode<>(512, newChild2);

        emptyTree.AddChild(rootNode, newChild);
        emptyTree.AddChild(emptyTree.Root, newChild2);
        emptyTree.AddChild(newChild3.Parent, newChild3);

        List<SimpleTreeNode<Integer>> actualNodes = emptyTree.GetAllNodes();
        List<Integer> expectedValues = new ArrayList<>();

        expectedValues.add(100);
        expectedValues.add(51);
        expectedValues.add(256);
        expectedValues.add(512);

        boolean isMatchedActualAndExpectedValues = actualNodes.stream().mapToInt(value -> value.NodeValue)
                .allMatch(value -> expectedValues.contains(value));

        assertTrue(isMatchedActualAndExpectedValues);
        assertEquals(expectedValues.size(), actualNodes.size());
    }

    @Test
    void getAllChildrenOnOneElement() {
        assertTrue(emptyTree.FindNodesByValue(100).isEmpty());

        final SimpleTreeNode<Integer> root = new SimpleTreeNode<>(100, null);
        emptyTree.AddChild(root.Parent, root);

        List<SimpleTreeNode<Integer>> founded = emptyTree.GetAllNodes();
        assertEquals(100, founded.get(0).NodeValue);
        assertEquals(1, founded.size());

        emptyTree.AddChild(emptyTree.Root, new SimpleTreeNode<>(128, emptyTree.Root));
        founded = emptyTree.GetAllNodes();
        assertEquals(2, founded.size());
    }

    @Test
    void getAllChildrenOnEmptyList() {
        SimpleTree<Integer> simpleTree = new SimpleTree<>(null);
        assertTrue(simpleTree.GetAllNodes().isEmpty());
    }

    @Test
    void findAllChildrenByValue() {
        final SimpleTreeNode<Integer> root = new SimpleTreeNode<>(100, null);
        emptyTree.AddChild(root.Parent, root);

        SimpleTreeNode<Integer> newChild = new SimpleTreeNode<>(51, root);
        SimpleTreeNode<Integer> newChild2 = new SimpleTreeNode<>(256, root);
        SimpleTreeNode<Integer> newChild3 = new SimpleTreeNode<>(512, newChild2);
        SimpleTreeNode<Integer> newChild4 = new SimpleTreeNode<>(1024, newChild2);
        SimpleTreeNode<Integer> newChild5 = new SimpleTreeNode<>(2048, newChild4);
        SimpleTreeNode<Integer> newChild6 = new SimpleTreeNode<>(2048, newChild3);

        emptyTree.AddChild(root, newChild);
        emptyTree.AddChild(root, newChild2);
        emptyTree.AddChild(newChild3.Parent, newChild3);
        emptyTree.AddChild(newChild4.Parent, newChild4);
        emptyTree.AddChild(newChild5.Parent, newChild5);
        emptyTree.AddChild(newChild6.Parent, newChild6);

        List<SimpleTreeNode<Integer>> founded = emptyTree.FindNodesByValue(1024);
        assertEquals(1, founded.size());
        assertEquals(1024, founded.get(0).NodeValue);

        founded = emptyTree.FindNodesByValue(2048);
        assertEquals(2, founded.size());
        assertEquals(2048, founded.get(0).NodeValue);
        assertEquals(2048, founded.get(1).NodeValue);

    }

    @Test
    void findAllByValueCornerCases() {
        List<SimpleTreeNode<Integer>> founded;
        founded = emptyTree.FindNodesByValue(100);

        assertTrue(founded.isEmpty());

        final SimpleTreeNode<Integer> root = new SimpleTreeNode<>(100, null);
        emptyTree.AddChild(root.Parent, root);

        founded = emptyTree.FindNodesByValue(100);
        assertEquals(1, founded.size());
        assertEquals(100, founded.get(0).NodeValue);

        final SimpleTreeNode<Integer> child1 = new SimpleTreeNode<>(128, emptyTree.Root);
        emptyTree.AddChild(child1.Parent, child1);

        founded = emptyTree.FindNodesByValue(128);
        assertEquals(1, founded.size());
        assertEquals(128, founded.get(0).NodeValue);

        final SimpleTreeNode<Integer> child2 = new SimpleTreeNode<>(128, child1);
        emptyTree.AddChild(child2.Parent, child2);

        final SimpleTreeNode<Integer> child3 = new SimpleTreeNode<>(128, child1);
        emptyTree.AddChild(child3.Parent, child3);

        founded = emptyTree.FindNodesByValue(128);
        assertEquals(3, founded.size());

        for (SimpleTreeNode<Integer> node : founded) {
            assertEquals(128, node.NodeValue);
        }

    }

    @Test
    @DisplayName("move the node with all children to another place of tree")
    void moveNodesTo() {
        final SimpleTreeNode<Integer> root = new SimpleTreeNode<>(100, null);
        emptyTree.AddChild(root.Parent, root);

        final SimpleTreeNode<Integer> firstChildOfRoot = new SimpleTreeNode<>(51, root);
        final SimpleTreeNode<Integer> secondChildOfRoot = new SimpleTreeNode<>(256, root);
        final SimpleTreeNode<Integer> firstChildOfSecond = new SimpleTreeNode<>(512, secondChildOfRoot);
        final SimpleTreeNode<Integer> firstChildOfFirst = new SimpleTreeNode<>(1024, firstChildOfSecond);

        emptyTree.AddChild(firstChildOfRoot.Parent, firstChildOfRoot);
        emptyTree.AddChild(secondChildOfRoot.Parent, secondChildOfRoot);
        emptyTree.AddChild(firstChildOfSecond.Parent, firstChildOfSecond);
        emptyTree.AddChild(firstChildOfFirst.Parent, firstChildOfFirst);

        assertNull(firstChildOfRoot.Children);
        assertEquals(512, secondChildOfRoot.Children.get(0).NodeValue);

        emptyTree.MoveNode(firstChildOfSecond, firstChildOfRoot);
        assertTrue(secondChildOfRoot.Children.isEmpty());

        assertEquals(512, firstChildOfRoot.Children.get(0).NodeValue);
        assertTrue(secondChildOfRoot.Children.isEmpty());

        assertEquals(1024, emptyTree.Root.Children.get(0).Children.get(0).Children.get(0).NodeValue);

    }

    @Test
    @DisplayName("count all leaf in the tree")
    void countAllLeaf() {
        assertEquals(0, emptyTree.LeafCount());
        final SimpleTreeNode<Integer> root = new SimpleTreeNode<>(100, null);
        final SimpleTree<Integer> tree = new SimpleTree<>(root);

        assertEquals(1, tree.LeafCount());

        final SimpleTreeNode<Integer> firstRootChild = new SimpleTreeNode<>(128, tree.Root);
        tree.AddChild(firstRootChild.Parent, firstRootChild);
        assertEquals(1, tree.LeafCount());

        final SimpleTreeNode<Integer> secondRootChild = new SimpleTreeNode<>(256, tree.Root);
        tree.AddChild(secondRootChild.Parent, secondRootChild);
        assertEquals(2, tree.LeafCount());

        final SimpleTreeNode<Integer> childOfFirst = new SimpleTreeNode<>(512, firstRootChild);
        tree.AddChild(childOfFirst.Parent, childOfFirst);
        assertEquals(2, tree.LeafCount());

        final SimpleTreeNode<Integer> secondChildOfFirst = new SimpleTreeNode<>(1024, firstRootChild);
        tree.AddChild(secondChildOfFirst.Parent, secondChildOfFirst);
        assertEquals(3, tree.LeafCount());

        final SimpleTreeNode<Integer> firstChildOfSecond = new SimpleTreeNode<>(64, secondRootChild);
        tree.AddChild(firstChildOfSecond.Parent, firstChildOfSecond);
        assertEquals(3, tree.LeafCount());

        tree.DeleteNode(firstRootChild);
        assertEquals(1, tree.LeafCount());
    }

    @Test
    @DisplayName("count all nodes in the tree")
    void countAllNodes() {
        assertEquals(0, emptyTree.Count());

        final SimpleTreeNode<Integer> root = new SimpleTreeNode<>(100, null);
        final SimpleTree<Integer> tree = new SimpleTree<>(root);
        assertEquals(1, tree.Count());
        assertEquals(100, tree.Root.NodeValue);

        final SimpleTreeNode<Integer> firstChildOfRoot = new SimpleTreeNode<>(51, root);
        final SimpleTreeNode<Integer> secondChildOfRoot = new SimpleTreeNode<>(256, root);
        final SimpleTreeNode<Integer> firstChildOfSecond = new SimpleTreeNode<>(512, secondChildOfRoot);
        final SimpleTreeNode<Integer> firstChildOfFirst = new SimpleTreeNode<>(1024, firstChildOfSecond);

        tree.AddChild(firstChildOfRoot.Parent, firstChildOfRoot);
        tree.AddChild(secondChildOfRoot.Parent, secondChildOfRoot);
        tree.AddChild(firstChildOfSecond.Parent, firstChildOfSecond);
        tree.AddChild(firstChildOfFirst.Parent, firstChildOfFirst);

        assertEquals(5, tree.Count());

        tree.MoveNode(firstChildOfSecond, firstChildOfRoot);
        assertEquals(5, tree.Count());

        tree.DeleteNode(firstChildOfSecond);

        assertEquals(3, tree.Count());

        tree.DeleteNode(secondChildOfRoot);
        assertEquals(2, tree.Count());
    }

    @Test
    void testDepth() {
        final SimpleTreeNode<Integer> root = new SimpleTreeNode<>(100, null);
        final SimpleTree<Integer> tree = new SimpleTree<>(root);

        assertEquals(1, tree.Count());
        assertEquals(0, tree.Root.depth);

        final SimpleTreeNode<Integer> firstChildRoot = new SimpleTreeNode<>(16, tree.Root);
        final SimpleTreeNode<Integer> secondChildRoot = new SimpleTreeNode<>(24, tree.Root);

        tree.AddChild(firstChildRoot.Parent, firstChildRoot);
        tree.AddChild(secondChildRoot.Parent, secondChildRoot);

        assertEquals(1, firstChildRoot.depth);
        assertEquals(1, secondChildRoot.depth);

        final SimpleTreeNode<Integer> firstChildOfFirstChild = new SimpleTreeNode<>(32, firstChildRoot);
        tree.AddChild(firstChildOfFirstChild.Parent, firstChildOfFirstChild);

        assertEquals(2, firstChildOfFirstChild.depth);

        tree.MoveNode(firstChildRoot, secondChildRoot);

        assertEquals(24, tree.Root.Children.get(0).NodeValue);
        assertEquals(16, tree.Root.Children.get(0).Children.get(0).NodeValue);

        assertEquals(1, secondChildRoot.depth);
        assertEquals(2, firstChildRoot.depth);
        assertEquals(3, firstChildOfFirstChild.depth);

    }
}
