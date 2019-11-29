package com.duplxey.javasimpleirc.util.file;

import java.io.*;
import java.util.stream.Collectors;

public class FileUtil {

    public static String getFileContent(String fileName) {
        try {
            return sumLines(new FileInputStream(new File(fileName)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getResourceContent(ClassLoader classLoader, String fileName) {
        return sumLines(classLoader.getResourceAsStream(fileName));
    }

    private static String sumLines(InputStream is) {
        if (is != null) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
                return reader.lines().collect(Collectors.joining(System.lineSeparator()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
