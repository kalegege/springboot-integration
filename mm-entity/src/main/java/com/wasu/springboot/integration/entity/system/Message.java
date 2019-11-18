package com.wasu.springboot.integration.entity.system;

import lombok.Data;

@Data
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
}
