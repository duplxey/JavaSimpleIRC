package com.duplxey.javasimpleirc.server.commander.command.commands;

import com.duplxey.javasimpleirc.server.commander.CMessage;
import com.duplxey.javasimpleirc.server.commander.Commander;
import com.duplxey.javasimpleirc.server.commander.command.Command;
import com.duplxey.javasimpleirc.server.irc.IRCServer;
import com.duplxey.javasimpleirc.server.irc.channel.Channel;
import com.duplxey.javasimpleirc.server.irc.channel.ChannelManager;
import com.duplxey.javasimpleirc.util.regex.RegexUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ChannelCommand extends Command {

    private ChannelManager channelManager;

    private ArrayList<String> addOperations;
    private ArrayList<String> removeOperations;
    private ArrayList<String> moveOperations;

    public ChannelCommand(IRCServer ircServer) {
        super("channel", "Channel manager.", new String[] {"channels", "c"}, "<command> | <create|delete|move> <name> | <index>");

        this.channelManager = ircServer.getChannelManager();

        addOperations = new ArrayList<>(Arrays.asList("create", "add", "register"));
        removeOperations = new ArrayList<>(Arrays.asList("delete", "remove", "unregister"));
        moveOperations = new ArrayList<>(Collections.singletonList("move"));
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 0) {
            Commander.getLogger().info("Currently registered channels:");
            Commander.getLogger().info("+--------------+---------------------------------------------+");
            Commander.getLogger().info("| Index        | Channel name                                |");
            Commander.getLogger().info("+--------------+---------------------------------------------+");
            String rowFormat = "| %-12d | %-43s |";
            int i = 0;
            for (Channel channel : channelManager.getChannels()) {
                Commander.getLogger().info(String.format(rowFormat, i, channel.getName()));
                i++;
            }
            Commander.getLogger().info("+--------------+---------------------------------------------+");
            return;
        }
        if (args.length == 2) {
            String operation = args[0];
            String channelName = args[1];
            if (!channelName.matches(RegexUtil.getChannelRegex())) {
                Commander.getLogger().info(CMessage.REGEX_NO_MATCH.getText());
                return;
            }
            if (addOperations.contains(operation)) {
                if (channelManager.existsChannel(channelName)) {
                    Commander.getLogger().info(CMessage.CHANNEL_EXISTS.getText());
                    return;
                }
                channelManager.addChannel(channelName);
                Commander.getLogger().info("Created a channel named '" + channelName + "'.");
                return;
            } else if (removeOperations.contains(operation)) {
                if (!channelManager.existsChannel(channelName)) {
                    Commander.getLogger().info(CMessage.CHANNEL_DOESNT_EXIST.getText());
                    return;
                }
                channelManager.removeChannel(channelName);
                Commander.getLogger().info("Removed a channel named '" + channelName + "'.");
                return;
            }
        }
        if (args.length == 3) {
            String operation = args[0];
            String channelName = args[1];
            if (moveOperations.contains(operation)) {
                int index = 0;
                try {
                    index = Integer.parseInt(args[2]);
                } catch (NumberFormatException e) {
                    Commander.getLogger().info(CMessage.NOT_INTEGER.getText());
                    return;
                }
                if (!channelManager.existsChannel(channelName)) {
                    Commander.getLogger().info(CMessage.CHANNEL_DOESNT_EXIST.getText());
                    return;
                }
                channelManager.moveChannel(channelName, index);
                Commander.getLogger().info("Moved a channel named '" + channelName + "' to " + index + ".");
                return;
            }
        }
        Commander.getLogger().info(CMessage.WRONG_SYNTAX.getText() + getSyntax());
    }
}
