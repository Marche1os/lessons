import java.util.List;

public class MaxValueFounder {

    public static int findSecondMaximumNumber(List<Integer> numbers) {
        return doSeekSecondMaximumNumber(numbers, numbers.size(), Integer.MIN_VALUE, Integer.MIN_VALUE);
    }

    private static int doSeekSecondMaximumNumber(List<Integer> numbers, int lastIndex, int firstMax, int secondMax) {
        if (lastIndex == 0)
            return secondMax;
        else {
            final int currentNumber = numbers.get(lastIndex - 1);
            if (currentNumber > firstMax) {
                secondMax = firstMax;
                firstMax = currentNumber;
            } else if (currentNumber > secondMax) {
                secondMax = currentNumber;
            }

            return doSeekSecondMaximumNumber(numbers, lastIndex - 1, firstMax, secondMax);
        }

    }
}
