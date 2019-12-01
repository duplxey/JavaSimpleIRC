package com.duplxey.javasimpleirc.server.irc;

import com.duplxey.javasimpleirc.util.file.ConfigFile;
import com.duplxey.javasimpleirc.util.file.YamlConfiguration;

public class IRCServerSettings {

    private YamlConfiguration config;

    public IRCServerSettings() {
        init();
    }

    private void init() {
        ConfigFile file = new ConfigFile("config.yml");
        config = file.getConfig();
        if (file.isNew()) {
            config.setString("name", "<server_name>");
            config.setString("description", "<server_description>");
            config.setString("motd", "<server_motd>");
            config.setInt("port", 5422);
        }
    }

    public String getName() {
        return config.getString("name");
    }

    public String getDescription() {
        return config.getString("description");
    }

    public String getMOTD() {
        return config.getString("motd");
    }

    public void setMOTD(String motd) {
        config.setString("motd", motd);
    }

    public int getPort() {
        return config.getInt("port");
    }
}
