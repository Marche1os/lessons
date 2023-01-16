import java.util.List;

public class PrinterEventIndexes {

    public static void printValuesByEvenIndex(List<Integer> numbers) {
        doPrintValuesByEvenIndex(numbers, 0);
    }

    private static void doPrintValuesByEvenIndex(List<Integer> numbers, int currentPosition) {
        if (currentPosition >= numbers.size())
            return;

        System.out.println(numbers.get(currentPosition));

        doPrintValuesByEvenIndex(numbers, currentPosition + 2);
    }
}
