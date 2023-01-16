public class Recursion {

    public static double pow(final int number, final int degree) {
        if (degree == 0)
            return 1;

        if (degree < 0) {
            double resultOfPow = raiseNumberToPower(number, -degree);
            return 1 / resultOfPow;
        }

        return raiseNumberToPower(number, degree);
    }

    private static double raiseNumberToPower(final int number, final int degree) {
        if (degree == 1)
            return number;
        else
            return number * raiseNumberToPower(number, degree - 1);
    }


    public static int sumOfDigitsNumber(final int number) {
        final String stringNumber = String.valueOf(number);
        
        return calculateSumOfDigitsNumber(stringNumber, stringNumber.length());
    }

    private static int calculateSumOfDigitsNumber(final String stringNumber, int n) {
        if (n == 1) {
            return Integer.parseInt(stringNumber.substring(0, 1));
        } else {
            return Character.getNumericValue(stringNumber.charAt(n - 1)) + calculateSumOfDigitsNumber(stringNumber, n - 1);
        }
    }
}
