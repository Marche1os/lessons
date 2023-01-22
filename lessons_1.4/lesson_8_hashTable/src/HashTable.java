public class HashTable {
    public int size;
    public int step;
    public String[] slots;

    public HashTable(int sz, int stp) {
        size = sz;
        step = stp;
        slots = new String[size];
        for (int i = 0; i < size; i++) slots[i] = null;
    }

    public int hashFun(String value) {
        if (value == null)
            return -1;

        int sumOfChars = 0;
        for (Character character : value.toCharArray())
            sumOfChars += character;
        return sumOfChars % size;
    }

    public int seekSlot(String value) {
        if (value == null)
            return -1;

        final int slot = hashFun(value);
        int step = this.step;

        for (int i = slot; step != 0; i += step) {
            if (i >= size) {
                step--;
                i = 0;
            }

            if (slots[i] == null) {
                return i;
            }
        }

        return -1;
    }

    public int put(String value) {
        if (value == null)
            return -1;

        final int slotIndex = seekSlot(value);

        if (slotIndex != -1) {
            slots[slotIndex] = value;
            return slotIndex;
        }

        return -1;
    }

    public int find(String value) {
        if (value == null)
            return -1;

        for (int i = 0; i < size; i++) {
            if (value.equals(slots[i])) {
                return i;
            }
        }

        return -1;
    }
}