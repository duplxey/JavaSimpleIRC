package com.duplxey.javasimpleirc.client;

import com.duplxey.javasimpleirc.util.connection.Connection;
import com.duplxey.javasimpleirc.util.request.Request;
import com.duplxey.javasimpleirc.util.request.RequestType;
import com.duplxey.javasimpleirc.util.response.Response;

import java.net.Socket;

public class ClientConnection extends Connection {

    private IRCClient ircClient;

    public ClientConnection(Socket socket, IRCClient ircClient) {
        super(socket);

        this.ircClient = ircClient;
    }

    @Override
    public void onRequest(Request request) {
        switch (request.getRequestType()) {
            case FETCH_USERNAME:
                respond(new Response(RequestType.FETCH_USERNAME, ircClient.getUsername()));
        }
    }

    @Override
    public void onResponse(Response response) {
        switch (response.getRequestType()) {
            case FETCH_USERNAME:
                System.out.println("Got an username: " + response.getContent());
                break;
            case MESSAGE:
                System.out.println("Received: " + response.getContent());
                break;
        }
    }
}
