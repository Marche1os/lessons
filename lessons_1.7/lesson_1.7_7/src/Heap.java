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

        for (int item : a)
            Add(item);

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
        int index = lastExistedIndex;
        int parent = (index - 1) / 2;

        while (index > 0 && parent >= 0) {
            if (HeapArray[index] > HeapArray[parent]) {
                int temp = HeapArray[index];
                HeapArray[index] = HeapArray[parent];
                HeapArray[parent] = temp;
            }
            index = parent;
            parent = (index - 1) / 2;
        }

    }

    private void rebuildOnDelete() {
        int last = HeapArray[lastExistedIndex];
        HeapArray[lastExistedIndex] = 0;
        int index = 0;
        HeapArray[index] = last;

        while (true) {
            int largest = index;
            int leftParent = index * 2 + 1;
            int rightParent = index * 2 + 2;

            if (leftParent < HeapArray.length && HeapArray[largest] < HeapArray[leftParent])
                largest = leftParent;

            if (rightParent < HeapArray.length && HeapArray[largest] < HeapArray[rightParent])
                largest = rightParent;

            if (index == largest)
                break;

            int temp = HeapArray[index];
            HeapArray[0] = HeapArray[index];
            HeapArray[largest] = temp;

            index = largest;
        }

        lastExistedIndex--;
    }
}