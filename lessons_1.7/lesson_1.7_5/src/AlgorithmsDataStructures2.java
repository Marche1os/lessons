import java.util.*;

public class AlgorithmsDataStructures2 {
    private static int[] result;
    private static int count = 0;

    public static int[] GenerateBBSTArray(int[] a) {
        Arrays.sort(a);
        result = new int[a.length];
        count = 0;
        generate(a, 0, a.length - 1);

        return result;
    }

    private static void generate(int[] array, int start, int end) {
        if (start > end)
            return;
        int middle = (start + end) / 2;
        int root = array[middle];

        result[count++] = root;
        generate(array, start, middle - 1);
        generate(array, middle + 1, end);

    }
}