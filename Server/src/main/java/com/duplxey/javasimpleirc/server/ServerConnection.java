package com.duplxey.javasimpleirc.server;

import com.duplxey.javasimpleirc.util.connection.Connection;
import com.duplxey.javasimpleirc.util.request.Request;
import com.duplxey.javasimpleirc.util.request.RequestType;
import com.duplxey.javasimpleirc.util.response.Response;

import java.net.Socket;

public class ServerConnection extends Connection {

    private IRCServer ircServer;

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
                break;
        }
    }

    @Override
    public void onResponse(Response response) {
        switch (response.getRequestType()) {
            case FETCH_USERNAME:
                String username = response.getContent();
                if (!ircServer.containsClient(username)) {
                    System.out.println("Added a new client '" + username + "'.");
                    ircServer.addClient(username, this);
                }
        }
    }
}
