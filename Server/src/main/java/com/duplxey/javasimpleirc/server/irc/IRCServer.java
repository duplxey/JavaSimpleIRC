package com.duplxey.javasimpleirc.server.irc;

import com.duplxey.javasimpleirc.server.Main;
import com.duplxey.javasimpleirc.server.config.SettingsManager;
import com.duplxey.javasimpleirc.util.file.FileUtil;
import com.duplxey.javasimpleirc.util.packet.Message;
import com.duplxey.javasimpleirc.util.packet.response.Response;
import com.duplxey.javasimpleirc.util.packet.response.ResponseType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class IRCServer {

    private SettingsManager settingsManager;
    private Logger logger = LoggerFactory.getLogger(IRCServer.class);

    private ServerSocket serverSocket;
    private AcceptorThread acceptorThread;
    private LinkedHashMap<String, ServerConnection> clients = new LinkedHashMap<>();
    private LinkedList<Message> messageHistory = new LinkedList<>();

    public IRCServer(SettingsManager settingsManager) {
        this.settingsManager = settingsManager;

        logger.info(FileUtil.getResourceContent(Main.class.getClassLoader(), "welcome.txt"));
        int port = settingsManager.getConfig().getInt("port");
        try {
            serverSocket = new ServerSocket(port);
            logger.info("Listening on port: " + port);
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
        if (response.getResponseType() == ResponseType.MESSAGE) {
            String[] splitted = response.getContent().split("@", 2);
            addMessage(new Message(splitted[0], splitted[1]));
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

    public void close() {
        for (ServerConnection client : clients.values()) {
            client.destroy();
        }
        acceptorThread.cancel();
    }

    public SettingsManager getSettingsManager() {
        return settingsManager;
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public LinkedHashMap<String, ServerConnection> getClients() {
        return clients;
    }

    public LinkedList<Message> getMessageHistory() {
        return messageHistory;
    }
}
