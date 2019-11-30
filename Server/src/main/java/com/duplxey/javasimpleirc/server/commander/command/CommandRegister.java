package com.duplxey.javasimpleirc.server.commander.command;

import com.duplxey.javasimpleirc.server.commander.command.commands.*;
import com.duplxey.javasimpleirc.server.irc.IRCServer;

public class CommandRegister {

    public CommandRegister(IRCServer ircServer) {
        new CommandsCommand();
        new ExitCommand(ircServer);
        new InfoCommand();
        new ClientsCommand(ircServer);
        new KickCommand(ircServer);
        new SayCommand(ircServer);
    }
}
