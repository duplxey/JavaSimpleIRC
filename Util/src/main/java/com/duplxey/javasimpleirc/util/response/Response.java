package com.duplxey.javasimpleirc.util.response;

import com.duplxey.javasimpleirc.util.request.RequestType;

public class Response {

    private RequestType requestType;
    private String content;

    public Response(RequestType requestType, String content) {
        this.requestType = requestType;
        this.content = content;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "RES@" + requestType.getId() + "@" + content;
    }
}
