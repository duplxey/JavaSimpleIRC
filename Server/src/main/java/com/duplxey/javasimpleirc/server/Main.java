package com.duplxey.javasimpleirc.server;

import com.duplxey.javasimpleirc.server.commander.Commander;
import com.duplxey.javasimpleirc.server.config.SettingsManager;
import com.duplxey.javasimpleirc.server.irc.IRCServer;

public class Main {

    public static void main(String[] args) {
        SettingsManager settingsManager = new SettingsManager();
        IRCServer ircServer = new IRCServer(settingsManager);

        Commander commander = new Commander(ircServer);
        commander.start();
    }
}
