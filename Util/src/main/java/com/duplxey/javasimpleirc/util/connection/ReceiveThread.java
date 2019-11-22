package com.duplxey.javasimpleirc.util.connection;

import com.duplxey.javasimpleirc.util.request.Request;
import com.duplxey.javasimpleirc.util.request.RequestType;
import com.duplxey.javasimpleirc.util.response.Response;

import java.io.IOException;

public class ReceiveThread extends Thread {

    private Connection connection;

    public ReceiveThread(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void run() {
        while (true) {
            try {
                String message = connection.getDataInputStream().readUTF();
                String[] splitted = message.split("@", 3);
                RequestType type = RequestType.byId(splitted[1]);
                boolean isResponse = splitted[0].equalsIgnoreCase("RES");
                if (isResponse) {
                    connection.onResponse(new Response(type, splitted[2]));
                } else {
                    connection.onRequest(new Request(type, splitted[2]));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
