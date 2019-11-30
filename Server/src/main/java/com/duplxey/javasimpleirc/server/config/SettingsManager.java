package com.duplxey.javasimpleirc.server.config;

import com.duplxey.javasimpleirc.util.file.ConfigFile;
import com.duplxey.javasimpleirc.util.file.YamlConfiguration;

public class SettingsManager {

    private ConfigFile configFile;
    private YamlConfiguration config;

    public SettingsManager() {
        init();
    }

    private void init() {
        configFile = new ConfigFile("config.yml");
        config = configFile.getConfig();
        if (configFile.isNew()) {
            config.setString("name", "<server_name>");
            config.setString("description", "<server_description>");
            config.setString("motd", "<server_motd>");
            config.setInt("port", 5422);
            config.setBoolean("debug", false);
        }
    }

    public YamlConfiguration getConfig() {
        return config;
    }
}
