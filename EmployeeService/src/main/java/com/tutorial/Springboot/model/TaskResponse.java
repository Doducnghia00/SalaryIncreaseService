package com.tutorial.Springboot.model;

import java.util.Objects;

public class TaskResponse {
    private String status;
    private String message;
    private Object event;
    private Object salaryHistory;

    public TaskResponse(String status, String message, Object event, Object salaryHistory) {
        this.status = status;
        this.message = message;
        this.event = event;
        this.salaryHistory = salaryHistory;
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

    public Object getEvent() {
        return event;
    }

    public void setEvent(Object event) {
        this.event = event;
    }

    public Object getSalaryHistory() {
        return salaryHistory;
    }

    public void setSalaryHistory(Object salaryHistory) {
        this.salaryHistory = salaryHistory;
    }
}
