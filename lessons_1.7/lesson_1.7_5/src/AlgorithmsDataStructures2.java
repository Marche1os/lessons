import java.util.*;
import java.util.function.ToIntFunction;

public class AlgorithmsDataStructures2 {

    public static int[] GenerateBBSTArray(int[] a) {
        if (a.length == 0)
            return new int[0];

        Arrays.sort(a);

        ArrayList<Integer> tree = generate(new ArrayList<>(), a, 0, a.length - 1);

        return generateBst(tree);
    }

    private static ArrayList<Integer> generate(
            final ArrayList<Integer> tree,
            final int[] sourceArray,
            int start,
            int end
    ) {
        if (start > end)
            return null;
        int middle = (start + end) / 2;
        int root = sourceArray[middle];

        tree.add(root);
        generate(tree, sourceArray, start, middle - 1);
        generate(tree, sourceArray, middle + 1, end);

        return tree;
    }

    private static int[] generateBst(
            final ArrayList<Integer> sourceTree
    ) {
        Integer[] arrayToComplete = new Integer[sourceTree.size()];
        arrayToComplete[0] = sourceTree.get(0);
        for (int i = 1; i < sourceTree.size(); i++) {
            int index = 0;
            int node = sourceTree.get(i);

            while (true) {
                int leftParentIndex = 2 * index + 1;
                int rightParentIndex = 2 * index + 2;
                int current = arrayToComplete[index];

                if (leftParentIndex >= arrayToComplete.length || rightParentIndex >= arrayToComplete.length)
                    return convertPrimitiveToObjectInt(arrayToComplete);

                if (arrayToComplete[leftParentIndex] != null && current > node)
                    index = leftParentIndex;

                if (arrayToComplete[leftParentIndex] == null && current > node) {
                    index = leftParentIndex;
                    arrayToComplete[index] = node;
                    break;
                }

                if (arrayToComplete[rightParentIndex] != null && current < node)
                    index = rightParentIndex;

                if (arrayToComplete[rightParentIndex] == null && current < node) {
                    index = rightParentIndex;
                    arrayToComplete[index] = node;
                    break;
                }
            }
        }

        return convertPrimitiveToObjectInt(arrayToComplete);
    }

    private static int[] convertPrimitiveToObjectInt(Integer[] array) {
        int[] result = new int[array.length];

        for (int i = 0; i < array.length; i++)
            result[i] = array[i];

        return result;
    }
}