package com.duplxey.javasimpleirc.server.config;

import com.duplxey.javasimpleirc.util.file.ConfigFile;

public class SettingsManager {

    private ConfigFile configFile;

    public SettingsManager() {
        init();
    }

    private void init() {
        configFile = new ConfigFile("config.yml");
        if (configFile.isNew()) {
            configFile.getConfig().setString("name", "<server_name>");
            configFile.getConfig().setString("description", "<server_description>");
            configFile.getConfig().setInt("port", 5422);
            configFile.getConfig().setBoolean("debug", false);
        }
    }

    public ConfigFile getConfigFile() {
        return configFile;
    }
}
