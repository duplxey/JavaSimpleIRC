package com.duplxey.javasimpleirc.server.commander.command.commands;

import com.duplxey.javasimpleirc.server.commander.Commander;
import com.duplxey.javasimpleirc.server.commander.command.Command;
import com.duplxey.javasimpleirc.server.irc.IRCServer;
import com.duplxey.javasimpleirc.server.irc.ServerConnection;
import com.duplxey.javasimpleirc.server.irc.channel.Channel;

import java.util.Map;

public class ClientsCommand extends Command {

    private IRCServer ircServer;

    public ClientsCommand(IRCServer ircServer) {
        super("clients", "Displays connected clients' data.", new String[] {"online", "users", "connected"});

        this.ircServer = ircServer;
    }

    @Override
    public void execute(String[] args) {
        if (ircServer.getClients().size() == 0) {
            Commander.getLogger().info("There are no clients connected.");
            return;
        }
        Commander.getLogger().info("Currently connected clients:");
        for (Channel channel : ircServer.getChannelManager().getChannels()) {
            if (channel.getClients().size() == 0) {
                Commander.getLogger().info(channel.getName() + " >>> None");
                continue;
            }
            Commander.getLogger().info(channel.getName() + " >>> ");
            Commander.getLogger().info("+------------------+----------------------------+------------+");
            Commander.getLogger().info("| Username         | IP                         | Time alive |");
            Commander.getLogger().info("+------------------+----------------------------+------------+");
            String rowFormat = "| %-16s | %-26s | %-10d |";
            for (Map.Entry<String, ServerConnection> client : channel.getClients().entrySet()) {
                Commander.getLogger().info(String.format(rowFormat, client.getKey(), client.getValue().getSocket().getRemoteSocketAddress(), client.getValue().timeAlive()/1000));
            }
            Commander.getLogger().info("+------------------+----------------------------+------------+");
        }
    }
}
