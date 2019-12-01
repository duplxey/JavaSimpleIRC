package com.duplxey.javasimpleirc.util.packet.request;

public enum RequestType {
    FETCH_SERVER_DATA("FSD"),
    FETCH_SERVER_MOTD("FSM"),

    FETCH_CHANNEL_LIST("FCL"),
    FETCH_CHANNEL_HISTORY("FMH"),
    FETCH_CHANNEL_CLIENTS("FCC"),
    CHANNEL_CONNECT("CC"),
    CHANNEL_SEND_MESSAGE("CSM"),

    FETCH_USERNAME("FU"),

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
