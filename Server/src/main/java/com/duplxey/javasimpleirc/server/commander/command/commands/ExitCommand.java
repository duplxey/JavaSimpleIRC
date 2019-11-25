package com.duplxey.javasimpleirc.server.commander.command.commands;

import com.duplxey.javasimpleirc.server.commander.Commander;
import com.duplxey.javasimpleirc.server.commander.command.Command;
import com.duplxey.javasimpleirc.server.constants.Message;

public class ExitCommand extends Command {

    public ExitCommand() {
        super("exit", "Terminates the program.", new String[] {"quit"});
    }

    @Override
    public void execute(String[] args) {
        Commander.getLogger().info(Message.PROGRAM_EXIT.getText());
        System.exit(0);
    }
}
