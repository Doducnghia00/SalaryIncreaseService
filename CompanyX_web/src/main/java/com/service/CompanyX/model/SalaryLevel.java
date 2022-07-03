package com.service.CompanyX.model;

import javax.persistence.*;

@Entity
@Table(name = "salary_level")
public class SalaryLevel {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO) // auto-increment
    private Long id;
    @Column(nullable = false, unique = true, length = 300)
    private String levelName;
    private Double salary;

    public SalaryLevel(){}

    public SalaryLevel(Long id, String levelName, Double salary) {
        this.id = id;
        this.levelName = levelName;
        this.salary = salary;
    }

    public SalaryLevel(String name, Double salary) {

        this.levelName = name;
        this.salary = salary;
    }

    public Long getId() {
        return id;
    }

    public String getLevelName() {
        return levelName;
    }

    public Double getSalary() {
        return salary;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "SalaryLevel{" +
                "id=" + id +
                ", levelName='" + levelName + '\'' +
                ", salary=" + salary +
                '}';
    }
}
