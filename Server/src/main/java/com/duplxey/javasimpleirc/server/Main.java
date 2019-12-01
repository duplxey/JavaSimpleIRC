package com.duplxey.javasimpleirc.server;

import com.duplxey.javasimpleirc.server.commander.Commander;
import com.duplxey.javasimpleirc.server.irc.IRCServer;
import com.duplxey.javasimpleirc.server.irc.IRCServerSettings;

public class Main {

    public static void main(String[] args) {
        IRCServer ircServer = new IRCServer(new IRCServerSettings());

        Commander commander = new Commander(ircServer);
        commander.start();
    }
}
