package com.tutorial.Springboot.repositories;

import com.tutorial.Springboot.model.Condition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConditionRepository extends JpaRepository<Condition,Long> {
    //List<Product> findByProductName(String productName);
    List<Condition> findByLevelName(String levelName);
}
