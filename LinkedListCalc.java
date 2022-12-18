import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class LinkedListCalc {

    List<Integer> sumElementsBySameIndex(final LinkedList<Integer> _list1, final LinkedList<Integer> _list2) {
        if (_list1.size() != _list2.size()) {
            return Collections.emptyList();
        }
        final List<Integer> result = new ArrayList<>(_list1.size());

        for (int i = 0; i < _list1.size(); i++) {
            int el1 = _list1.get(i);
            int el2 = _list2.get(i);
            result.add(el1 + el2);
        }
        return result;
    }
}
