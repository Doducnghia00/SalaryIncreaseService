package com.tutorial.Springboot.model;

import java.sql.Time;

public class ResponseTimesheet {
    private String status;
    private String message;
    private Timesheet data;

    public ResponseTimesheet() {
    }

    public ResponseTimesheet(String status, String message, Timesheet data) {
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

    public Timesheet getData() {
        return data;
    }

    public void setData(Timesheet data) {
        this.data = data;
    }
}
