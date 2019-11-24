package com.duplxey.javasimpleirc.client.gui;

import com.duplxey.javasimpleirc.client.gui.controller.LoginFrameController;

public class GUIManager {

    public GUIManager() {
        LoginFrameController loginFrameController = new LoginFrameController(this);
        loginFrameController.show();
    }
}
