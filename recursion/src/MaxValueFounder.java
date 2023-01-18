import java.util.List;

public abstract class MaxValueFounder {

    public static <T extends Comparable<T>> T getSecondMaximum(List<T> items) {
        if (items.isEmpty()) {
            return null;
        }

        return getSecondMaximum(items, items.size(), null, null);
    }

    private static <T extends Comparable<T>> T getSecondMaximum(
            List<T> items,
            int lastIndex,
            T firstMax,
            T secondMax
    ) {
        if (lastIndex == 0) {
            return secondMax;
        } else {
            final T cur = items.get(lastIndex - 1);
            if (isHigher(cur, firstMax)) {
                secondMax = firstMax;
                firstMax = cur;
            } else if (isHigher(cur, secondMax)) {
                secondMax = cur;
            }
            return getSecondMaximum(items, lastIndex - 1, firstMax, secondMax);
        }
    }

    private static <T extends Comparable<T>> boolean isHigher(T one, T two) {
        if (one == two)
            return false;

        if (two == null)
            return true;

        return one.compareTo(two) > 0;
    }
}
