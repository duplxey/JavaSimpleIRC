package com.duplxey.javasimpleirc.util.packet.response;

import com.duplxey.javasimpleirc.util.packet.PacketManager;

public class Response {

    private ResponseType responseType;
    private String content;

    public Response(ResponseType responseType, String content) {
        this.responseType = responseType;
        this.content = content;
    }

    public ResponseType getResponseType() {
        return responseType;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return PacketManager.RESPONSE_DELIMITER + "@" + responseType.getId() + "@" + content;
    }
}
