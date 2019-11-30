package com.duplxey.javasimpleirc.client.gui;

import com.duplxey.javasimpleirc.client.gui.controller.LoginFrameController;
import com.duplxey.javasimpleirc.client.gui.controller.MainFrameController;
import com.duplxey.javasimpleirc.client.irc.IRCClient;

public class GUIManager {

    private LoginFrameController loginController;
    private MainFrameController mainController;

    public GUIManager(IRCClient ircClient) {
        loginController = new LoginFrameController(ircClient);
        mainController = new MainFrameController(ircClient);
    }

    public void openLogin() {
        loginController.show();
    }

    public void openMain() {
        mainController.show();
    }

    public LoginFrameController getLoginController() {
        return loginController;
    }

    public MainFrameController getMainController() {
        return mainController;
    }
}
