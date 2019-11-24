package com.duplxey.javasimpleirc.client.gui;

import com.duplxey.javasimpleirc.client.gui.controller.LoginFrameController;
import com.duplxey.javasimpleirc.client.gui.controller.MainFrameController;

public class GUIManager {

    public GUIManager() {
        MainFrameController loginFrameController = new MainFrameController(this);
        loginFrameController.show();
    }
}
