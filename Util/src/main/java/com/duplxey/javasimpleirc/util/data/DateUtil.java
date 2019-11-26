package com.duplxey.javasimpleirc.util.data;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    private static SimpleDateFormat timeFormat;

    static {
        timeFormat = new SimpleDateFormat("HH:mm:ss");
    }

    public static String getTime() {
        return timeFormat.format(new Date());
    }
}
