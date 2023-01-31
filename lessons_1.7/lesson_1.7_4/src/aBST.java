import java.util.*;

class aBST {
    public Integer Tree[];

    public aBST(int depth) {
        int tree_size = getCalculatedTreeSizeByDepth(depth);
        Tree = new Integer[tree_size];
        for (int i = 0; i < tree_size; i++) Tree[i] = null;
    }

    private int getCalculatedTreeSizeByDepth(final int depth) {
        if (depth <= 0)
            return 0;
        if (depth == 1)
            return 1;
        if (depth == 2)
            return 3;

        int treeSizeResult = 3;
        int nodesCountOnCurrentDepth = 2;

        for (int i = 3; i <= depth; i++) {
            nodesCountOnCurrentDepth = nodesCountOnCurrentDepth * 2;
            treeSizeResult += nodesCountOnCurrentDepth;
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