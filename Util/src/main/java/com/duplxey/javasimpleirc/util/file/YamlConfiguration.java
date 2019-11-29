package com.duplxey.javasimpleirc.util.file;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class YamlConfiguration {

    private File file;

    private Yaml yaml;
    private Map<String, Object> data;

    public YamlConfiguration(File file) {
        this.file = file;

        load();
    }

    private void load() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));

            DumperOptions dumperOptions = new DumperOptions();
            dumperOptions.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
            dumperOptions.setPrettyFlow(true);

            yaml = new Yaml(dumperOptions);
            Object d = yaml.load(reader);
            if (d == null) {
                data = new HashMap<>();
            } else {
                data = (Map<String, Object>) d;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void save() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));

            yaml.dump(data, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getString(String key) {
        return (String) data.get(key);
    }

    public char getChar(String key) {
        return (char) data.get(key);
    }

    public float getFloat(String key) {
        return (float) data.get(key);
    }

    public double getDouble(String key) {
        return (double) data.get(key);
    }

    public int getInt(String key) {
        return (int) data.get(key);
    }

    public boolean getBoolean(String key) {
        return (boolean) data.get(key);
    }

    public void setString(String key, String value) {
        data.put(key, value);
        save();
    }

    public void setChar(String key, char value) {
        data.put(key, value);
        save();
    }

    public void setFloat(String key, float value) {
        data.put(key, value);
        save();
    }

    public void setDouble(String key, double value) {
        data.put(key, value);
        save();
    }

    public void setInt(String key, int value) {
        data.put(key, value);
        save();
    }

    public void setBoolean(String key, boolean value) {
        data.put(key, value);
        save();
    }

    public Map<String, Object> getData() {
        return data;
    }

    public File getFile() {
        return file;
    }
}
