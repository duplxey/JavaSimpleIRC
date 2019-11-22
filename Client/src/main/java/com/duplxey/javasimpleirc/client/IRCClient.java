package com.duplxey.javasimpleirc.client;

import com.duplxey.javasimpleirc.util.connection.Connection;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class IRCClient {

    private String username;
    private Socket socket;
    private Connection connection;

    public IRCClient() {
        init();
    }

    private void init() {
        System.out.println("Please enter your username:");
        username = new Scanner(System.in).nextLine().trim();
        try {
            socket = new Socket("localhost", 5422);
            connection = new ClientConnection(socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getUsername() {
        return username;
    }

    public Socket getSocket() {
        return socket;
    }

    public Connection getConnection() {
        return connection;
    }
}
