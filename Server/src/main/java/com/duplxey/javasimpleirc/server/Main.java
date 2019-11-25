package com.duplxey.javasimpleirc.server;

import com.duplxey.javasimpleirc.server.commander.Commander;
import com.duplxey.javasimpleirc.server.irc.IRCServer;

public class Main {

    public static void main(String[] args) {
        IRCServer ircServer = new IRCServer();

        Commander commander = new Commander(ircServer);
        commander.start();
    }
}
