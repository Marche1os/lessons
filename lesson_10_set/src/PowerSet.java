import java.util.*;

public class PowerSet {
    HashMap<String, Object> map;
    private final Object EMPTY_VALUE = new Object();

    public PowerSet() {
        map = new HashMap<>(20_000, 1.0f);
    }

    public int size() {
        return map.size();
    }

    public void put(String value) {
        if (map.containsKey(value) || size() == 20_000)
            return;
        map.put(value, EMPTY_VALUE);
    }

    public boolean get(String value) {
        return map.containsKey(value);
    }

    public boolean remove(String value) {
        return map.remove(value, EMPTY_VALUE);
    }

    public PowerSet intersection(PowerSet set2) {
        final PowerSet result = new PowerSet();

        for (String str : map.keySet()) {
            if (set2.get(str))
                result.put(str);
        }

        return result;
    }

    public PowerSet union(PowerSet set2) {
        PowerSet result = new PowerSet();
        Set<String> keySet2 = new HashSet<>(set2.map.keySet());
        keySet2.addAll(map.keySet());
        keySet2.forEach(item -> result.put(item));
        return result;
    }

    public PowerSet difference(PowerSet set2) {
        PowerSet result = new PowerSet();

        map.keySet().forEach(key -> {
            if (!set2.get(key)) {
                result.put(key);
            }
        });

        return result;
    }

    public boolean isSubset(PowerSet set2) {
        for (String item : set2.map.keySet()) {
            if (!get(item)) {
                return false;
            }
        }

        return true;
    }
}