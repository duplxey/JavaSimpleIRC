package com.duplxey.javasimpleirc.server.commander;

public enum CMessage {
    UNKNOWN_COMMAND("Unknown command! Use 'help' for help."),
    NO_COMMAND("Cannot find the specified command."),
    WRONG_SYNTAX("Wrong syntax! "),
    USER_NOT_FOUND("The specified user could not be found."),
    CHANNEL_DOESNT_EXIST("Channel with the specified name doesn't exist."),
    CHANNEL_EXISTS("Channel with the specified name already exists."),
    NOT_INTEGER("Argument could not be converted to an integer."),
    REGEX_NO_MATCH("Argument doesn't match regex."),
    ;

    private String text;

    CMessage(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
