package com.duplxey.javasimpleirc.client;

import com.duplxey.javasimpleirc.util.connection.Connection;
import com.duplxey.javasimpleirc.util.request.Request;
import com.duplxey.javasimpleirc.util.request.RequestType;

import java.io.IOException;
import java.net.Socket;

public class Main {

    public static void main(String[] args) {
        System.out.println("Client > Beep");
        try {
            Socket socket = new Socket("localhost", 5422);
            Connection connection = new ClientConnection(socket);

            connection.request(new Request(RequestType.FETCH_USERNAME));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
