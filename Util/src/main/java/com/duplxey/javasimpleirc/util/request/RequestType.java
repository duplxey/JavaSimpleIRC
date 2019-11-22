package com.duplxey.javasimpleirc.util.request;

public enum RequestType {
    FETCH_USERNAME("FE"),
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
