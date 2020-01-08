package com.duplxey.javasimpleirc.client.gui.controller;

import com.duplxey.javasimpleirc.client.gui.Controller;
import com.duplxey.javasimpleirc.client.gui.view.ConnectFrame;
import com.duplxey.javasimpleirc.client.gui.view.LoginFrame;
import com.duplxey.javasimpleirc.client.irc.IRCClient;
import com.duplxey.javasimpleirc.util.regex.RegexUtil;

import javax.swing.*;

public class ConnectFrameController implements Controller {

    private IRCClient ircClient;

    private ConnectFrame connectFrame;

    public ConnectFrameController(IRCClient ircClient) {
        this.ircClient = ircClient;

        connectFrame = new ConnectFrame(this);
        initComponents();
        initListeners();
    }

    @Override
    public String getTitle() {
        return "JavaSimpleIRC | Connect";
    }

    @Override
    public int getWidth() {
        return 600;
    }

    @Override
    public int getHeight() {
        return 400;
    }

    @Override
    public void initComponents() {
    }

    @Override
    public void initListeners() {
        connectFrame.getConnectButton().addActionListener(l -> {
            connectFrame.getHostHelpLabel().setVisible(false);
            connectFrame.getUsernameHelpLabel().setVisible(false);
            connectFrame.getPortHelpLabel().setVisible(false);

            String host = connectFrame.getHostInput().getText();
            int port = 0;
            String username = connectFrame.getUsernameInput().getText();

            try {
                port = Integer.parseInt(connectFrame.getPortInput().getText());
            } catch (NumberFormatException e) {
                connectFrame.getPortHelpLabel().setText("Port could not be parsed to an integer.");
                connectFrame.getPortHelpLabel().setVisible(true);
            }

            if (!username.matches(RegexUtil.getUsernameRegex())) {
                connectFrame.getUsernameHelpLabel().setText("Username doesn't match regex.");
                connectFrame.getUsernameHelpLabel().setVisible(true);
            }

            // If there are no errors, proceed with the connection
            if (!connectFrame.getHostHelpLabel().isVisible()
                    && !connectFrame.getPortHelpLabel().isVisible()
                    && !connectFrame.getUsernameHelpLabel().isVisible()) {

                if (ircClient.connect(username, host, port)) {
                    System.out.println("Successfully connected.");
                    connectFrame.dispose();

                    ircClient.getGuiManager().openMain();
                } else {
                    System.out.println("Connection failed.");
                    JOptionPane.showMessageDialog(connectFrame, "Could not connect to the requested server.", "Connection failed.", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }

    @Override
    public void show() {
        connectFrame.setVisible(true);
    }

    public ConnectFrame getConnectFrame() {
        return connectFrame;
    }
}
