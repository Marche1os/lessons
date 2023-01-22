import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PowerSetTest {
    private long startTime;
    private long endTime;

    @BeforeEach
    void start() {
        startTime = System.currentTimeMillis();
    }

    @AfterEach
    void finish() {
        endTime = System.currentTimeMillis();

        long workTime = (endTime - startTime);
        assertTrue(workTime <= 2_000);
    }

    @Test
    void put() {
        final PowerSet set = new PowerSet();
        final String value = "Watch";

        assertEquals(0, set.size());

        set.put(value);

        assertEquals(1, set.size());
        assertTrue(set.get(value));

        set.put(value);

        assertEquals(1, set.size());
    }

    @Test
    void remove() {
        final PowerSet set = new PowerSet();
        final String value = "Watch";

        set.put(value);

        assertTrue(set.get(value));

        assertTrue(set.remove(value));

        assertFalse(set.get(value));
        assertFalse(set.remove(value));
        assertEquals(0, set.size());
    }

    @Test
    void intersection() {
        final PowerSet set1 = new PowerSet();
        final PowerSet set2 = new PowerSet();
        final String value1 = "Car";
        final String value2 = "Watch";

        PowerSet intersection;

        set1.put(value1);
        set1.put(value2);

        intersection = set1.intersection(set2);

        assertEquals(0, intersection.size());
        assertFalse(intersection.get(value1));
        assertFalse(intersection.get(value2));

        set2.put(value1);

        intersection = set1.intersection(set2);

        assertEquals(1, intersection.size());
        assertTrue(intersection.get(value1));
        assertFalse(intersection.get(value2));

        set2.put(value2);

        intersection = set1.intersection(set2);
        assertEquals(2, intersection.size());
        assertTrue(intersection.get(value1));
        assertTrue(intersection.get(value2));
    }

    @Test
    void union() {
        final PowerSet set1 = new PowerSet();
        final PowerSet set2 = new PowerSet();
        final String value1 = "Car";
        final String value2 = "Watch";
        final String value3 = "Bike";
        final String value4 = "Chopper";

        PowerSet union = set1.union(set2);
        assertEquals(0, union.size());

        set1.put(value1);
        set1.put(value2);

        union = set1.union(set2);

        assertEquals(2, union.size());
        assertTrue(union.get(value1));
        assertTrue(union.get(value2));
        assertFalse(union.get(value3));

        set2.put(value3);
        set2.put(value4);

        union = set2.union(set1);

        assertEquals(4, union.size());
        assertTrue(union.get(value1));
        assertTrue(union.get(value2));
        assertTrue(union.get(value3));
        assertTrue(union.get(value4));
    }

    @Test
    void difference() {
        final PowerSet set1 = new PowerSet();
        final PowerSet set2 = new PowerSet();
        final String value1 = "Car";
        final String value2 = "Watch";
        final String value3 = "Bike";
        final String value4 = "Chopper";

        PowerSet diff;

        diff = set1.difference(set2);
        assertEquals(0, diff.size());

        set1.put(value1);
        set2.put(value1);

        set1.put(value2);
        set2.put(value2);

        set1.put(value3);
        set1.put(value4);

        diff = set1.difference(set2);

        assertEquals(2, diff.size());

        assertTrue(diff.get(value3));
        assertTrue(diff.get(value4));

        assertFalse(diff.get(value1));
        assertFalse(diff.get(value2));
    }

    @Test
    @DisplayName("all param elements included in the current set")
    void isSubsetCase1() {
        final PowerSet set1 = new PowerSet();
        final PowerSet set2 = new PowerSet();
        final String value1 = "Car";
        final String value2 = "Watch";
        final String value3 = "Bike";
        final String value4 = "Chopper";

        set1.put(value1);
        set1.put(value2);
        set1.put(value3);
        set1.put(value4);

        set2.put(value1);
        set2.put(value2);

        assertTrue(set1.isSubset(set2));
    }

    @Test
    @DisplayName("all elements of current set included in the param set")
    void isSubsetCase2() {
        final PowerSet set1 = new PowerSet();
        final PowerSet set2 = new PowerSet();
        final String value1 = "Car";
        final String value2 = "Watch";
        final String value3 = "Bike";
        final String value4 = "Chopper";

        set1.put(value1);
        set1.put(value2);

        set2.put(value1);
        set2.put(value2);
        set2.put(value3);
        set2.put(value4);

        assertTrue(set2.isSubset(set1));
    }

    @Test
    @DisplayName("not all elements of the param included in the current set")
    void isSubsetCase3() {
        final PowerSet set1 = new PowerSet();
        final PowerSet set2 = new PowerSet();
        final String value1 = "Car";
        final String value2 = "Watch";
        final String value3 = "Bike";
        final String value4 = "Chopper";

        set1.put(value1);
        set1.put(value2);

        set2.put(value1);
        set2.put(value2);
        set2.put(value3);
        set2.put(value4);

        assertFalse(set1.isSubset(set2));
    }

}
