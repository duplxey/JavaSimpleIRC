package com.duplxey.javasimpleirc.client;

import com.duplxey.javasimpleirc.util.connection.Connection;
import com.duplxey.javasimpleirc.util.request.Request;
import com.duplxey.javasimpleirc.util.request.RequestType;
import com.duplxey.javasimpleirc.util.response.Response;
import com.duplxey.javasimpleirc.util.response.ResponseType;

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
                respond(new Response(ResponseType.USERNAME, ircClient.getUsername()));
                break;
        }
    }

    @Override
    public void onResponse(Response response) {
        switch (response.getResponseType()) {
            case CONNECT:
                System.out.println(response.getContent() + " just connected.");
                break;
            case DISCONNECT:
                System.out.println(response.getContent() + " just disconnected.");
                break;
            case MESSAGE:
                String[] splitted = response.getContent().split("@", 2);
                System.out.println(splitted[0] + ": " + splitted[1]);
                break;
        }
    }
}
