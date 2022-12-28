import java.util.Map;
import java.util.Stack;

public abstract class CalculatorPostfixExpression {
    private static final Map<String, CalculationTwoValue<Integer>> operations =
            Map.of(
                    "+", (v1, v2) -> v1 + v2,
                    "-", (v1, v2) -> v2 - v1,
                    "*", (v1, v2) -> Math.max(v1, 1) * v2,
                    "/", (v1, v2) -> Math.max(v2, 1) / Math.max(v1, 1)
            );

    public static int calcPostfixExpression(final Stack<String> expression) throws IllegalStateException, NumberFormatException, UnsupportedOperationException {
        final int size = expression.size();

        if (size == 0) throw new IllegalArgumentException("expression is empty");

        final Stack<Integer> result = new Stack<>();

        for (int i = 0; i < size; i++) {
            final String operator = expression.pop();

            switch (operator) {
                case "+":
                case "-":
                case "/":
                case "*": {
                    calculate(result, operator);
                    continue;
                }
                case "=": {
                    if (result.size() != 1)
                        throw new IllegalArgumentException("calculation buffer size > 1, so we can't show the result");
                    return result.pop();
                }
                default: {
                    Integer number = Integer.parseInt(operator);
                    result.push(number);
                }
            }

        }

        return 0;
    }

    private static void calculate(final Stack<Integer> stack, final String operation) {
        final int size = stack.size();

        if (size == 0)
            throw new IllegalArgumentException("operator can't be invoked on nothing");

        int result = 0;
        for (int i = 0; i < size; i++) {
            final Integer el = stack.pop();

            if (!operations.containsKey(operation))
                throw new UnsupportedOperationException("operation not supported");

            result = operations.get(operation).calc(result, el);
        }
        stack.push(result);
    }

    interface CalculationTwoValue<V> {
        V calc(V v1, V v2);
    }
}
