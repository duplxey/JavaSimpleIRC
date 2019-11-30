package com.duplxey.javasimpleirc.server.commander.command.commands;

import com.duplxey.javasimpleirc.server.commander.Commander;
import com.duplxey.javasimpleirc.server.commander.command.Command;
import com.duplxey.javasimpleirc.server.irc.IRCServer;

public class ExitCommand extends Command {

    private IRCServer ircServer;

    public ExitCommand(IRCServer ircServer) {
        super("exit", "Terminates the program.", new String[] {"quit", "stop", "end"});

        this.ircServer = ircServer;
    }

    @Override
    public void execute(String[] args) {
        Commander.getLogger().info("Terminating the program.");
        ircServer.close();
        System.exit(0);
    }
}
