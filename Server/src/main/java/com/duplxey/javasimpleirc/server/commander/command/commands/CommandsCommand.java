package com.duplxey.javasimpleirc.server.commander.command.commands;

import com.duplxey.javasimpleirc.server.commander.Commander;
import com.duplxey.javasimpleirc.server.commander.command.Command;
import com.duplxey.javasimpleirc.server.commander.command.CommandManager;
import de.vandermeer.asciitable.AsciiTable;

public class CommandsCommand extends Command {

    public CommandsCommand() {
        super("commands", "Displays the command list.", new String[] {"help"});
    }

    @Override
    public void execute(String[] args) {
        AsciiTable at = new AsciiTable();
        at.addRule();
        at.addRow("Command", "Description", "Syntax");
        at.addRule();
        for (Command command : CommandManager.getCommands()) {
            at.addRow(command.getCommand(), command.getDescription(), command.getSyntax());
            at.addRule();
        }
        Commander.getLogger().info("List of all the registered commands: \n" + at.render());
    }
}
