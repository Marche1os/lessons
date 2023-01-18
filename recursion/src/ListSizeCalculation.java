import java.util.LinkedList;
import java.util.NoSuchElementException;

public class ListSizeCalculation {

    public static int getLengthOfList(LinkedList list) {
        try {
            list.pop();
        } catch (NoSuchElementException e) {
            return 0;
        }
        return 1 + getLengthOfList(list);
    }
}
