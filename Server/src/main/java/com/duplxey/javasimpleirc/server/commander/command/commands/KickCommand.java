package com.duplxey.javasimpleirc.server.commander.command.commands;

import com.duplxey.javasimpleirc.server.commander.Commander;
import com.duplxey.javasimpleirc.server.commander.command.Command;
import com.duplxey.javasimpleirc.server.constants.Message;
import com.duplxey.javasimpleirc.server.irc.IRCServer;

public class KickCommand extends Command {

    private IRCServer ircServer;

    public KickCommand(IRCServer ircServer) {
        super("kick", "Kicks an user.", "<command> <username>");

        this.ircServer = ircServer;
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 1) {
            Commander.getLogger().info(Message.WRONG_SYNTAX.getText() + getSyntax());
            return;
        }
        String username = args[0];
        if (!ircServer.containsClient(username)) {
            Commander.getLogger().info("Could not find the user with the specified username.");
            return;
        }
        Commander.getLogger().info("Kicked '" + username + "'.");
        ircServer.getClient(username).onDrop(null);
    }
}
