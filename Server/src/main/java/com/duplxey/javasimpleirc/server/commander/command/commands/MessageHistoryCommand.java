package com.duplxey.javasimpleirc.server.commander.command.commands;

import com.duplxey.javasimpleirc.server.commander.Commander;
import com.duplxey.javasimpleirc.server.commander.command.Command;
import com.duplxey.javasimpleirc.server.irc.IRCServer;
import com.duplxey.javasimpleirc.util.packet.Message;

public class MessageHistoryCommand extends Command {

    private IRCServer ircServer;

    public MessageHistoryCommand(IRCServer ircServer) {
        super("history", "Displays the message history.");

        this.ircServer = ircServer;
    }

    @Override
    public void execute(String[] args) {
        for (Message message : ircServer.getMessageHistory()) {
            Commander.getLogger().info(message.toString());
        }
    }
}
