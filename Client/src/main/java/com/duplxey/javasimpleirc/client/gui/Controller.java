package com.duplxey.javasimpleirc.client.gui;

public interface Controller {
    String getTitle();
    int getWidth();
    int getHeight();

    void initComponents();
    void initListeners();

    void show();
}
