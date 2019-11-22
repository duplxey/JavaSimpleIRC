package com.duplxey.javasimpleirc.util.request;

public class Request {

    private RequestType requestType;
    private String content;

    public Request(RequestType requestType, String content) {
        this.requestType = requestType;
        this.content = content;
    }

    public Request(RequestType requestType) {
        this(requestType, null);
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "REQ@" + requestType.getId() + "@" + content;
    }
}
