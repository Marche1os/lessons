import java.lang.reflect.Array;

public class DynArray<T> {
    private static final int MIN_CAPACITY = 16;
    public T[] array;
    public int count;
    public int capacity;
    Class clazz;

    public DynArray(Class clz) {
        clazz = clz;

        count = 0;
        makeArray(16);
    }

    public void makeArray(int new_capacity) {
        final int finalCapacity = new_capacity >= MIN_CAPACITY ? new_capacity : MIN_CAPACITY;
        final T[] newArray = (T[]) Array.newInstance(this.clazz, finalCapacity);

        if (array != null)
            System.arraycopy(array, 0, newArray, 0, count);

        array = newArray;
        capacity = finalCapacity;
    }

    public T getItem(int index) {
        throwExceptionIfWrongIndex(index);

        return array[index];
    }

    public void append(T itm) {
        if (count == capacity) {
            int newCapacity = capacity * 2;
            makeArray(newCapacity);
        }
        array[count] = itm;
        count++;
    }

    public void insert(T itm, int index) {
        if (index < 0 || index > capacity)
            throw new ArrayIndexOutOfBoundsException("index out of bounds");

        if (index >= count) {
            append(itm);
            return;
        }

        if (array[index] == null) {
            array[index] = itm;
            count++;
            return;
        }

        if (count != capacity) {
            offsetElementsFromIndex(index);
            array[index] = itm;
            count++;
            return;
        }

        makeArray(capacity * 2);
        offsetElementsFromIndex(index);
        array[index] = itm;
        count++;
    }

    private void offsetElementsFromIndex(final int index) {
        int i = index;

        while (array[i] != null && i < capacity) i++;

        while (i > index) {
            array[i] = array[i - 1];
            i--;
        }
    }

    public void remove(int index) {
        throwExceptionIfWrongIndex(index);

        array[index] = null;
        shrinkToIndex(index);
        count--;

        if (isNeedReduce()) {
            int newCapacity = (int) (capacity / 1.5);
            makeArray(newCapacity);
        }
    }

    private void shrinkToIndex(final int index) {
        int i = index;
        while (i < capacity - 1) {
            array[i] = array[i + 1];
            i++;
        }
        array[capacity - 1] = null;
    }

    private void throwExceptionIfWrongIndex(int index) {
        if (isWrongIndex(index))
            throw new ArrayIndexOutOfBoundsException("index < 0 or index > end of array.");
    }

    private boolean isWrongIndex(int index) {
        return index < 0 || index >= capacity;
    }

    private boolean isNeedReduce() {
        return (count < (capacity * 0.5));
    }
}