package com.duplxey.javasimpleirc.util.request;

public enum RequestType {
    FETCH_SERVER_DATA("FD"),
    FETCH_USERNAME("FU"),
    FETCH_CLIENTS("FC"),
    FETCH_MESSAGE_HISTORY("FM"),
    SEND_MESSAGE("SM"),
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
