package com.duplxey.javasimpleirc.server.commander.command.commands;

import com.duplxey.javasimpleirc.server.commander.Commander;
import com.duplxey.javasimpleirc.server.commander.command.Command;
import com.duplxey.javasimpleirc.server.irc.IRCServer;
import com.duplxey.javasimpleirc.server.irc.ServerConnection;
import de.vandermeer.asciitable.AsciiTable;

import java.util.Iterator;
import java.util.Map;

public class ClientsCommand extends Command {

    private IRCServer ircServer;

    public ClientsCommand(IRCServer ircServer) {
        super("clients", "Displays currently connected clients (with their data).", new String[] {"online", "users", "connected"});

        this.ircServer = ircServer;
    }

    @Override
    public void execute(String[] args) {
        Iterator it = ircServer.getClients().entrySet().iterator();
        if (!it.hasNext()) {
            Commander.getLogger().info("There are no clients connected.");
            return;
        }
        AsciiTable at = new AsciiTable();
        at.addRule();
        at.addRow("Username", "IP", "Time alive");
        at.addRule();
        while (it.hasNext()) {
            Map.Entry e = (Map.Entry) it.next();
            String username = (String) e.getKey();
            ServerConnection connection = (ServerConnection) e.getValue();
            at.addRow(username, connection.getSocket().getRemoteSocketAddress(), connection.aliveSince()/1000 + "s");
            at.addRule();
        }
        Commander.getLogger().info("Currently connected clients: \n" + at.render());
    }
}
