package com.duplxey.javasimpleirc.util.connection;

import com.duplxey.javasimpleirc.util.request.Request;
import com.duplxey.javasimpleirc.util.request.RequestType;
import com.duplxey.javasimpleirc.util.response.Response;
import com.duplxey.javasimpleirc.util.response.ResponseType;

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
                if (splitted[0].equalsIgnoreCase("RES")) {
                    connection.onResponse(new Response(ResponseType.byId(splitted[1]), splitted[2]));
                } else {
                    connection.onRequest(new Request(RequestType.byId(splitted[1]), splitted[2]));
                }
            } catch (IOException e) {
                System.out.println("Connection dropped! :O");
                e.printStackTrace();
                connection.destroy();
            }
        }
    }
}
