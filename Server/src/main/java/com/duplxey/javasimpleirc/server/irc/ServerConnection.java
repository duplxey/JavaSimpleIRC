package com.duplxey.javasimpleirc.server.irc;

import com.duplxey.javasimpleirc.util.Message;
import com.duplxey.javasimpleirc.util.connection.Connection;
import com.duplxey.javasimpleirc.util.request.Request;
import com.duplxey.javasimpleirc.util.request.RequestType;
import com.duplxey.javasimpleirc.util.response.Response;

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
                respond(new Response(RequestType.FETCH_USERNAME, "Server"));
                break;
            case MESSAGE:
                ircServer.broadcast(new Response(RequestType.MESSAGE, request.getContent()));
                ircServer.addMessage(new Message(username, request.getContent()));
                break;
        }
    }

    @Override
    public void onResponse(Response response) {
        switch (response.getRequestType()) {
            case FETCH_USERNAME:
                String username = response.getContent();
                if (!ircServer.containsClient(username)) {
                    System.out.println("addud");
                    ircServer.addClient(username, this);
                    this.username = username;
                }
        }
    }
}
