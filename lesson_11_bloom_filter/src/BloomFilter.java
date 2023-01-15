//https://www.chrislaux.com/hashtable
public class BloomFilter {
    public int filter_len;
    private int bitmask;

    public BloomFilter(int f_len) {
        filter_len = f_len;
        bitmask = 0;
    }

    public int hash1(String str1) {
        int hash = 0;
        for (int i = 0; i < str1.length(); i++) {
            int code = (int) str1.charAt(i);
            hash = (hash * 17) + code;
        }

        return hash % filter_len;
    }

    public int hash2(String str1) {
        int hash = 0;
        for (int i = 0; i < str1.length(); i++) {
            int code = (int) str1.charAt(i);
            hash = (hash * 223) + code;
        }

        return hash % filter_len;
    }

    public void add(String str1) {
        int ind1 = hash1(str1);
        int ind2 = hash2(str1);
        bitmask = (bitmask | (1 << ind1)) | (bitmask | (1 << ind2));
    }

    public boolean isValue(String str1) {
        final int ind1 = hash1(str1);
        final int ind2 = hash2(str1);

        return ((bitmask & (1 << ind1)) != 0) && ((bitmask & (1 << ind2)) != 0);
    }
}