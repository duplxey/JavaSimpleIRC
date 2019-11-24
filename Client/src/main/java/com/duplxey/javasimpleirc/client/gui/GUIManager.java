package com.duplxey.javasimpleirc.client.gui;

import com.duplxey.javasimpleirc.client.IRCClient;
import com.duplxey.javasimpleirc.client.gui.controller.LoginFrameController;
import com.duplxey.javasimpleirc.client.gui.controller.MainFrameController;

public class GUIManager {

    private IRCClient ircClient;

    public GUIManager(IRCClient ircClient) {
        this.ircClient = ircClient;
    }

    public void openLogin() {
        LoginFrameController loginFrameController = new LoginFrameController(ircClient);
        loginFrameController.show();
    }

    public void openMain() {
        MainFrameController mainFrameController = new MainFrameController(ircClient);
        mainFrameController.show();
    }
}
