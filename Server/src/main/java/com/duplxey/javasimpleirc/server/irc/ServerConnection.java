package com.duplxey.javasimpleirc.server.irc;

import com.duplxey.javasimpleirc.util.connection.Connection;
import com.duplxey.javasimpleirc.util.connection.Droppable;
import com.duplxey.javasimpleirc.util.packet.Message;
import com.duplxey.javasimpleirc.util.packet.request.Request;
import com.duplxey.javasimpleirc.util.packet.response.Response;
import com.duplxey.javasimpleirc.util.packet.response.ResponseType;

import java.net.Socket;

public class ServerConnection extends Connection implements Droppable {

    private IRCServer ircServer;
    private String username = null;

    public ServerConnection(Socket socket, IRCServer ircServer) {
        super(socket);

        this.ircServer = ircServer;
    }

    @Override
    public void onRequest(Request request) {
        switch (request.getRequestType()) {
            case FETCH_USERNAME:
                respond(new Response(ResponseType.USERNAME, "server"));
                break;
            case FETCH_CLIENTS:
                StringBuilder builder = new StringBuilder();
                ircServer.getClients().values().forEach(client -> {
                    builder.append(client.username);
                    builder.append("@");
                });
                String clientStr = builder.toString();
                if (clientStr.endsWith("@")) clientStr = clientStr.substring(0, clientStr.length()-1);
                respond(new Response(ResponseType.CLIENTS, clientStr));
                break;
            case FETCH_MESSAGE_HISTORY:
                StringBuilder builder1 = new StringBuilder();
                for (Message message : ircServer.getMessageHistory()) {
                    builder1.append(message).append("@");
                }
                respond(new Response(ResponseType.MESSAGE_HISTORY, builder1.toString()));
                break;
            case SEND_MESSAGE:
                ircServer.broadcast(new Response(ResponseType.MESSAGE, username + "@" + request.getContent()));
                break;
            case FETCH_SERVER_DATA:
                respond(new Response(ResponseType.SERVER_DATA, ircServer.getSettingsManager().getConfig().getString("name") + " (" + ircServer.getSettingsManager().getConfig().getString("description") + ")"));
                break;
            case FETCH_SERVER_MOTD:
                respond(new Response(ResponseType.SERVER_MOTD, ircServer.getSettingsManager().getConfig().getString("motd")));
                break;
        }
    }

    @Override
    public void onResponse(Response response) {
        switch (response.getResponseType()) {
            case USERNAME:
                String username = response.getContent();
                if (!ircServer.containsClient(username)) {
                    ircServer.addClient(username, this);
                    this.username = username;
                }
        }
    }

    @Override
    public void onDrop(Exception e) {
        ircServer.removeClient(username);
        destroy();
    }
}
