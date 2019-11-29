package com.duplxey.javasimpleirc.util.data;

import java.io.File;
import java.io.IOException;

public class ConfigFile {

    private File directory;

    private File file;
    private YamlConfiguration config;
    private boolean isNew = false;

    public ConfigFile(File directory, String fileName) {
        this.directory = directory;

        file = new File(directory, fileName);

        create();
        load();
    }

    public ConfigFile(String fileName) {
        this(null, fileName);
    }

    private void create() {
        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    isNew = true;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void load() {
        config = new YamlConfiguration(file);
    }

    public boolean isNew() {
        return isNew;
    }

    public File getParentDirectory() {
        return directory;
    }

    public File getFile() {
        return file;
    }

    public YamlConfiguration getConfig() {
        return config;
    }
}
