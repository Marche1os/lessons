import java.util.Stack;

public abstract class BracketsChecker {

    public static boolean isBracketsBalanced(final String input) {
        if (input.isBlank()) return false;

        final Stack<Character> characterStack = new Stack<>();

        for (Character item : input.toCharArray()) {
            if (item == '(') {
                characterStack.push(item);
                continue;
            }
            if (item == ')' && characterStack.size() != 0) {
                characterStack.pop();
                continue;
            }
            if (item == ')') {
                return false;
            }
        }

        return characterStack.size() == 0;
    }
}
