import java.util.List;

public abstract class MaxValueFounder {

    public static <T extends Comparable<T>> T getSecondMaximum(List<T> items) {
        if (items.size() < 2) {
            return null;
        }

        T firstItem = items.get(0);
        T secondItem = items.get(1);

        T firstMax;
        if (isFirstArgumentHigherThanTwo(firstItem, secondItem)) {
            firstMax = items.get(0);
        } else {
            firstMax = items.get(1);
        }

        T secondMax;
        if (isFirstArgumentHigherThanTwo(secondItem, firstItem)) {
            secondMax = firstItem;
        } else {
            secondMax = secondItem;
        }

        List<T> newItems = items.subList(2, items.size());

        return getSecondMaximum(newItems, newItems.size(), firstMax, secondMax);
    }

    private static <T extends Comparable<T>> T getSecondMaximum(
            List<T> items,
            int lastIndex,
            T firstMax,
            T secondMax
    ) {
        if (lastIndex == 0)
            return secondMax;

        final T currentItem = items.get(lastIndex - 1);
        if (isFirstArgumentHigherThanTwo(currentItem, firstMax)) {
            secondMax = firstMax;
            firstMax = currentItem;
        } else if (isFirstArgumentHigherThanTwo(currentItem, secondMax)) {
            secondMax = currentItem;
        }

        return getSecondMaximum(items, lastIndex - 1, firstMax, secondMax);
    }

    private static <T extends Comparable<T>> boolean isFirstArgumentHigherThanTwo(T one, T two) {
        if (one == two)
            return false;

        if (two == null)
            return true;

        return one.compareTo(two) > 0;
    }
}
