package com.duplxey.javasimpleirc.util;

import java.util.Date;

public class Message {

    private String author;
    private String content;
    private Date datetime;

    public Message(String author, String content, Date datetime) {
        this.author = author;
        this.content = content;
        this.datetime = datetime;
    }

    public Message(String author, String content) {
        this(author, content, new Date());
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public Date getDatetime() {
        return datetime;
    }
}
