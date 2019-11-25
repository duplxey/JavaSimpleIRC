package com.duplxey.javasimpleirc.server.commander.command;

public abstract class Command {

    private String command;
    private String description;
    private String[] aliases;
    private String syntax;

    /**
     * Constructs a new command with the syntax and aliases.
     *
     * @param command  Command
     * @param description  Description
     * @param syntax  Syntax
     * @param aliases  Aliases
     */
    public Command(String command, String description, String[] aliases, String syntax) {
        this.command = command;
        this.description = description;
        this.syntax = syntax.replaceAll("<command>", command);
        this.aliases = aliases;

        CommandManager.registerCommand(this);
    }

    /**
     * Constructs a new command with aliases.
     *
     * @param command  Command
     * @param description  Description
     * @param aliases  Aliases
     */
    public Command(String command, String description, String[] aliases) {
        this(command, description, aliases, "<command>");
    }

    /**
     * Constructs a new command with the syntax.
     *
     * @param command  Command
     * @param description  Description
     * @param syntax  Syntax
     */
    public Command(String command, String description, String syntax) {
        this(command, description, new String[] {}, syntax);
    }

    /**
     * Constructs a new command.
     *
     * @param command  Command
     * @param description  Description
     */
    public Command(String command, String description) {
        this(command, description, new String[] {}, "<command>");
    }

    /**
     * Executes the command.
     */
    public abstract void execute(String[] args);

    /**
     * Get command.
     *
     * @return  Command
     */
    public String getCommand() {
        return command;
    }

    /**
     * Gets command's description.
     *
     * @return  Description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets command's aliases.
     *
     * @return  Aliases
     */
    public String[] getAliases() {
        return aliases;
    }

    /**
     * Gets formatted command's syntax.
     *
     * @return  Syntax
     */
    public String getSyntax() {
        return syntax;
    }

    /**
     * Gets command's information.
     *
     * @return  Command information
     */
    public String info() {
        return command + " | " + description + " | " + syntax;
    }
}
