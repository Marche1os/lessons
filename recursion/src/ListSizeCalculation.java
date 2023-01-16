import java.util.LinkedList;

public class ListSizeCalculation {

    public static int getLengthOfList(LinkedList list) {
        if (list.size() == 0)
            return 0;

        return calculateLengthOfList(list, list.size());
    }

    private static int calculateLengthOfList(LinkedList list, int listSize) {
        if (listSize == 1)
            return 1;
        else
            return  1 + calculateLengthOfList(list, listSize - 1);
    }
}
