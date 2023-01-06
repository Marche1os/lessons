import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NativeDictionaryTest {
    private NativeDictionary<String> dictionary;

    private static final int SIZE = 127;

    @BeforeEach
    void generateTestData() {
        dictionary = new NativeDictionary<>(SIZE, String.class);
    }

    @Test
    void isKey() {
        assertFalse(dictionary.isKey("Watch"));

        dictionary.put("Watch", "Rolex");

        assertTrue(dictionary.isKey("Watch"));
        assertFalse(dictionary.isKey("watch"));
        assertFalse(dictionary.isKey("Rolex"));
    }

    @Test
    void put() {
        dictionary.put("Watch", "Apple");
        dictionary.put("Car", "Ford");
        dictionary.put("Contacts", "Mark");

        final String watch = dictionary.get("Watch");
        final String car = dictionary.get("Car");
        final String contacts = dictionary.get("Contacts");

        assertEquals("Apple", watch);
        assertEquals("Ford", car);
        assertEquals("Mark", contacts);

        dictionary.put("Watch", "Rolex");
        dictionary.put("Car", "Tesla");

        final String watchUpd = dictionary.get("Watch");
        final String carUpd = dictionary.get("Car");

        assertEquals("Rolex", watchUpd);
        assertEquals("Tesla", carUpd);
    }

    @Test
    void putBad() {
        dictionary.put("", "Car");

        assertTrue(dictionary.isKey(""));
        final String value = dictionary.get("");
        assertEquals("Car", value);

        dictionary.put("", "watch");
        String newValue = dictionary.get("");
        assertEquals("watch", newValue);

        dictionary.put(null, "Hey");
        assertNull(dictionary.get(null));

        dictionary.put("Phone", null);
        assertNull(dictionary.get("Phone"));

    }

    @Test
    void get() {
        final String empty = dictionary.get("");
        final String empty2 = dictionary.get("Watch");

        assertNull(empty);
        assertNull(empty2);

        dictionary.put("Watch", "Apple");

        final String watch = dictionary.get("Watch");
        assertEquals("Apple", watch);
    }

    @Test
    void getNull() {
        final String value = dictionary.get(null);
        assertNull(value);
    }

}
