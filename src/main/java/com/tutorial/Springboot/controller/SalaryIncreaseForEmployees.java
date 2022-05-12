package com.tutorial.Springboot.controller;

import com.tutorial.Springboot.model.Event;
import com.tutorial.Springboot.model.ResponseObject;
import com.tutorial.Springboot.repositories.EventRepository;
import com.tutorial.Springboot.repositories.SalaryHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task")
public class SalaryIncreaseForEmployees {
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private SalaryHistoryRepository salaryHistoryRepository;

    @PostMapping("")
    ResponseEntity<ResponseObject> addTask(@RequestBody Event newEvent){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","Test", "")
        );
    }

    @DeleteMapping("/{id}")
    ResponseEntity<ResponseObject> deletedTask(@PathVariable Long id){
        boolean exists  = eventRepository.existsById(id);
        if(exists){
            eventRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok","Delete Task Successfully","")
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed","Cannot find task to delete","")
        );
    }

  /*  @GetMapping("/{id}")
    ResponseEntity<ResponseObject> getTask(@PathVariable Long id){
        boolean exists = eventRepository.existsById(id);
        Event event = new Event();
        if(exists){

        }
    }*/
}
