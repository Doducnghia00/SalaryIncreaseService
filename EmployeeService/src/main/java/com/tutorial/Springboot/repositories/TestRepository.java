package com.tutorial.Springboot.repositories;

import com.tutorial.Springboot.model.SalaryLevel;
import com.tutorial.Springboot.model.TestModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestRepository extends JpaRepository<TestModel, Long> {

    List<TestModel> findByIdEmployees(Long id);
}
