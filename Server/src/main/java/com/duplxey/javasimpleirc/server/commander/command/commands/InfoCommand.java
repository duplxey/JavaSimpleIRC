package com.duplxey.javasimpleirc.server.commander.command.commands;

import com.duplxey.javasimpleirc.server.commander.CMessage;
import com.duplxey.javasimpleirc.server.commander.Commander;
import com.duplxey.javasimpleirc.server.commander.command.Command;
import com.duplxey.javasimpleirc.server.commander.command.CommandManager;

public class InfoCommand extends Command {

    public InfoCommand() {
        super("info", "Displays the info of a command.", new String[] {"i"}, "<command> <target-command>");
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 1) {
            Commander.getLogger().info(CMessage.WRONG_SYNTAX.getText() + getSyntax());
            return;
        }
        String name = args[0];
        Command command = CommandManager.getCommand(name);
        if (command == null) {
            Commander.getLogger().info(CMessage.NO_COMMAND.getText());
            return;
        }
        Commander.getLogger().info(name + "'s information:");
        Commander.getLogger().info("+---------------------------------+--------------------------+");
        Commander.getLogger().info("| Description                     | Syntax                   |");
        Commander.getLogger().info("+---------------------------------+--------------------------+");
        String rowFormat = "| %-31s | %-24s |";
        Commander.getLogger().info(String.format(rowFormat, command.getDescription(), command.getSyntax()));
        Commander.getLogger().info("+---------------------------------+--------------------------+");
    }
}
