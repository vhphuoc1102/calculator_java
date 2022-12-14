package feature;

public class BaseConverter {
    public static String baseConversion(String number, int sBase, int dBase) {
        // Parse the number with source radix
        // and return in specified radix(base)
        return Integer.toString(
                Integer.parseInt(number, sBase), dBase);
    }
}
