package com.duplxey.javasimpleirc.server;

import com.duplxey.javasimpleirc.util.connection.Connection;
import com.duplxey.javasimpleirc.util.request.Request;
import com.duplxey.javasimpleirc.util.request.RequestType;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) {
        System.out.println("Server > Beep");

        try {
            ServerSocket serverSocket = new ServerSocket(5422);
            while (true) {
                Socket socket = serverSocket.accept();
                Connection connection = new ServerConnection(socket);
                connection.request(new Request(RequestType.FETCH_USERNAME));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
