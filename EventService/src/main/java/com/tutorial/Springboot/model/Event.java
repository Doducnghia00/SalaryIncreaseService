package com.tutorial.Springboot.model;


import javax.persistence.*;

@Entity
@Table(name = "event")
public class Event {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO) // auto-increment
    private Long eventId;
    private java.sql.Date startDate, endDate;
    @Column(nullable = false, unique = true, length = 300)
    private String nameEvent;
    //private String


    public Event() {
    }

    public Event(Long eventId,
                 java.sql.Date startDate,
                 java.sql.Date endDate,
                 String nameEvent) {
        this.eventId = eventId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.nameEvent = nameEvent;
    }

    public Event(java.sql.Date startDate,
                 java.sql.Date endDate,
                 String nameEvent) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.nameEvent = nameEvent;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public java.sql.Date getStartDate() {
        return startDate;
    }

    public void setStartDate(java.sql.Date startDate) {
        this.startDate = startDate;
    }

    public java.sql.Date getEndDate() {
        return endDate;
    }

    public void setEndDate(java.sql.Date endDate) {
        this.endDate = endDate;
    }

    public String getNameEvent() {
        return nameEvent;
    }

    public void setNameEvent(String nameEvent) {
        this.nameEvent = nameEvent;
    }
}
