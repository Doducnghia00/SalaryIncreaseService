package com.tutorial.Springboot.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="tbtimesheet")
public class Timesheet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)//auto-increment
    //you can also use "sequence"

    private Long employeeId;
    private String employeeName;
    private int workingDays;
    private int unauthorizedLeave;
    private int leaveDay;

   // private int soNgayNghiLe;
    private int overtimeDay;

    private String levelName;
    private int workingTime;
    private int warnings;
    private  int completedProjects; // completed Projects
    private int performanceScore;

    public Timesheet() {
    }


    public Timesheet(String employeeName, int workingDays, int unauthorizedLeave, int leaveDay, int overtimeDay, String levelName,
                     int workingTime, int warnings, int completedProjects, int performanceScore) {
        this.employeeName = employeeName;
        this.workingDays = workingDays;
        this.unauthorizedLeave = unauthorizedLeave;
        this.leaveDay = leaveDay;
        this.overtimeDay = overtimeDay;
        this.levelName = levelName;
        this.workingTime = workingTime;
        this.warnings = warnings;
        this.completedProjects = completedProjects;
        this.performanceScore = performanceScore;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public int getWorkingDays() {
        return workingDays;
    }

    public void setWorkingDays(int workingDays) {
        this.workingDays = workingDays;
    }

    public int getUnauthorizedLeave() {
        return unauthorizedLeave;
    }

    public void setUnauthorizedLeave(int unauthorizedLeave) {
        this.unauthorizedLeave = unauthorizedLeave;
    }

    public int getLeaveDay() {
        return leaveDay;
    }

    public void setLeaveDay(int leaveDay) {
        this.leaveDay = leaveDay;
    }

    public int getOvertimeDay() {
        return overtimeDay;
    }

    public void setOvertimeDay(int overtimeDay) {
        this.overtimeDay = overtimeDay;
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

    @Override
    public String toString() {
        return "EmployeeTimesheet{" +
                "id=" + employeeId +
                ", employeeName='" + employeeName + '\'' +
                ", workingDays=" + workingDays +
                ", unauthorizedLeave=" + unauthorizedLeave +
                ", leaveDay=" + leaveDay +
               // ", soNgayNghiLe=" + soNgayNghiLe +
                ", soNgayTangCa=" + overtimeDay +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Timesheet that = (Timesheet) o;
        return workingDays == that.workingDays && unauthorizedLeave == that.unauthorizedLeave && leaveDay == that.leaveDay && overtimeDay == that.overtimeDay && Objects.equals(employeeId, that.employeeId) && Objects.equals(employeeName, that.employeeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, employeeName, workingDays, unauthorizedLeave, leaveDay, overtimeDay);
    }
}
