package com.tutorial.Springboot.repositories;

import com.tutorial.Springboot.model.Timesheet;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EmployeeTimesheetRepository extends JpaRepository<Timesheet, Long> {
    List<Timesheet> findByEmployeeName(String employeeName);

}
