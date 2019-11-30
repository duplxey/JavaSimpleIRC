package com.duplxey.javasimpleirc.server.commander;

public enum CMessage {
    UNKNOWN_COMMAND("Unknown command! Use 'help' for help."),
    NO_COMMAND("Cannot find the specified command."),
    WRONG_SYNTAX("Wrong syntax! "),
    USER_NOT_FOUND("The specified user could not be found."),
    ;

    private String text;

    CMessage(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
