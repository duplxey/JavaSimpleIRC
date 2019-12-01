package com.duplxey.javasimpleirc.client.gui;

import javax.swing.*;
import java.awt.*;

public class ControlledFrame extends JFrame {

    public ControlledFrame(Controller controller) {
        setTitle(controller.getTitle());
        setSize(new Dimension(controller.getWidth(), controller.getHeight()));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }
}
