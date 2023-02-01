import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ArrayBinaryTreeTest {

    @Test
    void testTreeSize() {
        aBST bst = new aBST(2);
        assertEquals(7, bst.Tree.length);

        bst = new aBST(3);
        assertEquals(15, bst.Tree.length);

        bst = new aBST(4);
        assertEquals(31, bst.Tree.length);

        bst = new aBST(5);
        assertEquals(63, bst.Tree.length);
    }

    @Test
    void findKeyIndex() {
        final aBST bst = new aBST(4);
        bst.Tree[0] = 50;
        bst.Tree[1] = 25;
        bst.Tree[2] = 75;
        bst.Tree[3] = null;
        bst.Tree[4] = 37;
        bst.Tree[5] = 62;
        bst.Tree[6] = 84;
        bst.Tree[7] = null;
        bst.Tree[8] = null;
        bst.Tree[9] = 31;
        bst.Tree[10] = 43;
        bst.Tree[11] = 55;
        bst.Tree[12] = null;
        bst.Tree[13] = null;
        bst.Tree[14] = 92;

        Integer index = bst.FindKeyIndex(92);
        assertEquals(14, index);

        index = bst.FindKeyIndex(50);
        assertEquals(0, index);

        index = bst.FindKeyIndex(124);
        assertNull(index);
    }

    @Test
    void findKeyIndexInEmpty() {
        aBST bst = new aBST(-1);
        Integer index = bst.FindKeyIndex(24);
        assertNull(index);

        bst = new aBST(2);
        int additionIndex = bst.AddKey(100);

        assertEquals(0, additionIndex);
        assertEquals(100, bst.Tree[0]);

        index = bst.FindKeyIndex(100);
        assertEquals(0, index);

        index = bst.FindKeyIndex(140);
        assertEquals(-2, index);
    }

    @Test
    void findKeyIndexInCornerCases() {
        final aBST bst = new aBST(1);
        bst.Tree[0] = 100;

        int index = bst.FindKeyIndex(100);
        assertEquals(0, index);
    }

    @Test
    void addKey() {
        final aBST bst = new aBST(4);

        int index = bst.AddKey(50);
        assertEquals(0, index);
        assertEquals(50, bst.Tree[0]);

        index = bst.AddKey(25);
        assertEquals(1, index);
        assertEquals(25, bst.Tree[1]);

        index = bst.AddKey(75);

        assertEquals(2, index);
        assertEquals(75, bst.Tree[2]);
    }

    @Test
    void addKeyNull() {
        final aBST bst = new aBST(2);

        int index = bst.AddKey(50);
        assertEquals(0, index);
        assertEquals(50, bst.Tree[0]);

        index = bst.AddKey(24);
        assertEquals(1, index);

        index = bst.AddKey(50);
        assertEquals(0, index);
        assertEquals(50, bst.Tree[0]);
    }
}
