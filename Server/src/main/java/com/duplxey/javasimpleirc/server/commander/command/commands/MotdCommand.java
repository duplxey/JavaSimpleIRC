package com.duplxey.javasimpleirc.server.commander.command.commands;

import com.duplxey.javasimpleirc.server.commander.Commander;
import com.duplxey.javasimpleirc.server.commander.command.Command;
import com.duplxey.javasimpleirc.server.irc.IRCServer;
import com.duplxey.javasimpleirc.util.util.StringUtil;

public class MotdCommand extends Command {

    private IRCServer ircServer;

    public MotdCommand(IRCServer ircServer) {
        super("motd", "Displays/sets the motd.", "<command> | <motd>");

        this.ircServer = ircServer;
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 0) {
            Commander.getLogger().info("MOTD: " + ircServer.getSettings().getMOTD());
            return;
        }
        StringBuilder builder = new StringBuilder();
        for (String arg : args) builder.append(arg).append(" ");
        String message = StringUtil.cutLast(builder.toString());
        ircServer.getSettings().setMOTD(message);
        Commander.getLogger().info("MOTD has been set to '" + message + "'.");
    }
}
