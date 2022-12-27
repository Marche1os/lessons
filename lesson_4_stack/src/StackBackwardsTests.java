import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StackBackwardsTests {
    private StackBackwards<Integer> emptyStack;
    private StackBackwards<Integer> completedStack;

    private static final int PRIMARY_SIZE = 10;

    @BeforeEach
    void generateTestData() {
        emptyStack = new StackBackwards<>();
        completedStack = new StackBackwards<>();

        for (int i = 0; i < PRIMARY_SIZE; i++)
            completedStack.push(i);
    }

    @AfterEach
    void resetTestData() {
        emptyStack = new StackBackwards<>();
        completedStack = new StackBackwards<>();
    }

    @Test
    @DisplayName("test pop function when list is empty")
    void popEmpty() {
        assertEquals(0, emptyStack.size());
        assertNull(emptyStack.pop());
    }

    @Test
    @DisplayName("test pop function when list is full of elements")
    void popFromCompleted() {
        assertEquals(PRIMARY_SIZE, completedStack.size());

        completedStack.push(128);

        assertEquals(PRIMARY_SIZE + 1, completedStack.size());

        final int removedValue = completedStack.pop();

        assertEquals(128, removedValue);
        assertEquals(PRIMARY_SIZE, completedStack.size());

        final int size = completedStack.size();
        for (int i = 0; i < size; i++)
            assertNotEquals(removedValue, completedStack.pop());

        assertEquals(0, completedStack.size());
    }

    @Test
    @DisplayName("test push elements into empty stack")
    void push() {
        final int size = 2048;
        for (int i = 0; i < size; i++)
            emptyStack.push(i);

        assertEquals(size, emptyStack.size());

        for (int i = size - 1; i >= 0; i--)
            assertEquals(i, emptyStack.pop());

        assertEquals(0, emptyStack.size());
        assertNull(emptyStack.pop());
    }

    @Test
    @DisplayName(
            "test peek function on empty and completed stacks. " +
                    "Also comparing actual value with expected value in the loop"
    )
    void peek() {
        assertEquals(0, emptyStack.size());
        assertNull(emptyStack.peek());
        assertEquals(PRIMARY_SIZE, completedStack.size());

        for (int i = PRIMARY_SIZE - 1; i >= 0; i--) {
            assertEquals(i, completedStack.peek());
            assertEquals(i + 1, completedStack.size());

            final int removedValue = completedStack.pop();
            assertEquals(removedValue, i);
        }
    }

    @Test
    void size() {
        assertEquals(0, emptyStack.size());
        assertEquals(10, completedStack.size());

        emptyStack.push(128);
        assertEquals(1, emptyStack.size());

        emptyStack.pop();
        assertEquals(0, emptyStack.size());

        completedStack.peek();
        completedStack.pop();
        assertEquals(9, completedStack.size());
    }
}
