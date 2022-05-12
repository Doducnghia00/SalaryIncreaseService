package com.tutorial.Springboot.controller;

import com.tutorial.Springboot.model.*;
import com.tutorial.Springboot.repositories.EventRepository;
import com.tutorial.Springboot.repositories.SalaryHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/task")
public class SalaryIncreaseForEmployees {
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private SalaryHistoryRepository salaryHistoryRepository;

    @PostMapping("")
    ResponseEntity<ResponseObject> insertTask(@RequestBody Event newEvent){
       // boolean check = false;
        long millis=System.currentTimeMillis();
        java.sql.Date today = new java.sql.Date(millis);
        java.sql.Date startDate = newEvent.getStartDate();
        java.sql.Date endDate = newEvent.getEndDate();
        if(startDate.before(today) || endDate.before(startDate)) {
            //check = false;
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("failed","Start date must be after today & End date must be after start date","")
            );
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Add task successfully",eventRepository.save(newEvent))
            );
        }
           /* Event finalEvent = new Event();
            List<Event> events = eventRepository.findAll();
            finalEvent.setEndDate(events.get(0).getEndDate());
            finalEvent.setEventId(events.get(0).getEventId());
            finalEvent.setNameEvent(events.get(0).getNameEvent());
            for (Event event: events){
                java.sql.Date endDate1 = event.getEndDate();
                if(endDate1.after(finalEvent.getEndDate())){
                    finalEvent.setEndDate(endDate1);
                    finalEvent.setEventId(event.getEventId());
                    finalEvent.setNameEvent(event.getNameEvent());
                }
            }
        }*/
      /*  List<Event> events = eventRepository.findAll();
        for (Event event: events){
            java.sql.Date startDate = event.getStartDate();
            java.sql.Date endDate = event.getEndDate();
        }*/
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

    @GetMapping("/{id}")
    ResponseEntity<TaskResponse> getTask(@PathVariable Long id){
        Optional<Event> foundEvent = eventRepository.findById(id);
        List<Event> event = eventRepository.findByEventId(id);
        List<SalaryHistory> salaryHistories = salaryHistoryRepository.findByIdEvent(id);
        if(foundEvent.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new TaskResponse("ok","Query successfully",event.get(0),salaryHistories)
            );
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(
                    new TaskResponse("ok","Cannot find  task with id = " + id,"","")
            );
        }

    }

    @GetMapping("")
    List<Event> getAllEvent(){
        return eventRepository.findAll();
    }


}
