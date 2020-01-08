package com.duplxey.javasimpleirc.client.gui;

import com.duplxey.javasimpleirc.client.gui.controller.ConnectFrameController;
import com.duplxey.javasimpleirc.client.gui.controller.LoginFrameController;
import com.duplxey.javasimpleirc.client.gui.controller.MainFrameController;
import com.duplxey.javasimpleirc.client.gui.view.ConnectFrame;
import com.duplxey.javasimpleirc.client.irc.IRCClient;

public class GUIManager {

    private ConnectFrameController connectController;
    private MainFrameController mainController;

    public GUIManager(IRCClient ircClient) {
        connectController = new ConnectFrameController(ircClient);
        mainController = new MainFrameController(ircClient);
    }

    public void openLogin() {
        connectController.show();
    }

    public void openMain() {
        mainController.show();
    }

    public ConnectFrameController getLoginController() {
        return connectController;
    }

    public MainFrameController getMainController() {
        return mainController;
    }
}
