public abstract class Polindrom {

    public static boolean isPolindrom(final String input) {
        if (input.isBlank())
            return false;

        final Deque<String> deque = new Deque<>();

        for (int i = 0; i < input.length(); i++)
            deque.addTail(String.valueOf(input.charAt(i)).toLowerCase());

        for (int i = 0; i < deque.size() - 1; i++) {
            final String front = deque.removeFront();
            final String tail = deque.removeTail();
            if (!front.equals(tail))
                return false;
        }

        return true;
    }
}
