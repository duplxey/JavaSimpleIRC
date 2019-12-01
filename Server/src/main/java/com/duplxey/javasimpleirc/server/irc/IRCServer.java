package com.duplxey.javasimpleirc.server.irc;

import com.duplxey.javasimpleirc.server.Main;
import com.duplxey.javasimpleirc.server.irc.channel.Channel;
import com.duplxey.javasimpleirc.server.irc.channel.ChannelManager;
import com.duplxey.javasimpleirc.util.file.FileUtil;
import com.duplxey.javasimpleirc.util.packet.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

public class IRCServer {

    private IRCServerSettings settings;
    private Logger logger = LoggerFactory.getLogger(IRCServer.class);

    private ServerSocket serverSocket;
    private AcceptorThread acceptorThread;
    private ChannelManager channelManager;

    public IRCServer(IRCServerSettings settings) {
        this.settings = settings;

        logger.info(FileUtil.getResourceContent(Main.class.getClassLoader(), "welcome.txt"));
        try {
            serverSocket = new ServerSocket(settings.getPort());
            logger.info("Listening on port: " + settings.getPort());
        } catch (IOException e) {
            e.printStackTrace();
        }
        acceptorThread = new AcceptorThread(this);
        acceptorThread.start();

        channelManager = new ChannelManager(this);
    }

    public void broadcast(Response response) {
        logger.debug("Broadcasting " + response + "!");
        for (ServerConnection client : getClients()) {
            client.respond(response);
        }
    }

    public void close() {
        for (ServerConnection client : getClients()) client.destroy();
        acceptorThread.cancel();
    }

    public ArrayList<ServerConnection> getClients() {
        ArrayList<ServerConnection> clients = new ArrayList<>();
        for (Channel channel : channelManager.getChannels()) {
            clients.addAll(channel.getClients().values());
        }
        return clients;
    }

    public ServerConnection getClient(String username) {
        for (ServerConnection serverConnection : getClients()) {
            if (serverConnection.getUsername() != null && serverConnection.getUsername().equalsIgnoreCase(username)) return serverConnection;
        }
        return null;
    }

    public boolean containsClient(String username) {
        for (ServerConnection serverConnection : getClients()) {
            if (serverConnection.getUsername() != null && serverConnection.getUsername().equalsIgnoreCase(username)) return true;
        }
        return false;
    }

    public IRCServerSettings getSettings() {
        return settings;
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public ChannelManager getChannelManager() {
        return channelManager;
    }
}
