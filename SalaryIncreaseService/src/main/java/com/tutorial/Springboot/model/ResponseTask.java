package com.tutorial.Springboot.model;

import java.sql.Time;

public class ResponseTask {
    private Event event;
    private Timesheet timesheet;
    private Condition condition;
    private Boolean result;

    public ResponseTask() {
    }

    public ResponseTask(Event event, Timesheet timesheet, Condition condition, Boolean result) {
        this.event = event;
        this.timesheet = timesheet;
        this.condition = condition;
        this.result = result;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Timesheet getTimesheet() {
        return timesheet;
    }

    public void setTimesheet(Timesheet timesheet) {
        this.timesheet = timesheet;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }
}
