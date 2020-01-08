package com.duplxey.javasimpleirc.client.gui.view;

import com.duplxey.javasimpleirc.client.gui.ControlledFrame;
import com.duplxey.javasimpleirc.client.gui.Controller;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import java.awt.*;

public class ConnectFrame extends ControlledFrame {

    private JPanel panel;
    private JList serverList;
    private JTextField hostInput;
    private JTextField portInput;
    private JTextField usernameInput;
    private JButton directConnectButton;
    private JButton removeButton;
    private JButton addButton;
    private JButton connectButton;
    private JLabel hostHelpLabel;
    private JLabel portHelpLabel;
    private JLabel usernameHelpLabel;

    public ConnectFrame(Controller controller) {
        super(controller);

        add(panel);
    }

    public JList getServerList() {
        return serverList;
    }

    public JTextField getHostInput() {
        return hostInput;
    }

    public JTextField getPortInput() {
        return portInput;
    }

    public JTextField getUsernameInput() {
        return usernameInput;
    }

    public JButton getDirectConnectButton() {
        return directConnectButton;
    }

    public JButton getRemoveButton() {
        return removeButton;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public JButton getConnectButton() {
        return connectButton;
    }

    public JLabel getHostHelpLabel() {
        return hostHelpLabel;
    }

    public JLabel getPortHelpLabel() {
        return portHelpLabel;
    }

    public JLabel getUsernameHelpLabel() {
        return usernameHelpLabel;
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panel = new JPanel();
        panel.setLayout(new GridLayoutManager(10, 4, new Insets(15, 15, 15, 15), -1, -1));
        final JScrollPane scrollPane1 = new JScrollPane();
        panel.add(scrollPane1, new GridConstraints(0, 0, 9, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        serverList = new JList();
        scrollPane1.setViewportView(serverList);
        final JLabel label1 = new JLabel();
        label1.setText("Host (eg. IP, domain)");
        label1.setVerticalAlignment(0);
        label1.setVerticalTextPosition(0);
        panel.add(label1, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        hostInput = new JTextField();
        panel.add(hostInput, new GridConstraints(4, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Port (default = 5422");
        panel.add(label2, new GridConstraints(3, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        portInput = new JTextField();
        panel.add(portInput, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Username (4-16 characters)");
        panel.add(label3, new GridConstraints(6, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        addButton = new JButton();
        addButton.setText("Add");
        panel.add(addButton, new GridConstraints(9, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        removeButton = new JButton();
        removeButton.setText("Remove");
        panel.add(removeButton, new GridConstraints(9, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        directConnectButton = new JButton();
        directConnectButton.setText("Direct connect");
        panel.add(directConnectButton, new GridConstraints(9, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        connectButton = new JButton();
        connectButton.setText("Connect");
        panel.add(connectButton, new GridConstraints(9, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        usernameInput = new JTextField();
        panel.add(usernameInput, new GridConstraints(7, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        hostHelpLabel = new JLabel();
        hostHelpLabel.setForeground(new Color(-4179669));
        hostHelpLabel.setText("");
        panel.add(hostHelpLabel, new GridConstraints(2, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        portHelpLabel = new JLabel();
        portHelpLabel.setForeground(new Color(-4179669));
        portHelpLabel.setText("");
        panel.add(portHelpLabel, new GridConstraints(5, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        usernameHelpLabel = new JLabel();
        usernameHelpLabel.setForeground(new Color(-4179669));
        usernameHelpLabel.setText("");
        panel.add(usernameHelpLabel, new GridConstraints(8, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel;
    }

}
