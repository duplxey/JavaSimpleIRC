package com.duplxey.javasimpleirc.client.gui.controller;

import com.duplxey.javasimpleirc.client.gui.Controller;
import com.duplxey.javasimpleirc.client.gui.view.MainFrame;
import com.duplxey.javasimpleirc.client.irc.IRCClient;
import com.duplxey.javasimpleirc.util.packet.request.Request;
import com.duplxey.javasimpleirc.util.packet.request.RequestType;
import com.duplxey.javasimpleirc.util.util.DateUtil;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Date;
import java.util.List;

public class MainFrameController implements Controller {

    private IRCClient ircClient;

    private MainFrame mainFrame;
    private JTextPane messagesPane;
    private DefaultListModel<String> userModel = new DefaultListModel<>();
    private DefaultListModel<String> channelModel = new DefaultListModel<>();

    public MainFrameController(IRCClient ircClient) {
        this.ircClient = ircClient;

        mainFrame = new MainFrame(this);
        messagesPane = mainFrame.getMessagesPane();
        initComponents();
        initListeners();
    }

    @Override
    public String getTitle() {
        return "JavaSimpleIRC | %data%";
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
            sendMessage();
        });
        mainFrame.getMessageInput().addKeyListener(new KeyListener() {
            @Override public void keyTyped(KeyEvent e) {}
            @Override public void keyPressed(KeyEvent e) { }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    sendMessage();
                }
            }
        });
        mainFrame.getChannelList().addMouseListener(new MouseListener() {
            @Override public void mouseClicked(MouseEvent e) {}
            @Override public void mousePressed(MouseEvent e) {}
            @Override public void mouseEntered(MouseEvent e) {}
            @Override public void mouseExited(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {
                String channelName = mainFrame.getChannelList().getSelectedValue().toString().replaceAll("#", "");
                if (ircClient.getConnection().getChannelName() != null && ircClient.getConnection().getChannelName().equalsIgnoreCase(channelName)) return;
                ircClient.getConnection().request(new Request(RequestType.CHANNEL_CONNECT, channelName));
            }
        });
    }

    private void sendMessage() {
        String message = mainFrame.getMessageInput().getText();
        if (message.length() > 350) {
            JOptionPane.showMessageDialog(mainFrame, "Message is too long!", "Message failed.", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (message.length() == 0) return;
        ircClient.getConnection().request(new Request(RequestType.CHANNEL_SEND_MESSAGE, message));
        mainFrame.getMessageInput().setText("");
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

    public void clearMessages() {
        mainFrame.getMessagesPane().setText("");
    }

    public void addChannel(String channelName) {
        if (mainFrame.getChannelList().isSelectionEmpty()) {
            mainFrame.getChannelList().setSelectedIndex(0);
        }
        channelModel.addElement("#" + channelName);
        mainFrame.getChannelList().setModel(channelModel);
    }

    public void removeChannel(String channelName) {
        mainFrame.getChannelList().setSelectedIndex(0);
        channelModel.removeElement("#" + channelName);
        mainFrame.getChannelList().setModel(channelModel);
    }

    public void setClients(List<String> usernames) {
        userModel.clear();
        for (String username : usernames) {
            addClient(username);
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

    public void clearClients() {
        userModel = new DefaultListModel<>();
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
