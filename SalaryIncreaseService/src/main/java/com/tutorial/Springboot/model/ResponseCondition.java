package com.tutorial.Springboot.model;

public class ResponseCondition {
    private String status;
    private String message;
    private Condition data;

    public ResponseCondition() {
    }

    public ResponseCondition(String status, String message, Condition data) {
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

    public Condition getData() {
        return data;
    }

    public void setData(Condition data) {
        this.data = data;
    }
}
