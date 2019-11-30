package com.duplxey.javasimpleirc.server.irc;

import com.duplxey.javasimpleirc.util.connection.Connection;
import com.duplxey.javasimpleirc.util.packet.request.Request;
import com.duplxey.javasimpleirc.util.packet.request.RequestType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.Socket;

public class AcceptorThread extends Thread {

    private IRCServer ircServer;
    private Logger logger = LoggerFactory.getLogger(AcceptorThread.class);

    private boolean running = true;

    public AcceptorThread(IRCServer ircServer) {
        this.ircServer = ircServer;
    }

    @Override
    public void run() {
        logger.info("Waiting for clients...");
        while (running) {
            Socket socket = null;
            try {
                socket = ircServer.getServerSocket().accept();
                logger.info("Accepted a connection from " + socket.getRemoteSocketAddress() + ".");
            } catch (IOException e) {
                e.printStackTrace();
            }
            Connection connection = new ServerConnection(socket, ircServer);
            connection.request(new Request(RequestType.FETCH_USERNAME));
        }
    }

    public void cancel() {
        running = false;
    }
}
