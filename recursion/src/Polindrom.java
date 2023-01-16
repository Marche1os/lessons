public class Polindrom {

    public static boolean isPolindromString(final String inputString) {
        if (inputString.length() < 2)
            return true;

        return inputString.equalsIgnoreCase(reversedString(inputString, inputString.length()));
    }

    private static String reversedString(final String inputString, int stringSize) {
        if (stringSize == 1) {
            return inputString.substring(0, 1);
        } else {
            return inputString.charAt(stringSize - 1) + reversedString(inputString, stringSize - 1);
        }
    }
}
