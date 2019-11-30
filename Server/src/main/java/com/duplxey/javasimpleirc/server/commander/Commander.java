package com.duplxey.javasimpleirc.server.commander;

import com.duplxey.javasimpleirc.server.commander.command.Command;
import com.duplxey.javasimpleirc.server.commander.command.CommandManager;
import com.duplxey.javasimpleirc.server.commander.command.CommandRegister;
import com.duplxey.javasimpleirc.server.irc.IRCServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Scanner;

public class Commander {

    private boolean running = true;
    private static Logger logger = LoggerFactory.getLogger(Commander.class);

    public Commander(IRCServer ircServer) {
        // Registers all the commands.
        new CommandRegister(ircServer);
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (running) {
            String input = scanner.nextLine();

            if (input != null) {
                input = input.toLowerCase();

                String cmd;
                String[] arguments = {};
                if (input.contains(" ")) {
                    // isValid arguments
                    arguments = input.split(" ");
                    cmd = arguments[0];
                    arguments = Arrays.copyOfRange(arguments, 1, arguments.length);
                } else {
                    cmd = input;
                }
                Command command = CommandManager.getCommand(cmd);
                if (command == null) {
                    Commander.getLogger().info(CMessage.UNKNOWN_COMMAND.getText());
                } else {
                    command.execute(arguments);
                }
            }
        }
    }

    public void stop() {
        running = false;
    }

    public static Logger getLogger() {
        return logger;
    }
}
