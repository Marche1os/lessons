import java.lang.reflect.Array;

class NativeDictionary<T> {
    public int size;
    public String[] slots;
    public T[] values;

    public NativeDictionary(int sz, Class clazz) {
        size = sz;
        slots = new String[size];
        values = (T[]) Array.newInstance(clazz, this.size);
    }

    public int hashFun(String key) {
        if (key == null)
            return 0;

        final int hashCode = Math.abs(key.hashCode());
        return (hashCode % size);
    }

    public boolean isKey(String key) {
        if (key == null)
            return false;

        final int index = hashFun(key);
        return slots[index] != null && values[index] != null;
    }

    public void put(String key, T value) {
        if (key == null || value == null)
            return;

        final int index = hashFun(key);
        slots[index] = key;
        values[index] = value;
    }

    public T get(String key) {
        if (key == null)
            return null;
        final int index = hashFun(key);
        return values[index];
    }
}