package com.duplxey.javasimpleirc.util.connection;

import com.duplxey.javasimpleirc.util.packet.PacketManager;
import com.duplxey.javasimpleirc.util.packet.request.Request;
import com.duplxey.javasimpleirc.util.packet.request.RequestType;
import com.duplxey.javasimpleirc.util.packet.response.Response;
import com.duplxey.javasimpleirc.util.packet.response.ResponseType;
import org.apache.commons.lang3.EnumUtils;

import java.io.IOException;

public class ReceiveThread extends Thread {

    private Connection connection;
    private boolean running = true;

    public ReceiveThread(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void run() {
        while (running) {
            try {
                String data = connection.getDataInputStream().readUTF();
                String[] splitted = data.split(PacketManager.DELIMITER, 3);
                if (splitted[0].equalsIgnoreCase(PacketManager.RESPONSE_DELIMITER)) {
                    // If that response type doesn't exist, we can safely ignore it.
                    if (EnumUtils.isValidEnum(ResponseType.class, splitted[1])) {
                        connection.onResponse(new Response(ResponseType.byId(splitted[1]), splitted[2]));
                    }
                } else {
                    // If that request type doesn't exist, we can safely ignore it.
                    if (EnumUtils.isValidEnum(RequestType.class, splitted[1])) {
                        connection.onRequest(new Request(RequestType.byId(splitted[1]), splitted[2]));
                    }
                }
            } catch (IOException e) {
                if (connection instanceof Droppable) {
                    ((Droppable) connection).onDrop(e);
                } else {
                    e.printStackTrace();
                }
            }
        }
    }

    public void cancel() {
        running = false;
    }
}
