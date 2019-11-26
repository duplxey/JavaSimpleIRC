package com.duplxey.javasimpleirc.util;

public class Message {

    private String author;
    private String content;
    private long timestamp;

    public Message(String author, String content, long timestamp) {
        this.author = author;
        this.content = content;
        this.timestamp = timestamp;
    }

    public Message(String author, String content) {
        this(author, content, System.currentTimeMillis());
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return getAuthor() + "|" + getContent() + "|" + getTimestamp();
    }
}
