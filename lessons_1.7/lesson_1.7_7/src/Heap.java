import java.util.*;

class Heap {
    public int[] HeapArray;
    private int lastExistedIndex;

    public Heap() {
        HeapArray = null;
    }

    public void MakeHeap(int[] a, int depth) {
        if (null == a)
            return;

        int heapSize = (int) (Math.pow(2, depth + 1) - 1);

        HeapArray = new int[heapSize];

        System.arraycopy(a, 0, HeapArray, 0, a.length);

        lastExistedIndex = a.length - 1;
    }

    public int GetMax() {
        if (HeapArray == null || lastExistedIndex < 0)
            return -1;

        int root = HeapArray[0];
        rebuildOnDelete();

        return root;
    }

    public boolean Add(int key) {
        if (HeapArray == null)
            return false;
        if (lastExistedIndex + 1 >= HeapArray.length)
            return false;

        HeapArray[++lastExistedIndex] = key;
        rebuildOnAdd();

        return true;
    }

    private void rebuildOnAdd() {
        int last = HeapArray[lastExistedIndex];

        for (int i = lastExistedIndex; i > 0; i--) {
            if (HeapArray[i - 1] < last) {
                int temp = HeapArray[i - 1];
                HeapArray[i - 1] = last;
                HeapArray[i] = temp;
            }
        }
    }

    private void rebuildOnDelete() {
        if (lastExistedIndex >= HeapArray.length)
            return;

        int last = HeapArray[lastExistedIndex];
        HeapArray[lastExistedIndex] = 0;
        int index = 0;
        HeapArray[index] = last;

        for (int i = 0; i < lastExistedIndex; i++) {
            if (HeapArray[i + 1] > last) {
                int temp = HeapArray[i + 1];
                HeapArray[i + 1] = last;
                HeapArray[index] = temp;
                index = i + 1;
            }
        }
        lastExistedIndex--;
    }
}