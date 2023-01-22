import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NativeCacheTest {

    @Test
    void put() {
        final NativeCache<String> cache = new NativeCache<>(4, String.class);

        cache.put("Key1", "value1");

        String value = cache.get("Key1");
        cache.get("Key1");
        assertEquals("value1", value);

        cache.put("Key2", "value2");
        cache.get("Key2");

        cache.put("Key3", "value3");
        cache.get("Key3");
        cache.get("Key3");

        cache.put("Key4", "value4");

        cache.put("Key5", "value5");

        assertEquals(4, cache.size);

        assertNull(cache.get("Key4"));

        assertNotNull(cache.get("Key1"));
        assertNotNull(cache.get("Key2"));
        assertNotNull(cache.get("Key3"));
        assertNotNull(cache.get("Key5"));

        cache.put("Key6", "value");

        cache.get("Key6");
        cache.get("Key6");
        cache.get("Key6");
        cache.get("Key6");


        assertEquals(4, cache.size);
        assertNull(cache.get("Key5"));

        assertNotNull(cache.get("Key1"));
        assertNotNull(cache.get("Key6"));
        assertNotNull(cache.get("Key3"));
        assertNotNull(cache.get("Key2"));

        cache.put("Key7", "value");


        assertNull(cache.get("Key2"));

        assertNotNull(cache.get("Key1"));
        assertNotNull(cache.get("Key6"));
        assertNotNull(cache.get("Key3"));
        assertNotNull(cache.get("Key7"));
    }

    @Test
    void get() {
        final NativeCache<Integer> cache = new NativeCache<>(4, Integer.class);

        cache.put("key1", 1);
        cache.put("key2", 2);
        cache.put("key3", 3);
        cache.put("key4", 0);

        int value = cache.get("key1");
        assertEquals(1, value);

        cache.get("key2");
        value = cache.get("key2");
        assertEquals(2, value);

        cache.get("key3");

        value = cache.get("key3");
        cache.get("key3");

        assertEquals(3, value);

        for (int i = 0; i < cache.slots.length; i++) {
            if (cache.slots[i].equals("key1")) {
                assertEquals(1, cache.hits[i]);
            }

            if (cache.slots[i].equals("key2")) {
                assertEquals(2, cache.hits[i]);
            }

            if (cache.slots[i].equals("key3")) {
                assertEquals(3, cache.hits[i]);
            }

            if (cache.slots[i].equals("key4")) {
                assertEquals(0, cache.hits[i]);
            }

        }

    }

    @Test
    void remove() {
        final NativeCache<Integer> cache = new NativeCache<>(4, Integer.class);

        cache.put("key1", 0);
        assertEquals(1, cache.size);
        cache.put("key2", 1);
        assertEquals(2, cache.size);

        cache.put("key3", 2);
        assertEquals(3, cache.size);
        cache.put("key4", 3);
        assertEquals(4, cache.size);

        boolean removed = cache.remove("key4");
        assertTrue(removed);
        assertNull(cache.get("key4"));
        assertEquals(3, cache.size);

        removed = cache.remove("key4");
        assertFalse(removed);
        assertNull(cache.get("key4"));
        assertEquals(3, cache.size);
    }

}
