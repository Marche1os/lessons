import java.util.List;

public class PrinterEvenValues {

    public static void printAllEvenValues(List<Integer> numbers) {
        if (numbers.isEmpty()) {
            return;
        }

        final Integer lastNumber = numbers.get(numbers.size() - 1);
        if (lastNumber % 2 == 0) {
            System.out.println(lastNumber);
        }

        printAllEvenValues(numbers.subList(0, numbers.size() - 1));
    }
}
