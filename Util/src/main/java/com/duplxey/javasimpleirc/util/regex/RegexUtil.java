package com.duplxey.javasimpleirc.util.regex;

public class RegexUtil {

    public static String getUsernameRegex() {
        return "[A-z0-9_]{4,16}";
    }

    public static String getChannelRegex() {
        return "[A-z0-9_]{1,16}";
    }
}
