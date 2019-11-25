package com.duplxey.javasimpleirc.util.response;

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
        return "RES@" + responseType.getId() + "@" + content;
    }
}
