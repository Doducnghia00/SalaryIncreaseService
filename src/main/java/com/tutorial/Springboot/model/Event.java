package com.tutorial.Springboot.model;


import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "event")
public class Event {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO) // auto-increment
    private Long id;
    private java.sql.Date startDate, endDate;
    @Column(nullable = false, unique = true, length = 300)
    private String nameEvent;


    public Event() {
    }

    public Event(Long id,
                 java.sql.Date startDate,
                 java.sql.Date endDate,
                 String nameEvent) {
        this.id = id;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
