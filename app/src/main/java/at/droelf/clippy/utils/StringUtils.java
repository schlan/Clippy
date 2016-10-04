package at.droelf.clippy.utils;

import java.util.Locale;

public class StringUtils {

    public static boolean isValid(String string){
        return string != null && string.length() > 0;
    }

    public static String capitalize(final String line) {
        return Character.toUpperCase(line.charAt(0)) + line.substring(1).toLowerCase(Locale.US);
    }
}
