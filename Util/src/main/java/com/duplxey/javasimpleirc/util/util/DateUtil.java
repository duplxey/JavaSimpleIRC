package com.duplxey.javasimpleirc.util.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    private static SimpleDateFormat timeFormat;
    private static SimpleDateFormat dateFormat;

    static {
        timeFormat = new SimpleDateFormat("HH:mm:ss");
        dateFormat = new SimpleDateFormat("d.M.Y");
    }

    public static String applyTimeFormat(Date date) {
        return timeFormat.format(date);
    }

    public static String applyDateFormat(Date date) {
        return dateFormat.format(date);
    }
}
