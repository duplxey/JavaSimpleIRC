package com.duplxey.javasimpleirc.server.irc;

import com.duplxey.javasimpleirc.util.Message;
import com.duplxey.javasimpleirc.util.connection.Connection;
import com.duplxey.javasimpleirc.util.request.Request;
import com.duplxey.javasimpleirc.util.response.Response;
import com.duplxey.javasimpleirc.util.response.ResponseType;

import java.net.Socket;

public class ServerConnection extends Connection {

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
                respond(new Response(ResponseType.USERNAME, "Server"));
                break;
            case FETCH_CLIENTS:
                respond(new Response(ResponseType.CLIENTS, "many"));
                break;
            case FETCH_MESSAGE_HISTORY:
                respond(new Response(ResponseType.MESSAGE, "meme"));
                break;
            case SEND_MESSAGE:
                ircServer.broadcast(new Response(ResponseType.MESSAGE, request.getContent()));
                ircServer.addMessage(new Message(username, request.getContent()));
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
}
