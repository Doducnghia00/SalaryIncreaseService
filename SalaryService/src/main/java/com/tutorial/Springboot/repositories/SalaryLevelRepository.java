package com.tutorial.Springboot.repositories;


import com.tutorial.Springboot.model.SalaryLevel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SalaryLevelRepository extends JpaRepository<SalaryLevel, Long> {

  //  List<Product> findByProductName(String productName);
    List<SalaryLevel> findByLevelName(String levelName);
}
