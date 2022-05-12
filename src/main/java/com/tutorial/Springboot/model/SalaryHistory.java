package com.tutorial.Springboot.model;

import javax.persistence.*;

@Entity
@Table(name = "salary_history")
public class SalaryHistory {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO) // auto-increment
    private long id;
    private long idEmployees;
    private String nameEmployees;
    private String oldLevel;
    private String newLevel;
    private long idEvent;
    private String dayUp;

    public SalaryHistory(){};

    public SalaryHistory(long idEmployees, String nameEmployees, String oldLevel, String newLevel, long idEvent, String dayUp) {
        this.idEmployees = idEmployees;
        this.nameEmployees = nameEmployees;
        this.oldLevel = oldLevel;
        this.newLevel = newLevel;
        this.idEvent = idEvent;
        this.dayUp = dayUp;
    }

    public long getIdEmployees() {
        return idEmployees;
    }

    public void setIdEmployees(long idEmployees) {
        this.idEmployees = idEmployees;
    }

    public String getNameEmployees() {
        return nameEmployees;
    }

    public void setNameEmployees(String nameEmployees) {
        this.nameEmployees = nameEmployees;
    }

    public String getOldLevel() {
        return oldLevel;
    }

    public void setOldLevel(String oldLevel) {
        this.oldLevel = oldLevel;
    }

    public String getNewLevel() {
        return newLevel;
    }

    public void setNewLevel(String newLevel) {
        this.newLevel = newLevel;
    }

    public long getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(long idEvent) {
        this.idEvent = idEvent;
    }

    public String getDayUp() {
        return dayUp;
    }

    public void setDayUp(String dayUp) {
        this.dayUp = dayUp;
    }
}
