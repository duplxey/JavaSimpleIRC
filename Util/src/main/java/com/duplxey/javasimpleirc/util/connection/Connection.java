package com.duplxey.javasimpleirc.util.connection;

import com.duplxey.javasimpleirc.util.request.Request;
import com.duplxey.javasimpleirc.util.response.Response;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public abstract class Connection {

    private Socket socket;
    private long establishedStamp;

    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private ReceiveThread receiveThread;

    public Connection(Socket socket) {
        this.socket = socket;

        init();
    }

    private void init() {
        establishedStamp = System.currentTimeMillis();
        try {
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        receiveThread = new ReceiveThread(this);
        receiveThread.start();
    }

    public void request(Request request) {
        try {
            dataOutputStream.writeUTF(request.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public abstract void onRequest(Request request);

    public void respond(Response response) {
        try {
            dataOutputStream.writeUTF(response.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public abstract void onResponse(Response response);

    public void destroy() {
        try {
            receiveThread.destroy();
            dataInputStream.close();
            dataOutputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public long aliveSince() {
        return System.currentTimeMillis() - establishedStamp;
    }

    public Socket getSocket() {
        return socket;
    }

    public DataInputStream getDataInputStream() {
        return dataInputStream;
    }

    public DataOutputStream getDataOutputStream() {
        return dataOutputStream;
    }
}
