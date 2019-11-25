package com.duplxey.javasimpleirc.server.commander.command;

import java.util.*;

public class CommandManager {

    private static HashMap<String, Command> commands = new LinkedHashMap<>();

    public static void registerCommand(Command command) {
        commands.put(command.getCommand().toLowerCase(), command);
    }

    public static void unregisterCommand(Command command) {
        Iterator it = commands.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            if (entry.getValue() == command) it.remove();
        }
    }

    public static void unregisterCommand(String command) {
        commands.remove(command);
    }

    public static Command getCommand(String command) {
        if (commands.containsKey(command)) {
            return commands.get(command);
        } else {
            for (Command cmd : commands.values()) {
                for (String alias : cmd.getAliases()) {
                    if (alias.equalsIgnoreCase(command)) return cmd;
                }
            }
        }
        return null;
    }

    public static Collection<Command> getCommands() {
        return commands.values();
    }
}
