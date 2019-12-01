package com.duplxey.javasimpleirc.server.irc;

import com.duplxey.javasimpleirc.server.irc.channel.Channel;
import com.duplxey.javasimpleirc.util.connection.Connection;
import com.duplxey.javasimpleirc.util.connection.Droppable;
import com.duplxey.javasimpleirc.util.packet.Message;
import com.duplxey.javasimpleirc.util.packet.PacketManager;
import com.duplxey.javasimpleirc.util.packet.request.Request;
import com.duplxey.javasimpleirc.util.packet.response.Response;
import com.duplxey.javasimpleirc.util.packet.response.ResponseType;
import com.duplxey.javasimpleirc.util.regex.RegexUtil;
import com.duplxey.javasimpleirc.util.util.StringUtil;

import java.net.Socket;

public class ServerConnection extends Connection implements Droppable {

    private IRCServer ircServer;

    private IRCServerSettings settings;
    private String username = null;
    private Channel channel = null;

    public ServerConnection(Socket socket, IRCServer ircServer) {
        super(socket);

        this.ircServer = ircServer;
        this.settings = ircServer.getSettings();
    }

    @Override
    public void onRequest(Request request) {
        switch (request.getRequestType()) {
            case FETCH_SERVER_DATA:
                respond(new Response(ResponseType.SERVER_DATA, settings.getName() + " (" + settings.getDescription() + ")"));
                break;
            case FETCH_SERVER_MOTD:
                respond(new Response(ResponseType.SERVER_MOTD, settings.getMOTD()));
                break;
            case FETCH_CHANNEL_LIST:
                StringBuilder channelBuilder = new StringBuilder();
                for (Channel channel : ircServer.getChannelManager().getChannels()) {
                    channelBuilder.append(channel.getName()).append(PacketManager.DELIMITER);
                }
                respond(new Response(ResponseType.CHANNEL_LIST, StringUtil.cutLast(channelBuilder.toString())));
                break;
            case FETCH_CHANNEL_HISTORY:
                // We can safely ignore this message if channel hasn't been set yet.
                if (channel == null) {
                    return;
                }
                StringBuilder historyBuilder = new StringBuilder();
                for (Message message : channel.getMessageHistory()) {
                    historyBuilder.append(message).append(PacketManager.DELIMITER);
                }
                respond(new Response(ResponseType.CHANNEL_HISTORY, StringUtil.cutLast(historyBuilder.toString())));
                break;
            case FETCH_CHANNEL_CLIENTS:
                // We can safely ignore this message if channel hasn't been set yet.
                if (channel == null) {
                    return;
                }
                StringBuilder clientBuilder = new StringBuilder();
                for (ServerConnection client : channel.getClients().values()) {
                    clientBuilder.append(client.getUsername()).append(PacketManager.DELIMITER);
                }
                respond(new Response(ResponseType.CHANNEL_CLIENTS, clientBuilder.toString()));
                break;
            case CHANNEL_CONNECT:
                String channelName = request.getContent();
                // If the specified channel doesn't exist, let's return the default one
                if (channelName == null || !ircServer.getChannelManager().existsChannel(channelName)) {
                    channelName = ircServer.getChannelManager().getDefaultChannel().getName();
                }
                // If the client is already in a channel, disconnect him from the old one
                if (channel != null) channel.disconnect(username);
                channel = ircServer.getChannelManager().getChannel(channelName);
                channel.connect(username, this);
                respond(new Response(ResponseType.CHANNEL_CONNECT, channelName));
                break;
            case CHANNEL_SEND_MESSAGE:
                channel.broadcast(new Response(ResponseType.CHANNEL_MESSAGE, username + PacketManager.DELIMITER + request.getContent()));
                break;
            case FETCH_USERNAME:
                respond(new Response(ResponseType.USERNAME, ircServer.getSettings().getName()));
                break;
        }
    }

    @Override
    public void onResponse(Response response) {
        switch (response.getResponseType()) {
            case USERNAME:
                String username = response.getContent();
                if (!username.matches(RegexUtil.getUsernameRegex()) || ircServer.containsClient(username)) {
                    destroy();
                    return;
                }
                this.username = username;
        }
    }

    @Override
    public void onDrop(Exception e) {
        channel.disconnect(username);
        destroy();
    }

    public String getUsername() {
        return username;
    }
}