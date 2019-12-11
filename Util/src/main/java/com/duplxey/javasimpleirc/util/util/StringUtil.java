package com.duplxey.javasimpleirc.util.util;

public class StringUtil {

    public static String cutLast(String str) {
        if (str == null || str.length() == 0) return "";
        return str.substring(0, str.length()-1);
    }

    public static String glue(Object[] arr, String joiner, int skip) {
        StringBuilder builder = new StringBuilder();
        for (int i = skip; i < arr.length; i++) {
            builder.append(arr[i]).append(joiner);
        }
        return builder.toString();
    }

    public static String glue(Object[] arr, int skip) {
        return glue(arr, " ", skip);
    }

    public static String glue(Object[] arr, String joiner) {
        return glue(arr, joiner, 0);
    }

    public static String glue(Object[] arr) {
        return glue(arr, " ", 0);
    }
}
