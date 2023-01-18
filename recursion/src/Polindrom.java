public class Polindrom {

    public static boolean isPolindrom(final String s) {
        if (s.length() < 2) {
            return true;
        }

        if (s.charAt(0) == s.charAt(s.length() - 1)) {
            return isPolindrom(s.substring(1, s.length() - 1));
        }

        return false;
    }
}
