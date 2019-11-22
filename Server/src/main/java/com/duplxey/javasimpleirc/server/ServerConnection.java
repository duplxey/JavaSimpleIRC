package com.duplxey.javasimpleirc.server;

import com.duplxey.javasimpleirc.util.connection.Connection;
import com.duplxey.javasimpleirc.util.request.Request;
import com.duplxey.javasimpleirc.util.request.RequestType;
import com.duplxey.javasimpleirc.util.response.Response;

import java.net.Socket;

public class ServerConnection extends Connection {

    public ServerConnection(Socket socket) {
        super(socket);
    }

    @Override
    public void onRequest(Request request) {
        switch (request.getRequestType()) {
            case FETCH_USERNAME:
                respond(new Response(RequestType.FETCH_USERNAME, "Server"));
        }
    }

    @Override
    public void onResponse(Response response) {
        switch (response.getRequestType()) {
            case FETCH_USERNAME:
                System.out.println("Got an username: " + response.getContent());
        }
    }
}
