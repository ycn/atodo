package cc.ycn.util;

public class UserInputHelper {

    public static String getString(String raw) {
        if (raw == null || raw.matches("")) {
            return "";
        }

        String cooked = raw.trim();
        return cooked;
    }
}
