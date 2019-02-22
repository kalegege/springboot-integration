package com.wasu.springboot.integration.entity.system;

public class Message {
    private String from;

    private String to;

    private String message;

    public Message(String from, String to, String message) {
        this.from = from;
        this.to = to;
        this.message = message;
    }

    public Message(String from, String message) {
        this.from = from;
        this.message = message;
    }

    public Message(String message) {
        this.message = message;
        this.from="system";
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
