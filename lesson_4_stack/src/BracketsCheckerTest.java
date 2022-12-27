import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BracketsCheckerTest {

    @Test
    void isBracketsBalancedIncorrectInput() {
        assertFalse(BracketsChecker.isBracketsBalanced(""));
        assertFalse(BracketsChecker.isBracketsBalanced(")"));
        assertFalse(BracketsChecker.isBracketsBalanced("("));

    }

    @Test
    void isBracketsBalancedPositive() {
        final String input1 = "()";
        final String input2 = "(())";
        final String input3 = "((()()))";
        final String input4 = "()(()())((()()))";
        final String input5 = "()(())()(((()())))";
        final String input6 = "(2)((ab))(b)(((a*(b+c)())))*g";

        assertTrue(BracketsChecker.isBracketsBalanced(input1));
        assertTrue(BracketsChecker.isBracketsBalanced(input2));
        assertTrue(BracketsChecker.isBracketsBalanced(input3));
        assertTrue(BracketsChecker.isBracketsBalanced(input4));
        assertTrue(BracketsChecker.isBracketsBalanced(input5));
        assertTrue(BracketsChecker.isBracketsBalanced(input6));
    }

    @Test
    void isBracketsBalancedNegative() {
        final String input1 = ")(";
        final String input2 = "(()))";
        final String input3 = "((())))";
        final String input4 = "()())(";
        final String input5 = "(())()()(()";

        assertFalse(BracketsChecker.isBracketsBalanced(input1));
        assertFalse(BracketsChecker.isBracketsBalanced(input2));
        assertFalse(BracketsChecker.isBracketsBalanced(input3));
        assertFalse(BracketsChecker.isBracketsBalanced(input4));
        assertFalse(BracketsChecker.isBracketsBalanced(input5));
    }
}