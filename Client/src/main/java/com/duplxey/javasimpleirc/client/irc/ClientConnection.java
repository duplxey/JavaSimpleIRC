package com.duplxey.javasimpleirc.client.irc;

import com.duplxey.javasimpleirc.client.gui.controller.MainFrameController;
import com.duplxey.javasimpleirc.util.connection.Connection;
import com.duplxey.javasimpleirc.util.connection.Droppable;
import com.duplxey.javasimpleirc.util.packet.request.Request;
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
                break;
        }
    }

    @Override
    public void onResponse(Response response) {
        MainFrameController mfc = ircClient.getGuiManager().getMainController();
        switch (response.getResponseType()) {
            case CONNECT:
                mfc.addClient(response.getContent());
                mfc.addMessage("client", response.getContent() + " just connected.");
                break;
            case DISCONNECT:
                mfc.removeClient(response.getContent());
                mfc.addMessage("client", response.getContent() + " just disconnected.");
                break;
            case CLIENTS:
                mfc.setClients(Arrays.asList(response.getContent().split("@")));
                break;
            case MESSAGE:
                String[] splitted = response.getContent().split("@", 2);
                mfc.addMessage(splitted[0], splitted[1]);
                break;
            case MESSAGE_HISTORY:
                mfc.addMessage("HISTORY", "Retrieving up to 10 messages.");
                if (response.getContent().contains("@")) {
                    for (String message : response.getContent().split("@")) {
                        String[] parts = message.split("\\|");
                        mfc.addMessage(parts[0], parts[1], Long.parseLong(parts[2]));
                    }
                }
                break;
            case SERVER_DATA:
                mfc.getMainFrame().setTitle("JavaSimpleIRC | " + response.getContent());
                break;
            case SERVER_MOTD:
                mfc.addMessage("MOTD", response.getContent());
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
