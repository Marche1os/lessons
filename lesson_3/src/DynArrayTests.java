import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DynArrayTests {
    private static final int MIN_CAPACITY = 16;
    private DynArray<Integer> fullCompletedList;
    private DynArray<Integer> emptyList;

    @BeforeEach
    void generateTestData() {
        fullCompletedList = new DynArray<>(Integer.class);
        emptyList = new DynArray<>(Integer.class);

        for (int i = 0; i < MIN_CAPACITY; i++) {
            fullCompletedList.append(i);
        }
        assertEquals(MIN_CAPACITY, fullCompletedList.count);
        assertEquals(MIN_CAPACITY, fullCompletedList.capacity);
    }

    @AfterEach
    void resetTestData() {
        fullCompletedList = new DynArray<>(Integer.class);
        emptyList = new DynArray<>(Integer.class);
    }

    @Test
    @DisplayName("test Insert method when buffer size not exceeded")
    void appendInNotCompletedList() {
        assertNull(emptyList.getItem(0));
        assertNull(emptyList.getItem(1));
        assertNull(emptyList.getItem(2));
        assertEquals(MIN_CAPACITY, emptyList.capacity);
        assertEquals(0, emptyList.count);

        emptyList.append(0);
        emptyList.append(1);
        emptyList.append(2);

        assertEquals(0, emptyList.getItem(0));
        assertEquals(1, emptyList.getItem(1));
        assertEquals(2, emptyList.getItem(2));
        assertEquals(MIN_CAPACITY, emptyList.capacity);
        assertEquals(3, emptyList.count);
    }

    @Test
    @DisplayName("test append method when count = capacity")
    void appendToFillList() {
        assertNull(emptyList.getItem(0));
        assertNull(emptyList.getItem(1));
        assertNull(emptyList.getItem(2));
        assertEquals(MIN_CAPACITY, emptyList.capacity);
        assertEquals(0, emptyList.count);

        for (int i = 0; i < 16; i++) {
            emptyList.append(i);
        }
        assertEquals(emptyList.capacity, emptyList.count);
        assertEquals(MIN_CAPACITY, emptyList.count);
    }

    @Test
    @DisplayName("test Insert method when buffer size exceeded")
    void appendInCompletedList() {
        final int count = fullCompletedList.count;
        assertEquals(MIN_CAPACITY, fullCompletedList.capacity);
        assertEquals(MIN_CAPACITY, fullCompletedList.count);
        assertEquals(15, fullCompletedList.getItem(count - 1));

        fullCompletedList.append(148);

        assertEquals(MIN_CAPACITY * 2, fullCompletedList.capacity);
        assertEquals(count + 1, fullCompletedList.count);
        assertEquals(148, fullCompletedList.getItem(count));
        assertEquals(15, fullCompletedList.getItem(count - 1));
        assertEquals(14, fullCompletedList.getItem(count - 2));
    }

    @Test
    @DisplayName("test Insert in incorrect index")
    void insertInWrongPositionForEmptyList() {
        assertThrowsExactly(
                ArrayIndexOutOfBoundsException.class,
                () -> emptyList.insert(148, -1)
        );

        assertDoesNotThrow(() -> emptyList.insert(512, emptyList.capacity - 1));

        assertThrowsExactly(
                ArrayIndexOutOfBoundsException.class,
                () -> emptyList.insert(1024, emptyList.capacity + 1)
        );
    }

    @Test
    void testInsertOnIncorrectPositions() {
        final DynArray<Integer> array = new DynArray<>(Integer.class);
        for (int i = 0; i < 10; i++)
            array.insert(i, i);

        assertThrowsExactly(
                ArrayIndexOutOfBoundsException.class,
                () -> array.insert(128, -1)
        );

        assertThrowsExactly(
                ArrayIndexOutOfBoundsException.class,
                () -> array.insert(128, array.capacity + 1)
        );

        array.insert(512, array.capacity);

        assertNull(array.getItem(14));
        assertEquals(512, array.getItem(10));
        assertEquals(9, array.getItem(9));
    }

    @Test
    @DisplayName("test insert on incorrect position. And insert by index >= count")
    void testInsertWithExceededArray() {
        final DynArray<Integer> array = new DynArray<>(Integer.class);

        for (int i = 0; i < 20; i++) {
            array.insert(i, i);
        }

        assertEquals(MIN_CAPACITY * 2, array.capacity);
        assertEquals(20, array.count);

        assertThrowsExactly(
                ArrayIndexOutOfBoundsException.class,
                () -> array.insert(1000, -1)
        );

        assertThrowsExactly(
                ArrayIndexOutOfBoundsException.class,
                () -> array.insert(1000, array.capacity + 1)
        );

        array.insert(128, array.capacity);
        assertEquals(128, array.getItem(20));
        assertNull(array.getItem(array.capacity - 1));

        array.insert(512, array.capacity - 2);
        assertEquals(512, array.getItem(array.capacity - 2));

    }

    @Test
    void insertByIndex() {
        final DynArray<Integer> array = new DynArray<>(Integer.class);
        final int capacity = 2048;
        final int count = 1048;

        array.makeArray(capacity);

        assertEquals(capacity, array.capacity);
        assertEquals(0, array.count);

        for (int i = 0; i < count; i++)
            array.insert(i, i);

        assertEquals(capacity, array.capacity);
        assertEquals(count, array.count);

        array.insert(148, 1096);

        assertEquals(148, array.getItem(1096));
        assertEquals(count + 1, array.count);
        assertEquals(capacity, array.capacity);
    }

    @Test
    @DisplayName("test Insert by index when array element exists and buffer size not exceeded")
    void insertByIndexWhenValueNotNull() {
        final int capacity = emptyList.capacity;
        assertEquals(0, emptyList.count);
        emptyList.append(0);
        emptyList.append(1);
        emptyList.append(2);

        assertEquals(capacity, emptyList.capacity);
        assertEquals(3, emptyList.count);

        emptyList.insert(5, 1);


        assertEquals(4, emptyList.count);
        assertEquals(capacity, emptyList.capacity);

        assertEquals(5, emptyList.getItem(1));
        assertEquals(1, emptyList.getItem(2));
        assertEquals(2, emptyList.getItem(3));
    }

    @Test
    @DisplayName("test Insert null value")
    void insertNull() {
        emptyList.insert(128, 16);
        assertEquals(MIN_CAPACITY, emptyList.capacity);
        assertEquals(1, emptyList.count);
        assertNull(emptyList.getItem(15));
        assertEquals(128, emptyList.getItem(0));
    }

    @Test
    @DisplayName("test Insert by index when array is required be exceeded")
    void insertByIndexInCompletedList() {
        final int capacity = fullCompletedList.capacity;
        final int count = fullCompletedList.count;

        assertEquals(count, capacity);

        assertEquals(14, fullCompletedList.getItem(14));
        assertEquals(15, fullCompletedList.getItem(15));

        fullCompletedList.insert(5, 1);

        assertEquals(0, fullCompletedList.getItem(0));
        assertEquals(5, fullCompletedList.getItem(1));
        assertEquals(1, fullCompletedList.getItem(2));
        assertEquals(2, fullCompletedList.getItem(3));
        assertEquals(13, fullCompletedList.getItem(14));
        assertEquals(14, fullCompletedList.getItem(15));
        assertEquals(15, fullCompletedList.getItem(16));
        assertNull(fullCompletedList.getItem(17));
        assertEquals(capacity * 2, fullCompletedList.capacity);
        assertEquals(count + 1, fullCompletedList.count);
    }

    @Test
    @DisplayName("remove without change buffer size")
    void removeSingleElement() {
        assertEquals(16, fullCompletedList.count);
        assertEquals(15, fullCompletedList.getItem(15));
        assertEquals(MIN_CAPACITY, fullCompletedList.capacity);

        fullCompletedList.remove(0);

        assertEquals(1, fullCompletedList.getItem(0));
        assertEquals(2, fullCompletedList.getItem(1));
        assertNull(fullCompletedList.getItem(15));
        assertEquals(15, fullCompletedList.count);

        int i = 0;
        for (Integer item : fullCompletedList.array) {
            if (item != null)
                i++;
        }
        assertEquals(i, fullCompletedList.count);
        assertEquals(MIN_CAPACITY, fullCompletedList.capacity);
    }

    @Test
    @DisplayName("remove and reduce buffer size")
    void removeManyElements() {
        final DynArray<Integer> array = new DynArray<>(Integer.class);
        for (int i = 0; i < 30; i++)
            array.append(i);

        assertEquals(MIN_CAPACITY * 2, array.capacity);
        assertEquals(30, array.count);

        for (int i = 29; i > 14; i--)
            array.remove(i);

        assertEquals(21, array.capacity);
        assertEquals(15, array.count);

        for (int i = 0; i < 4; i++) {
            array.remove(i);
        }

        assertEquals(21, array.capacity);
        assertEquals(11, array.count);

        array.remove(4);

        assertEquals(MIN_CAPACITY, array.capacity);
        assertEquals(10, array.count);

    }

    @Test
    @DisplayName("remove method when index is incorrect")
    void removeInWrongPosition() {
        assertThrowsExactly(
                ArrayIndexOutOfBoundsException.class,
                () -> emptyList.remove(1)
        );

        assertThrowsExactly(
                ArrayIndexOutOfBoundsException.class,
                () -> fullCompletedList.remove(fullCompletedList.capacity)
        );

        assertThrowsExactly(
                ArrayIndexOutOfBoundsException.class,
                () -> fullCompletedList.remove(-1)
        );
    }
}
