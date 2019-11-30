package com.duplxey.javasimpleirc.client.gui.controller;

import com.duplxey.javasimpleirc.client.irc.IRCClient;
import com.duplxey.javasimpleirc.client.gui.Controller;
import com.duplxey.javasimpleirc.client.gui.view.MainFrame;
import com.duplxey.javasimpleirc.util.packet.request.Request;
import com.duplxey.javasimpleirc.util.packet.request.RequestType;
import com.duplxey.javasimpleirc.util.util.DateUtil;

import javax.swing.*;
import java.util.Date;
import java.util.List;

public class MainFrameController implements Controller {

    private IRCClient ircClient;

    private MainFrame mainFrame;
    private JTextPane messagesPane;
    private DefaultListModel<String> userModel = new DefaultListModel<>();

    public MainFrameController(IRCClient ircClient) {
        this.ircClient = ircClient;

        mainFrame = new MainFrame(this);
        messagesPane = mainFrame.getMessagesPane();
        initComponents();
        initListeners();
    }

    @Override
    public String getTitle() {
        return "JavaSimpleIRC";
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
        mainFrame.getSendButton().addActionListener(l -> {
            String message = mainFrame.getMessageInput().getText();
            if (message.length() > 350) {
                JOptionPane.showMessageDialog(mainFrame, "Message is too long!", "Message failed.", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (message.length() < 2) {
                JOptionPane.showMessageDialog(mainFrame, "Message is too short!", "Message failed.", JOptionPane.WARNING_MESSAGE);
                return;
            }
            ircClient.getConnection().request(new Request(RequestType.SEND_MESSAGE, message));
            mainFrame.getMessageInput().setText("");
        });
    }

    public void addMessage(String author, String message, long timestamp) {
        StringBuilder builder = new StringBuilder();
        builder.append(messagesPane.getText());
        if (messagesPane.getText().length() != 0) {
            builder.append("\n");
        }
        builder.append("[" + DateUtil.applyTimeFormat(new Date(timestamp)) + "] " + author + ": " + message);
        messagesPane.setText(builder.toString());
    }

    public void addMessage(String author, String message) {
        addMessage(author, message, System.currentTimeMillis());
    }

    public void setClients(List<String> usernames) {
        for (String username : usernames) {
            userModel.addElement(username);
        }
        mainFrame.getUserList().setModel(userModel);
    }

    public void addClient(String username) {
        userModel.addElement(username + (ircClient.getUsername().equalsIgnoreCase(username) ? " (you)" : ""));
        mainFrame.getUserList().setModel(userModel);
    }

    public void removeClient(String username) {
        userModel.removeElement(username);
        mainFrame.getUserList().setModel(userModel);
    }

    @Override
    public void show() {
        mainFrame.setVisible(true);
    }

    public MainFrame getMainFrame() {
        return mainFrame;
    }
}
