package com.tutorial.Springboot.model;

public class ResponseSalary {
    private String status;
    private String message;
    private SalaryLevel data;

    public ResponseSalary() {
    }

    public ResponseSalary(String status, String message, SalaryLevel data) {
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

    public SalaryLevel getData() {
        return data;
    }

    public void setData(SalaryLevel data) {
        this.data = data;
    }
}
