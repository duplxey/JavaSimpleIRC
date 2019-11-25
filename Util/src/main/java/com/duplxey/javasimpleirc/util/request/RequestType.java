package com.duplxey.javasimpleirc.util.request;

public enum RequestType {
    CONNECT("CO"),
    DISCONNECT("DI"),

    FETCH_USERNAME("FU"),
    FETCH_CLIENTS("FC"),
    FETCH_MESSAGE_HISTORY("FM"),

    MESSAGE("ME"),
    ;

    private String id;

    RequestType(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public static RequestType byId(String id) {
        for (RequestType requestType : RequestType.values()) {
            if (requestType.getId().equals(id)) {
                return requestType;
            }
        }
        return null;
    }
}
