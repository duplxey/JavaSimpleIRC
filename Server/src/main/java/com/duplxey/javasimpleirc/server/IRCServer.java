package com.duplxey.javasimpleirc.server;

import com.duplxey.javasimpleirc.util.response.Response;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.LinkedHashMap;

public class IRCServer {

    private ServerSocket serverSocket;
    private AcceptorThread acceptorThread;
    private LinkedHashMap<String, ServerConnection> clients = new LinkedHashMap<>();

    public IRCServer() {
        try {
            serverSocket = new ServerSocket(5422);
        } catch (IOException e) {
            e.printStackTrace();
        }
        acceptorThread = new AcceptorThread(this);
        acceptorThread.start();
    }

    public void broadcast(Response response) {
        for (ServerConnection serverConnection : clients.values()) {
            serverConnection.respond(response);
        }
    }

    public void addClient(String username, ServerConnection serverConnection) {
        clients.put(username, serverConnection);
    }

    public void removeClient(String username) {
        clients.remove(username);
    }

    public boolean containsClient(String username) {
        return clients.containsKey(username);
    }

    public ServerConnection getClient(String username) {
        return clients.get(username);
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public AcceptorThread getAcceptorThread() {
        return acceptorThread;
    }
}
