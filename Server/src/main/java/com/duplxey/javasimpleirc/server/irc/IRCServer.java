package com.duplxey.javasimpleirc.server.irc;

import com.duplxey.javasimpleirc.server.Main;
import com.duplxey.javasimpleirc.util.Message;
import com.duplxey.javasimpleirc.util.data.ResourceUtil;
import com.duplxey.javasimpleirc.util.response.Response;
import com.duplxey.javasimpleirc.util.response.ResponseType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class IRCServer {

    private Logger logger = LoggerFactory.getLogger(IRCServer.class);

    private ServerSocket serverSocket;
    private AcceptorThread acceptorThread;
    private LinkedHashMap<String, ServerConnection> clients = new LinkedHashMap<>();
    private LinkedList<Message> messageHistory = new LinkedList<>();

    public IRCServer() {
        logger.info(ResourceUtil.getResourceContent(Main.class.getClassLoader(), "welcome.txt"));
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
        logger.info("Added a new client named '" + username + "'.");
        clients.put(username, serverConnection);
        broadcast(new Response(ResponseType.CONNECT, username));
    }

    public void removeClient(String username) {
        logger.info("Removed a client named '" + username + "'.");
        clients.remove(username);
        broadcast(new Response(ResponseType.DISCONNECT, username));
    }

    public boolean containsClient(String username) {
        return clients.containsKey(username);
    }

    public ServerConnection getClient(String username) {
        return clients.get(username);
    }

    public void addMessage(Message message) {
        if (messageHistory.size() >= 10) {
            messageHistory.removeFirst();
        }
        messageHistory.add(message);
    }

    public LinkedHashMap<String, ServerConnection> getClients() {
        return clients;
    }

    public LinkedList<Message> getMessageHistory() {
        return messageHistory;
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public AcceptorThread getAcceptorThread() {
        return acceptorThread;
    }
}
