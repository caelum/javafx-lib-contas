package br.com.caelum.javafx.api.util;

public class StringUtils {

    public static String capitalize(String str) {
        return Character.toUpperCase(str.charAt(0)) + str.substring(1);
    }
}
