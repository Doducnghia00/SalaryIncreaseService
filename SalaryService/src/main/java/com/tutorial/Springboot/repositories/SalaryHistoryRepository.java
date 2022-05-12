package com.tutorial.Springboot.repositories;

import com.tutorial.Springboot.model.SalaryHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SalaryHistoryRepository extends JpaRepository<SalaryHistory,Long> {
    List<SalaryHistory> findByIdEmployees(Long idEmployees);
    List<SalaryHistory> findByIdEvent(Long idEvent);
}
