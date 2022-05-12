package com.tutorial.Springboot.controller;


import com.tutorial.Springboot.model.ResponseObject;
import com.tutorial.Springboot.model.Timesheet;
import com.tutorial.Springboot.repositories.EmployeeTimesheetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/Timesheet")
public class EmployeeController {
    @Autowired
    private EmployeeTimesheetRepository repository;
    @GetMapping("")
        //this request is: http://localhost:9090/Timesheet
    List<Timesheet> getALlEmployeeTimesheet(){
        return repository.findAll(); // where is data?
    }

    //Get detail product
    @GetMapping("/{id}")
    //Let's return an object with: data, message, status
    ResponseEntity<ResponseObject> findById(@PathVariable Long id){
        Optional<Timesheet> foundEmployeeTimesheet = repository.findById(id);
        return foundEmployeeTimesheet.isPresent() ?
                ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "Query employee timesheet successfully", foundEmployeeTimesheet))
                :ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("false","Cannot find employee timesheet with id = "+id,""));

    }

    //insert new Product with POST method
    //Postman : Raw, JSON
    @PostMapping("/insert")
    ResponseEntity<ResponseObject> insertEmployee(@RequestBody Timesheet newProduct){
        //2 products must not have the same name!
        List<Timesheet> foundProducts = repository.findByEmployeeName(newProduct.getEmployeeName().trim());
        if(foundProducts.size()>0){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("failed", "Employee name already taken", "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Insert Employee timesheet successfully", repository.save(newProduct))
        );
    }

    //update, upsert = update if found, otherwise insert
    @PutMapping("/{id}")
    ResponseEntity<ResponseObject> updateTimesheet(@RequestBody Timesheet newTimesheet, @PathVariable Long id){
        Timesheet updateTimesheet = repository.findById(id)
                .map(timesheet -> {
                    timesheet.setEmployeeName(newTimesheet.getEmployeeName());
                    timesheet.setWorkingDays(newTimesheet.getWorkingDays());
                    timesheet.setLeaveDay(newTimesheet.getLeaveDay());
                    timesheet.setUnauthorizedLeave(newTimesheet.getUnauthorizedLeave());
                    timesheet.setOvertimeDay(newTimesheet.getOvertimeDay());
                    timesheet.setLevelName(newTimesheet.getLevelName());
                    timesheet.setWorkingTime(newTimesheet.getWorkingTime());
                    timesheet.setWarnings(newTimesheet.getWarnings());
                    timesheet.setCompletedProjects(newTimesheet.getCompletedProjects());
                    timesheet.setPerformanceScore(newTimesheet.getPerformanceScore());
                    //timesheet.setSoNgayNghiLe(newTimesheet.getSoNgayNghiLe());
                    return repository.save(timesheet);
                }).orElseGet(() ->{
                    newTimesheet.setEmployeeId(id);
                    return repository.save(newTimesheet);
                });
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","Update Product successfully", updateTimesheet)
        );
    }
    //Delete a Product => DELETE method
    @DeleteMapping("/{id}")
    ResponseEntity<ResponseObject> deleteTimesheet(@PathVariable Long id){
        boolean exists = repository.existsById(id);
        if(exists){
            repository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok","Delete employee timesheet successfully", "")
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed","Cannot find employee timesheet to delete", "")
        );
    }

}
