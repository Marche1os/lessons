import java.util.*;

public class SimpleTreeNode<T> {
    public T NodeValue; // значение в узле
    public SimpleTreeNode<T> Parent; // родитель или null для корня
    public List<SimpleTreeNode<T>> Children; // список дочерних узлов или null

    public SimpleTreeNode(T val, SimpleTreeNode<T> parent) {
        NodeValue = val;
        Parent = parent;
        Children = null;
    }
}

class SimpleTree<T> {
    public SimpleTreeNode<T> Root; // корень, может быть null

    private int countOfNodes;

    private int countOfLeaves;

    public SimpleTree(SimpleTreeNode<T> root) {
        Root = root;
        if (root != null && (root.Children == null || root.Children.isEmpty())) {
            countOfLeaves = 1;
            countOfNodes = 1;
        }
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
        countOfNodes++;

        if (Root.Children.isEmpty()) {
            countOfLeaves = 1;
        } else
            countOfLeaves = getNewCountOfLeaves(Root);
    }

    public void DeleteNode(SimpleTreeNode<T> NodeToDelete) {
        if (NodeToDelete == null || NodeToDelete.Parent == null)
            return;

        final SimpleTreeNode<T> parentNode = NodeToDelete.Parent;
        parentNode.Children.removeIf(node -> NodeToDelete.equals(node));

        countOfNodes--;
        countOfLeaves = getNewCountOfLeaves(Root);

        // ваш код удаления существующего узла NodeToDelete
    }

    public List<SimpleTreeNode<T>> GetAllNodes() {
        if (Root == null)
            return Collections.emptyList();

        List<SimpleTreeNode<T>> founded = new ArrayList<>();
        founded.add(Root);
        founded.addAll(getAllRootChildren(Root));

        // ваш код выдачи всех узлов дерева в определённом порядке
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

        // ваш код поиска узлов по значению
        return founded;
    }

    private List<SimpleTreeNode<T>> getAllRootChildrenByValue(SimpleTreeNode<T> child, T val) {
        if (child.Children == null || child.Children.isEmpty())
            return Collections.emptyList();

        List<SimpleTreeNode<T>> founded = new ArrayList<>();

        for (SimpleTreeNode<T> node : child.Children) {
            if (val.equals(node.NodeValue)) {
                founded.add(node);
            }
            founded.addAll(getAllRootChildrenByValue(node, val));
        }

        return founded;
    }

    public void MoveNode(SimpleTreeNode<T> OriginalNode, SimpleTreeNode<T> NewParent) {
        // ваш код перемещения узла вместе с его поддеревом --
        // в качестве дочернего для узла NewParent
        DeleteNode(OriginalNode);
        AddChild(NewParent, OriginalNode);
    }

    public int Count() {
        // количество всех узлов в дереве
        // сделать оптимизацию. Подсчитывать количество листьев непосредственно при вызове этой функции.
        // при этом завести подсчет модицификаций (добавление/удаление узлов)
        // и завести признак, при какой модификации была вызвана эта функция.
        // Если значения совпадают - вернуть текущее countOfNodes, иначе - пересчитать
        return countOfNodes;
    }

    private void updateCountOfNodes() {
        // пройти по дереву и посчитать количество узлов (вызывать после добавления/удаления)
    }

    public int LeafCount() {
        // количество листьев в дереве

        // сделать оптимизацию. Подсчитывать количество листьев непосредственно при вызове этой функции.
        // при этом завести подсчет модицификаций (добавление/удаление узлов)
        // и завести признак, при какой модификации была вызвана эта функция.
        // Если значения совпадают - вернуть текущее countOfLeaves, иначе - пересчитать
        return countOfLeaves;
    }

    private int getNewCountOfLeaves(SimpleTreeNode<T> child) {
        if (child.Children == null || child.Children.isEmpty())
            return 0;

        int sum = 0;

        for (SimpleTreeNode<T> node : child.Children) {
            if (node.Children == null || node.Children.isEmpty()) {
                sum++;
            }

            sum += getNewCountOfLeaves(node);
        }

        return sum;
        // пройти по дереву и посчитать количество листьев (вызывать после добавления/удаления)
    }
}