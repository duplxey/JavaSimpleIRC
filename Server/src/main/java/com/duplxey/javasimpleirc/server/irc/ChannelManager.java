package com.duplxey.javasimpleirc.server.irc;

import com.duplxey.javasimpleirc.util.file.ConfigFile;
import com.duplxey.javasimpleirc.util.file.YamlConfiguration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChannelManager {

    private YamlConfiguration config;
    private List<String> registeredChannels = new ArrayList<>();

    public ChannelManager() {
        init();
        load();
    }

    private void init() {
        ConfigFile file = new ConfigFile("channels.yml");
        config = file.getConfig();
        if (file.isNew()) {
            config.setStringList("channels", new ArrayList<>(Collections.singletonList("general")));
        }
    }

    private void load() {
        registeredChannels = config.getStringList("channels");
    }

    public void addChannel(String channelName) {
        registeredChannels.add(channelName);
        config.setStringList("channels", registeredChannels);
    }

    public void removeChannel(String channelName) {
        registeredChannels.remove(channelName);
        config.setStringList("channels", registeredChannels);
    }

    public void moveChannel(String channelName, int index) {
        registeredChannels.remove(channelName);
        registeredChannels.add(index, channelName);
        config.setStringList("channels", registeredChannels);
    }

    public boolean existsChannel(String channelName) {
        return registeredChannels.contains(channelName);
    }

    public List<String> getRegisteredChannels() {
        return registeredChannels;
    }
}
