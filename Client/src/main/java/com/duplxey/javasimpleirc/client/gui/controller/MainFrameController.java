package com.duplxey.javasimpleirc.client.gui.controller;

import com.duplxey.javasimpleirc.client.IRCClient;
import com.duplxey.javasimpleirc.client.gui.Controller;
import com.duplxey.javasimpleirc.client.gui.GUIManager;
import com.duplxey.javasimpleirc.client.gui.view.MainFrame;
import com.duplxey.javasimpleirc.util.request.Request;
import com.duplxey.javasimpleirc.util.request.RequestType;

import javax.swing.*;

public class MainFrameController implements Controller {

    private IRCClient ircClient;

    private MainFrame mainFrame;

    public MainFrameController(IRCClient ircClient) {
        this.ircClient = ircClient;

        mainFrame = new MainFrame(this);
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
        // This just fills up the lists with dummy data
        String[] channelNames = new String[] {
                "Random",
                "School",
                "Programming",
                "Problems",
                "Confession",
                "Fun",
        };
        String[] userNames = new String[] {
                "mikey",
                "johhny404",
                "peter",
                "koolKid",
        };
        String[] messages = new String[] {
                "[19:56] mikey: beep",
                "[19:57] johhny404: boop",
                "[19:57] mikey: ok, thanks",
                "[19:58] koolkid: does anyone know what 2+2 is?",
                "[19:59] peter: i've got a problem, pls pm me",
        };
        DefaultListModel<String> channelModel = new DefaultListModel<>();
        for (String channel : channelNames) {
            channelModel.addElement(channel);
        }
        mainFrame.getChannelList().setModel(channelModel);
        DefaultListModel<String> userModel = new DefaultListModel<>();
        for (String user : userNames) {
            userModel.addElement(user);
        }
        mainFrame.getUserList().setModel(userModel);
        DefaultListModel<String> messageModel = new DefaultListModel<>();
        for (String message : messages) {
            messageModel.addElement(message);
        }
        mainFrame.getMessageList().setModel(messageModel);
    }

    @Override
    public void initListeners() {
        mainFrame.getSendButton().addActionListener(l -> {
            String message = mainFrame.getMessageInput().getText();
            ircClient.getConnection().request(new Request(RequestType.MESSAGE, message));
            mainFrame.getMessageInput().setText("");
        });
    }

    @Override
    public void show() {
        mainFrame.setVisible(true);
    }
}
