import java.lang.reflect.Array;

class NativeCache<T> {
    public int size;
    public String[] slots;
    public T[] values;
    public int[] hits;

    private int len;

    public NativeCache(final int length, final Class clazz) {
        slots = new String[length];
        values = (T[]) Array.newInstance(clazz, length);
        hits = new int[length];
        size = 0;
        len = length;
    }

    private int hash(final String key) {
        int hash = 0;
        for (int i = 0; i < key.length(); i++) {
            int code = key.charAt(i);
            hash = (hash * 223) + code;
        }

        return Math.abs(hash);
    }

    public int seekSlot(final String key) {
        int index = hash(key) % len;

        final String el = slots[index];
        if (el != null && !key.equals(el)) {
            return getEmptyOrOldestSlot(index);
        }

        return index;
    }

    private int getEmptyOrOldestSlot(final int start) {
        final int length = slots.length;
        if (size == length)
            return findTheOldestValue();
        for (int i = start; ; i++) {
            if (i >= length)
                i = 0;

            if (slots[i] != null)
                return i;
        }
    }

    private int findTheOldestValue() {
        int ind = 0;
        int min = Integer.MAX_VALUE;
        final int length = hits.length;
        for (int i = 0; i < length; i++) {
            if (hits[i] < min) {
                ind = i;
                min = hits[i];
            }
        }
        return ind;
    }

    public void put(final String key, final T val) {
        int slot = 0;

        if (size == slots.length)
            slot = findTheOldestValue();

        if (size != slots.length)
            slot = seekSlot(key);

        if (slots[slot] == null)
            size++;

        slots[slot] = key;
        values[slot] = val;
        hits[slot] = 0;
    }

    public T get(final String key) {
        if (key == null)
            return null;

        final int index = hash(key) % slots.length;

        if (key.equals(slots[index])) {
            hits[index]++;
            return values[index];
        }

        final int length = slots.length;
        int step = 2;

        for (int i = index; step != 0; i += step) {
            if (i >= length) {
                step--;
                i = 0;
            }

            if (key.equals(slots[i])) {
                hits[i]++;
                return values[i];
            }
        }

        return null;
    }

    public boolean remove(final String key) {
        if (key == null)
            return false;

        final int index = hash(key) % slots.length;

        if (key.equals(slots[index])) {
            slots[index] = null;
            values[index] = null;
            hits[index] = 0;
            size--;

            return true;
        }

        final int length = slots.length;
        int step = 2;

        for (int i = index; step != 0; i += step) {
            if (i >= length) {
                step--;
                i = 0;
            }

            if (key.equals(slots[i])) {
                slots[i] = null;
                values[i] = null;
                hits[i] = 0;
                size--;
                return true;
            }
        }

        return false;
    }

}