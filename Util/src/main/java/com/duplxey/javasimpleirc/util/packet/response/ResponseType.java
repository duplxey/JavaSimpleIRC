package com.duplxey.javasimpleirc.util.packet.response;

public enum ResponseType {
    SERVER_DATA("SD"),
    SERVER_MOTD("SM"),

    CHANNEL_LIST("CCL"),
    CHANNEL_HISTORY("CH"),
    CHANNEL_CLIENTS("CL"),

    CHANNEL_CONNECT("CC"),
    CHANNEL_OTHER_CONNECT("COC"),
    CHANNEL_DISCONNECT("DI"),
    CHANNEL_MESSAGE("CM"),
    CHANNEL_CREATE("CCE"),
    CHANNEL_DELETE("CCD"),

    USERNAME("US"),

    MESSAGE("ME"),
    ;

    private String id;

    ResponseType(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public static ResponseType byId(String id) {
        for (ResponseType responseType : ResponseType.values()) {
            if (responseType.getId().equals(id)) {
                return responseType;
            }
        }
        return null;
    }
}
