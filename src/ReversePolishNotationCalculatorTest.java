import java.util.ArrayDeque;
import java.util.Deque;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ReversePolishNotationCalculatorTest {

    private static ReversePolishNotationCalculator reversePolishNotationCalculator;

    @BeforeEach
    public void beforeEach() {
        reversePolishNotationCalculator = new ReversePolishNotationCalculator();
    }

    @Test
    public void shouldCalculateMultiplication() {
        int multiplication = reversePolishNotationCalculator.calculatePolishNotation("5 7 *");
        Assertions.assertEquals(multiplication, 35);
    }

    @Test
    public void shouldCalculateAddition() {
        int addition = reversePolishNotationCalculator.calculatePolishNotation("5 7 +");
        Assertions.assertEquals(addition, 12);
    }

    @Test
    public void shouldCalculateSubtraction() {
        int subtraction = reversePolishNotationCalculator.calculatePolishNotation("7 5 -");
        Assertions.assertEquals(subtraction, 2);
    }

    @Test
    public void shouldCalculateWithNegativeNumbers() {
        int subtraction = reversePolishNotationCalculator.calculatePolishNotation("7 -5 -");
        Assertions.assertEquals(subtraction, 12);
    }

}

class ReversePolishNotationCalculator {

    public int calculatePolishNotation(String str) {
        String[] parts = str.split(" ");
        Deque<Integer> numbers = new ArrayDeque<>();
        int index = 0;

        while (index != parts.length) {

            if (parts[index].isBlank()) {
                index++;
                continue;
            }

            if (isOperation(parts[index])) {
                int operandOne = numbers.pop();
                int operandTwo = numbers.pop();

                if (parts[index].equals("+")) {
                    numbers.push(operandOne + operandTwo);
                } else if (parts[index].equals("-")) {
                    numbers.push(operandTwo - operandOne);
                } else if (parts[index].equals("*")) {
                    numbers.push(operandOne * operandTwo);
                }

            } else {
                numbers.push(Integer.parseInt(parts[index]));
            }

            index++;
        }

        return numbers.pop();
    }

    private boolean isOperation(String part) {
        if (part.equals("+")
                || part.equals("-")
                || part.equals("*")) {
            return true;
        }

        return false;
    }
}
