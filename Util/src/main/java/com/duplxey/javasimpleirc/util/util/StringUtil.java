package com.duplxey.javasimpleirc.util.util;

public class StringUtil {

    public static String cutLast(String str) {
        if (str == null || str.length() == 0) return "";
        return str.substring(0, str.length()-1);
    }
}
