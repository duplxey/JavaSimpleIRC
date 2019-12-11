package com.duplxey.javasimpleirc.server.commander.command.commands;

import com.duplxey.javasimpleirc.server.commander.CMessage;
import com.duplxey.javasimpleirc.server.commander.Commander;
import com.duplxey.javasimpleirc.server.commander.command.Command;
import com.duplxey.javasimpleirc.server.irc.IRCServer;
import com.duplxey.javasimpleirc.util.packet.response.Response;
import com.duplxey.javasimpleirc.util.packet.response.ResponseType;
import com.duplxey.javasimpleirc.util.util.StringUtil;

public class SayCommand extends Command {

    private IRCServer ircServer;

    public SayCommand(IRCServer ircServer) {
        super("say", "Sends a message to other clients.", new String[] {"broadcast", "alert"}, "<command> <message>");

        this.ircServer = ircServer;
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 0) {
            Commander.getLogger().info(CMessage.WRONG_SYNTAX.getText() + getSyntax());
            return;
        }
        String message = StringUtil.cutLast(StringUtil.glue(args));
        ircServer.broadcast(new Response(ResponseType.MESSAGE, ircServer.getSettings().getName() + "@" + message));
        Commander.getLogger().info("Sent '" + message + "' to all the clients.");
    }
}