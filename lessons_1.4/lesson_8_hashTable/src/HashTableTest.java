import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HashTableTest {
    private HashTable hashTable;
    private static final int SIZE = 19;
    private static final int STEP = 3;

    @BeforeEach
    void generateTestData() {
        hashTable = new HashTable(SIZE, STEP);
    }

    @AfterEach
    void resetTestData() {
        hashTable = new HashTable(SIZE, STEP);
    }

    @Test
    void hashFun() {
        final char[] chars = new char[]{'B', 'A', 'O', 'B', 'A', 'B',};
        final String value = "BAOBAB";

        int sumOfChars = 0;
        for (Character character : chars)
            sumOfChars += character;

        final int expectedHash = (sumOfChars) % (SIZE);

        final int actualHash = hashTable.hashFun(value);

        assertEquals(expectedHash, actualHash);
    }

    @Test
    void seekSlot() {
        final String input = "Rolex";

        int seekSlot = hashTable.seekSlot(input);

        assertNotEquals(-1, seekSlot);

        int index1 = hashTable.put(input);

        assertEquals(seekSlot, index1);

        int index2 = hashTable.put(input);

        assertEquals(index1 + STEP, index2);

        int index3 = hashTable.put(input);

        assertEquals(index1 + (STEP << 1), index3);
    }

    @Test
    void seekSlotFailure() {
        final String input = "Rolex";
        for (int i = 0; i < SIZE; i++) {
            assertNotEquals(-1, hashTable.put(input));
        }

        assertEquals(-1, hashTable.put(input));
    }

    @Test
    void find() {
        final String input = "Rolex";
        assertEquals(-1, hashTable.find(input));

        int slot = hashTable.put(input);

        assertNotEquals(-1, slot);

        assertEquals(slot, hashTable.find(input));
    }

}
