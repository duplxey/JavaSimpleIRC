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
        String message = StringUtil.cutLast(StringUtil.glue(args));
        ircServer.getSettings().setMOTD(message);
        Commander.getLogger().info("MOTD has been set to '" + message + "'.");
    }
}
