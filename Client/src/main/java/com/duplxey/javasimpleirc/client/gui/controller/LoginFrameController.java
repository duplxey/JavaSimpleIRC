package com.duplxey.javasimpleirc.client.gui.controller;

import com.duplxey.javasimpleirc.client.IRCClient;
import com.duplxey.javasimpleirc.client.gui.Controller;
import com.duplxey.javasimpleirc.client.gui.view.LoginFrame;
import com.duplxey.javasimpleirc.util.regex.RegexUtil;

import javax.swing.*;

public class LoginFrameController implements Controller {

    private IRCClient ircClient;

    private LoginFrame loginFrame;

    public LoginFrameController(IRCClient ircClient) {
        this.ircClient = ircClient;

        loginFrame = new LoginFrame(this);
        initComponents();
        initListeners();
    }

    @Override
    public String getTitle() {
        return "JavaSimpleIRC | Login";
    }

    @Override
    public int getWidth() {
        return 400;
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
        loginFrame.getConnectButton().addActionListener(l -> {
            loginFrame.getHostHelpLabel().setVisible(false);
            loginFrame.getUsernameHelpLabel().setVisible(false);
            loginFrame.getPortHelpLabel().setVisible(false);

            String host = loginFrame.getHostInput().getText();
            int port = 0;
            String username = loginFrame.getUsernameInput().getText();

            try {
                port = Integer.parseInt(loginFrame.getPortInput().getText());
            } catch (NumberFormatException e) {
                loginFrame.getPortHelpLabel().setText("Port could not be parsed to an integer.");
                loginFrame.getPortHelpLabel().setVisible(true);
            }

            if (!username.matches(RegexUtil.getUsernameRegex())) {
                loginFrame.getUsernameHelpLabel().setText("Username doesn't match regex.");
                loginFrame.getUsernameHelpLabel().setVisible(true);
            }

            // If there are no errors, proceed with the connection
            if (!loginFrame.getHostHelpLabel().isVisible()
                    && !loginFrame.getPortHelpLabel().isVisible()
                    && !loginFrame.getUsernameHelpLabel().isVisible()) {

                if (ircClient.connect(username, host, port)) {
                    System.out.println("Successfully connected.");
                    loginFrame.dispose();

                    ircClient.getGuiManager().openMain();
                } else {
                    System.out.println("Connection failed.");
                    JOptionPane.showMessageDialog(loginFrame, "Could not connect to the requested server.", "Connection failed.", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }

    @Override
    public void show() {
        loginFrame.setVisible(true);
    }

    public LoginFrame getLoginFrame() {
        return loginFrame;
    }
}
