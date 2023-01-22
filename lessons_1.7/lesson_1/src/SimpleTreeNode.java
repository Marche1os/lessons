import java.util.*;

public class SimpleTreeNode<T> {
    public T NodeValue;
    public SimpleTreeNode<T> Parent;
    public List<SimpleTreeNode<T>> Children;

    public SimpleTreeNode(T val, SimpleTreeNode<T> parent) {
        NodeValue = val;
        Parent = parent;
        Children = null;
    }
}

class SimpleTree<T> {
    public SimpleTreeNode<T> Root;

    private int nodesCount;

    private int leavesCount;
    private int modificationCount;
    private int nodesCountModificationLevel;
    private int leavesCountModificationLevel;

    public SimpleTree(SimpleTreeNode<T> root) {
        Root = root;
        if (root != null && (root.Children == null || root.Children.isEmpty())) {
            leavesCount = 1;
            nodesCount = 1;
        }
        modificationCount = 0;
        nodesCountModificationLevel = 0;
        leavesCountModificationLevel = 0;
    }

    public void AddChild(SimpleTreeNode<T> ParentNode, SimpleTreeNode<T> NewChild) {
        if (ParentNode == null)
            return;

        if (Root == null) {
            Root = ParentNode;
            Root.Parent = null;
        }

        if (ParentNode.Children == null) {
            ParentNode.Children = new ArrayList<>();
        }

        NewChild.Parent = ParentNode;
        ParentNode.Children.add(NewChild);

        if (Root.Children.isEmpty()) {
            leavesCount = 1;
            nodesCount = 1;
        }
        modificationCount++;
    }

    public void DeleteNode(SimpleTreeNode<T> NodeToDelete) {
        if (NodeToDelete == null || NodeToDelete == Root)
            return;

        final SimpleTreeNode<T> parentNode = NodeToDelete.Parent;
        parentNode.Children.removeIf(node -> NodeToDelete.equals(node));

        modificationCount++;
    }

    public List<SimpleTreeNode<T>> GetAllNodes() {
        if (Root == null)
            return Collections.emptyList();

        List<SimpleTreeNode<T>> founded = new ArrayList<>();
        founded.add(Root);
        founded.addAll(getAllRootChildren(Root));

        return founded;
    }

    private List<SimpleTreeNode<T>> getAllRootChildren(SimpleTreeNode<T> child) {
        if (child.Children == null || child.Children.isEmpty())
            return Collections.emptyList();

        final List<SimpleTreeNode<T>> founded = new ArrayList<>();

        for (SimpleTreeNode<T> node : child.Children) {
            founded.add(node);
            founded.addAll(getAllRootChildren(node));
        }

        return founded;
    }

    public List<SimpleTreeNode<T>> FindNodesByValue(T val) {
        if (Root == null)
            return Collections.emptyList();

        final List<SimpleTreeNode<T>> founded = new ArrayList<>();

        if (val.equals(Root))
            founded.add(Root);

        founded.addAll(getAllRootChildrenByValue(Root, val));

        return founded;
    }

    private List<SimpleTreeNode<T>> getAllRootChildrenByValue(SimpleTreeNode<T> child, T val) {
        if (child.Children == null || child.Children.isEmpty())
            return Collections.emptyList();

        final List<SimpleTreeNode<T>> founded = new ArrayList<>();

        for (SimpleTreeNode<T> node : child.Children) {
            if (val.equals(node.NodeValue)) {
                founded.add(node);
            }
            founded.addAll(getAllRootChildrenByValue(node, val));
        }

        return founded;
    }

    public void MoveNode(SimpleTreeNode<T> OriginalNode, SimpleTreeNode<T> NewParent) {
        DeleteNode(OriginalNode);
        AddChild(NewParent, OriginalNode);
    }

    public int Count() {
        if (nodesCountModificationLevel == modificationCount)
            return nodesCount;

        if (Root == null) {
            nodesCount = 0;
            return nodesCount;
        }
        if (Root.Children == null || Root.Children.isEmpty()) {
            nodesCount = 1;
            return nodesCount;
        }

        int newNodesCount = 1;
        newNodesCount += getNewNodesCount(Root);
        nodesCount = newNodesCount;

        nodesCountModificationLevel = modificationCount;
        return nodesCount;
    }

    private int getNewNodesCount(SimpleTreeNode<T> child) {
        if (child.Children == null || child.Children.isEmpty())
            return 0;

        int newNodesCount = 0;

        for (SimpleTreeNode<T> node : child.Children) {
            newNodesCount++;
            newNodesCount += getNewNodesCount(node);
        }

        return newNodesCount;
    }

    public int LeafCount() {
        if (leavesCountModificationLevel == modificationCount) {
            return leavesCount;
        }
        if (Root == null) {
            leavesCount = 0;
            return leavesCount;
        }
        if (Root.Children == null || Root.Children.isEmpty()) {
            leavesCount = 1;
            return leavesCount;
        }

        leavesCount = getNewLeavesCount(Root);
        leavesCountModificationLevel = modificationCount;
        return leavesCount;
    }

    private int getNewLeavesCount(SimpleTreeNode<T> child) {
        if (child.Children == null || child.Children.isEmpty())
            return 0;

        int newLeavesCount = 0;

        for (SimpleTreeNode<T> node : child.Children) {
            if (node.Children == null || node.Children.isEmpty())
                newLeavesCount++;
            newLeavesCount += getNewLeavesCount(node);
        }

        return newLeavesCount;
    }
}