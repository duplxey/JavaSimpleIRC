package com.duplxey.javasimpleirc.client.gui;

import com.duplxey.javasimpleirc.client.IRCClient;
import com.duplxey.javasimpleirc.client.gui.controller.LoginFrameController;
import com.duplxey.javasimpleirc.client.gui.controller.MainFrameController;

public class GUIManager {

    private IRCClient ircClient;

    private MainFrameController mainFrameController;

    public GUIManager(IRCClient ircClient) {
        this.ircClient = ircClient;

        mainFrameController = new MainFrameController(ircClient);
    }

    public void openLogin() {
        LoginFrameController loginFrameController = new LoginFrameController(ircClient);
        loginFrameController.show();
    }

    public void openMain() {
        mainFrameController.show();
    }

    public MainFrameController getMainFrameController() {
        return mainFrameController;
    }
}
