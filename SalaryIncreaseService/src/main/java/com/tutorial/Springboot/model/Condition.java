package com.tutorial.Springboot.model;

import javax.persistence.*;

@Entity
@Table(name = "condition1")
public class Condition {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO) // auto-increment
    private Long id;
    @Column(nullable = false, unique = true, length = 300)
    private String levelName;
    private int workingTime;
    private int warnings;
    private  int completedProjects; // completed Projects
    private int performanceScore; // performance performanceScore

    public Condition(){};
    public Condition(String levelName, int workingTime, int warnings, int completedProjects, int performanceScore) {
        this.levelName = levelName;
        this.workingTime = workingTime;
        this.warnings = warnings;
        this.completedProjects = completedProjects;
        this.performanceScore = performanceScore;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public int getWorkingTime() {
        return workingTime;
    }

    public void setWorkingTime(int workingTime) {
        this.workingTime = workingTime;
    }

    public int getWarnings() {
        return warnings;
    }

    public void setWarnings(int warnings) {
        this.warnings = warnings;
    }

    public int getCompletedProjects() {
        return completedProjects;
    }

    public void setCompletedProjects(int completedProjects) {
        this.completedProjects = completedProjects;
    }

    public int getPerformanceScore() {
        return performanceScore;
    }

    public void setPerformanceScore(int performanceScore) {
        this.performanceScore = performanceScore;
    }
}
