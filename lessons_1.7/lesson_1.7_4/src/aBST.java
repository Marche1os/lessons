import java.util.*;

class aBST {
    public Integer Tree[];

    public aBST(int depth) {
        int tree_size = getCalculatedTreeSizeByDepth(depth);
        Tree = new Integer[tree_size];
        for (int i = 0; i < tree_size; i++) Tree[i] = null;
    }

    private int getCalculatedTreeSizeByDepth(final int depth) {
        if (depth < 0)
            return 0;
        if (depth == 0)
            return 1;
        if (depth == 1)
            return 3;
        if (depth == 2)
            return 7;

        int treeSizeResult = 0;
        int nodeCount = 0;

        for (int i = 0; i <= depth; i++) {
            if (i == 0)
                nodeCount = 1;
            else if (i == 1)
                nodeCount = 2;
            else nodeCount = nodeCount * 2;
            treeSizeResult = treeSizeResult + nodeCount;
        }

        return treeSizeResult;
    }

    public Integer FindKeyIndex(int key) {
        for (int i = 0; i < Tree.length; ) {
            if (null == Tree[i])
                return -i;

            if (key == Tree[i])
                return i;

            if (key > Tree[i])
                i = (2 * i) + 2;
            else if (key < Tree[i])
                i = (2 * i) + 1;
        }

        return null;
    }

    public int AddKey(int key) {
        Integer indexToInsert = FindKeyIndex(key);

        if ((indexToInsert == null))
            return -1;

        if (indexToInsert == 0 && null != Tree[0])
            return 0;

        if (indexToInsert < 1) {
            indexToInsert = -indexToInsert;
            Tree[indexToInsert] = key;
            return indexToInsert;
        }

        return indexToInsert;
    }

}