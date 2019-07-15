package com.example.firebasehelloworld;

import java.io.Serializable;

public class Message implements Serializable {

    private String text;
    private String priority;

    @Override
    public String toString() {
        return "Message{" +
                "text='" + text + '\'' +
                ", priority='" + priority + '\'' +
                '}';
    }

    public Message() {
// Default constructor required for calls to snapshot.toObject(Message.class)
    }

    public Message(String text, String priority) {
        this.text = text;
        this.priority = priority;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
}
