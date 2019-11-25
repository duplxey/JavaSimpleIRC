package com.duplxey.javasimpleirc.util.response;

import com.duplxey.javasimpleirc.util.request.RequestType;

public enum ResponseType {
    CONNECT("CO"),
    DISCONNECT("DI"),
    USERNAME("US"),
    CLIENTS("CL"),
    MESSAGE_HISTORY("MH"),
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
