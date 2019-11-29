package com.duplxey.javasimpleirc.server.commander.command.commands;

import com.duplxey.javasimpleirc.server.commander.Commander;
import com.duplxey.javasimpleirc.server.commander.command.Command;
import com.duplxey.javasimpleirc.server.commander.command.CommandManager;
import com.duplxey.javasimpleirc.server.constants.Message;

public class InfoCommand extends Command {

    public InfoCommand() {
        super("info", "Displays the info of a command.", new String[] {"i"}, "<command> <target-command>");
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 1) {
            Commander.getLogger().info(Message.WRONG_SYNTAX.getText() + getSyntax());
            return;
        }
        String name = args[0];
        Command command = CommandManager.getCommand(name);
        if (command == null) {
            Commander.getLogger().info(Message.NO_COMMAND.getText());
            return;
        }
        Commander.getLogger().info(command.info());
    }
}
