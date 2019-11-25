package com.duplxey.javasimpleirc.server;

import com.duplxey.javasimpleirc.util.connection.Connection;
import com.duplxey.javasimpleirc.util.request.Request;
import com.duplxey.javasimpleirc.util.request.RequestType;

import java.io.IOException;
import java.net.Socket;

public class AcceptorThread extends Thread {

    private IRCServer ircServer;

    public AcceptorThread(IRCServer ircServer) {
        this.ircServer = ircServer;
    }

    @Override
    public void run() {
        System.out.println("Waiting for clients...");
        while (true) {
            Socket socket = null;
            try {
                socket = ircServer.getServerSocket().accept();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Connection connection = new ServerConnection(socket, ircServer);
            connection.request(new Request(RequestType.FETCH_USERNAME));
        }
    }
}
