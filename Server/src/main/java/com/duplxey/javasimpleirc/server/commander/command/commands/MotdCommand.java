package com.duplxey.javasimpleirc.server.commander.command.commands;

import com.duplxey.javasimpleirc.server.commander.Commander;
import com.duplxey.javasimpleirc.server.commander.command.Command;
import com.duplxey.javasimpleirc.server.irc.IRCServer;

public class MotdCommand extends Command {

    private IRCServer ircServer;

    public MotdCommand(IRCServer ircServer) {
        super("motd", "Displays/sets the motd.", "<command> | <motd>");

        this.ircServer = ircServer;
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 0) {
            Commander.getLogger().info("MOTD: " + ircServer.getSettingsManager().getConfig().getString("motd"));
            return;
        }
        StringBuilder builder = new StringBuilder();
        for (String arg : args) builder.append(arg).append(" ");
        String message = builder.toString();
        message = message.substring(0, message.length()-1);
        ircServer.getSettingsManager().getConfig().setString("motd", message);
        Commander.getLogger().info("MOTD has been set to: " + message);
    }
}
