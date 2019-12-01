package com.duplxey.javasimpleirc.server.irc.channel;

import com.duplxey.javasimpleirc.server.irc.ServerConnection;
import com.duplxey.javasimpleirc.util.packet.Message;
import com.duplxey.javasimpleirc.util.packet.PacketManager;
import com.duplxey.javasimpleirc.util.packet.response.Response;
import com.duplxey.javasimpleirc.util.packet.response.ResponseType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;
import java.util.LinkedList;

public class Channel {

    private String name;
    private Logger logger = LoggerFactory.getLogger(Channel.class);

    private LinkedHashMap<String, ServerConnection> clients = new LinkedHashMap<>();
    private LinkedList<Message> messageHistory = new LinkedList<>();

    public Channel(String name) {
        this.name = name;
    }

    public void connect(String username, ServerConnection serverConnection) {
        broadcast(new Response(ResponseType.CHANNEL_OTHER_CONNECT, username));
        clients.put(username, serverConnection);
        logger.debug("Added a new client named '" + username + "' to '" + name + "'.");
    }

    public void disconnect(String username) {
        clients.remove(username);
        broadcast(new Response(ResponseType.CHANNEL_DISCONNECT, username));
        logger.debug("Disconnected a client named '" + username + "' from '" + name + "'.");
    }

    public void broadcast(Response response) {
        for (ServerConnection serverConnection : clients.values()) {
            serverConnection.respond(response);
        }
        if (response.getResponseType() == ResponseType.CHANNEL_MESSAGE || response.getResponseType() == ResponseType.MESSAGE) {
            String[] splitted = response.getContent().split(PacketManager.DELIMITER, 2);
            if (messageHistory.size() >= 10) messageHistory.removeFirst();
            messageHistory.add(new Message(splitted[0], splitted[1]));
        }
    }

    public boolean containsClient(String username) {
        return clients.containsKey(username);
    }

    public ServerConnection getClient(String username) {
        return clients.get(username);
    }

    public String getName() {
        return name;
    }

    public LinkedHashMap<String, ServerConnection> getClients() {
        return clients;
    }

    public LinkedList<Message> getMessageHistory() {
        return messageHistory;
    }
}
