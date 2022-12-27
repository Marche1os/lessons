import org.junit.jupiter.api.Test;

import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalculatorPostfixExpressionTest {

    @Test
    void testIncorrectExpressions() {
        final Stack<String> stack = new Stack<>();
        stack.push("2");
        stack.push("5");
        stack.push("=");

        assertThrows(
                IllegalArgumentException.class,
                () -> CalculatorPostfixExpression.calcPostfixExpression(stack)
        );

        final Stack<String> stack1 = new Stack<>();
        stack1.push("=");

        assertThrows(
                IllegalArgumentException.class,
                () -> CalculatorPostfixExpression.calcPostfixExpression(stack1)
        );

        stack1.push("+");
        assertThrows(
                IllegalArgumentException.class,
                () -> CalculatorPostfixExpression.calcPostfixExpression(stack1)
        );
    }

    @Test
    void testSumExpression() {
        final Stack<String> stack = new Stack<>();
        stack.push("=");
        stack.push("+");
        stack.push("2");
        stack.push("1");

        int result = CalculatorPostfixExpression.calcPostfixExpression(stack);
        assertEquals(3, result);
    }

    @Test
    void testSumWithNegativeExpression() {
        final Stack<String> stack = new Stack<>();
        stack.push("=");
        stack.push("+");
        stack.push("2");
        stack.push("-1");

        int result = CalculatorPostfixExpression.calcPostfixExpression(stack);
        assertEquals(1, result);
    }

    @Test
    void testSumWithTwoNegativeExpression() {
        final Stack<String> stack = new Stack<>();
        stack.push("=");
        stack.push("+");
        stack.push("-2");
        stack.push("-1");

        int result = CalculatorPostfixExpression.calcPostfixExpression(stack);
        assertEquals(-3, result);
    }

    @Test
    void testMinusExpression() {
        final Stack<String> stack = new Stack<>();
        stack.push("=");
        stack.push("-");
        stack.push("2");
        stack.push("8");

        int result = CalculatorPostfixExpression.calcPostfixExpression(stack);
        assertEquals(6, result);
    }

    @Test
    void testMinusWithNegativeExpression() {
        final Stack<String> stack = new Stack<>();
        stack.push("=");
        stack.push("-");
        stack.push("2");
        stack.push("-8");

        int result = CalculatorPostfixExpression.calcPostfixExpression(stack);
        assertEquals(-10, result);
    }

    @Test
    void testMinusWithTwoNegativeExpression() {
        final Stack<String> stack = new Stack<>();
        stack.push("=");
        stack.push("-");
        stack.push("-2");
        stack.push("-8");

        int result = CalculatorPostfixExpression.calcPostfixExpression(stack);
        assertEquals(-6, result);
    }

    @Test
    void testMultiplyExpression() {
        final Stack<String> stack = new Stack<>();
        stack.push("=");
        stack.push("*");
        stack.push("2");
        stack.push("8");

        int result = CalculatorPostfixExpression.calcPostfixExpression(stack);
        assertEquals(16, result);
    }

    @Test
    void testHugeExpression() {
        final Stack<String> stack = new Stack<>();
        stack.push("=");
        stack.push("+");
        stack.push("9");
        stack.push("*");
        stack.push("5");
        stack.push("+");
        stack.push("2");
        stack.push("8");

        int result = CalculatorPostfixExpression.calcPostfixExpression(stack);
        assertEquals(59, result);
    }

}
