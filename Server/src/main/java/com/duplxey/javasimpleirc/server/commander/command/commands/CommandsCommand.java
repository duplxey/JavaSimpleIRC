package com.duplxey.javasimpleirc.server.commander.command.commands;

import com.duplxey.javasimpleirc.server.commander.Commander;
import com.duplxey.javasimpleirc.server.commander.command.Command;
import com.duplxey.javasimpleirc.server.commander.command.CommandManager;

public class CommandsCommand extends Command {

    public CommandsCommand() {
        super("commands", "Displays the command list.", new String[] {"help"});
    }

    @Override
    public void execute(String[] args) {
        Commander.getLogger().info("List of all the registered commands: ");
        for (Command command : CommandManager.getCommands()) {
            Commander.getLogger().info(command.info());
        }
    }
}
