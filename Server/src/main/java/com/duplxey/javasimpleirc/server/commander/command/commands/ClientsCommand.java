package com.duplxey.javasimpleirc.server.commander.command.commands;

import com.duplxey.javasimpleirc.server.commander.Commander;
import com.duplxey.javasimpleirc.server.commander.command.Command;
import com.duplxey.javasimpleirc.server.irc.IRCServer;
import com.duplxey.javasimpleirc.server.irc.ServerConnection;

import java.util.Iterator;
import java.util.Map;

public class ClientsCommand extends Command {

    private IRCServer ircServer;

    public ClientsCommand(IRCServer ircServer) {
        super("clients", "Displays connected clients + data.");

        this.ircServer = ircServer;
    }

    @Override
    public void execute(String[] args) {
        Iterator it = ircServer.getClients().entrySet().iterator();
        if (!it.hasNext()) {
            Commander.getLogger().info("There are no clients connected.");
            return;
        }
        Commander.getLogger().info("Currently connected clients: ");
        while (it.hasNext()) {
            Map.Entry e = (Map.Entry) it.next();
            String username = (String) e.getKey();
            ServerConnection connection = (ServerConnection) e.getValue();
            Commander.getLogger().info(username + " -> " + connection.getSocket().getRemoteSocketAddress() + " (" + connection.aliveSince()/1000 + "s)");
        }
    }
}
