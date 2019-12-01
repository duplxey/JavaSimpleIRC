package com.duplxey.javasimpleirc.client.irc;

import com.duplxey.javasimpleirc.client.gui.controller.MainFrameController;
import com.duplxey.javasimpleirc.util.connection.Connection;
import com.duplxey.javasimpleirc.util.connection.Droppable;
import com.duplxey.javasimpleirc.util.packet.PacketManager;
import com.duplxey.javasimpleirc.util.packet.request.Request;
import com.duplxey.javasimpleirc.util.packet.request.RequestType;
import com.duplxey.javasimpleirc.util.packet.response.Response;
import com.duplxey.javasimpleirc.util.packet.response.ResponseType;

import javax.swing.*;
import java.net.Socket;
import java.util.Arrays;

public class ClientConnection extends Connection implements Droppable {

    private IRCClient ircClient;

    public ClientConnection(Socket socket, IRCClient ircClient) {
        super(socket);

        this.ircClient = ircClient;
    }

    @Override
    public void onRequest(Request request) {
        switch (request.getRequestType()) {
            case FETCH_USERNAME:
                respond(new Response(ResponseType.USERNAME, ircClient.getUsername()));
                request(new Request(RequestType.CHANNEL_CONNECT));
                break;
        }
    }

    @Override
    public void onResponse(Response response) {
        MainFrameController mfc = ircClient.getGuiManager().getMainController();
        switch (response.getResponseType()) {
            case SERVER_DATA:
                mfc.getMainFrame().setTitle("JavaSimpleIRC | " + response.getContent());
                break;
            case SERVER_MOTD:
                mfc.addMessage("MOTD", response.getContent());
                break;
            case CHANNEL_LIST:
                for (String channel : response.getContent().split(PacketManager.DELIMITER)) {
                    mfc.addChannel(channel);
                }
                break;
            case CHANNEL_HISTORY:
                mfc.addMessage("HISTORY", "Retrieving up to 10 messages.");
                for (String message : response.getContent().split(PacketManager.DELIMITER)) {
                    if (message.contains("|")) {
                        String[] parts = message.split("\\|");
                        mfc.addMessage(parts[0], parts[1], Long.parseLong(parts[2]));
                    }
                }
                break;
            case CHANNEL_CLIENTS:
                mfc.setClients(Arrays.asList(response.getContent().split(PacketManager.DELIMITER)));
                break;
            case CHANNEL_CONNECT:
                mfc.clearMessages();
                mfc.clearClients();
                request(new Request(RequestType.FETCH_SERVER_MOTD));
                request(new Request(RequestType.FETCH_CHANNEL_CLIENTS));
                request(new Request(RequestType.FETCH_CHANNEL_HISTORY));
                break;
            case CHANNEL_OTHER_CONNECT:
                mfc.addClient(response.getContent());
                break;
            case CHANNEL_DISCONNECT:
                mfc.removeClient(response.getContent());
                break;
            case CHANNEL_MESSAGE:
            case MESSAGE:
                String[] splitted = response.getContent().split(PacketManager.DELIMITER, 2);
                mfc.addMessage(splitted[0], splitted[1]);
                break;
            case CHANNEL_CREATE:
                mfc.addChannel(response.getContent());
                break;
            case CHANNEL_DELETE:
                mfc.removeChannel(response.getContent());
                break;
        }
    }

    @Override
    public void onDrop(Exception e) {
        MainFrameController mfc = ircClient.getGuiManager().getMainController();
        mfc.getMainFrame().dispose();
        JOptionPane.showMessageDialog(null, "Socket connection has been dropped.", "Connection dropped.", JOptionPane.WARNING_MESSAGE);
        ircClient.getConnection().destroy();
    }
}
