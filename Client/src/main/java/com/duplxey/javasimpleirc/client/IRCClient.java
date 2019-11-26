package com.duplxey.javasimpleirc.client;

import com.duplxey.javasimpleirc.client.gui.GUIManager;
import com.duplxey.javasimpleirc.util.connection.Connection;
import com.duplxey.javasimpleirc.util.request.Request;
import com.duplxey.javasimpleirc.util.request.RequestType;

import java.io.IOException;
import java.net.Socket;
import java.util.LinkedList;

public class IRCClient {

    private GUIManager guiManager;

    private String username;
    private Socket socket;
    private Connection connection;

    public IRCClient() {
        guiManager = new GUIManager(this);
        guiManager.openLogin();
    }

    public boolean connect(String username, String host, int port) {
        this.username = username;
        try {
            socket = new Socket(host, port);
            connection = new ClientConnection(socket, this);
            connection.request(new Request(RequestType.FETCH_CLIENTS));
            return true;
        } catch (IOException ignored) {}
        return false;
    }

    public boolean disconnect() {
        return true;
    }

    public GUIManager getGuiManager() {
        return guiManager;
    }

    public String getUsername() {
        return username;
    }

    public Connection getConnection() {
        return connection;
    }
}
