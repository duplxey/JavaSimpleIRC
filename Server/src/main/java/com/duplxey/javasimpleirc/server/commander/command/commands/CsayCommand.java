package com.duplxey.javasimpleirc.server.commander.command.commands;

import com.duplxey.javasimpleirc.server.commander.CMessage;
import com.duplxey.javasimpleirc.server.commander.Commander;
import com.duplxey.javasimpleirc.server.commander.command.Command;
import com.duplxey.javasimpleirc.server.irc.IRCServer;
import com.duplxey.javasimpleirc.server.irc.channel.Channel;
import com.duplxey.javasimpleirc.util.packet.response.Response;
import com.duplxey.javasimpleirc.util.packet.response.ResponseType;
import com.duplxey.javasimpleirc.util.util.StringUtil;

public class CsayCommand extends Command {

    private IRCServer ircServer;

    public CsayCommand(IRCServer ircServer) {
        super("channelsay", "Sends a message to all channel's clients.", new String[] {"csay"}, "<command> <channel> <message>");

        this.ircServer = ircServer;
    }

    @Override
    public void execute(String[] args) {
        if (args.length < 2) {
            Commander.getLogger().info(CMessage.WRONG_SYNTAX.getText() + getSyntax());
            return;
        }
        String channelName = args[0];
        if (!ircServer.getChannelManager().existsChannel(channelName)) {
            Commander.getLogger().info(CMessage.CHANNEL_DOESNT_EXIST.getText());
            return;
        }
        Channel channel = ircServer.getChannelManager().getChannel(channelName);
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i < args.length; i++) builder.append(args[i]).append(" ");
        String message = StringUtil.cutLast(builder.toString());
        channel.broadcast(new Response(ResponseType.CHANNEL_MESSAGE, ircServer.getSettings().getName() + "@" + message));
        Commander.getLogger().info("Sent '" + message + "' to all the clients in '" + channelName + "'.");
    }
}