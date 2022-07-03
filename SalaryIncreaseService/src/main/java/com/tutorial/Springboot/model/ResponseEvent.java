package com.tutorial.Springboot.model;

public class ResponseEvent {
    private String status;
    private String message;
    private Event data;

    public ResponseEvent() {
    }

    public ResponseEvent(String status, String message, Event data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Event getData() {
        return data;
    }

    public void setData(Event data) {
        this.data = data;
    }
}
