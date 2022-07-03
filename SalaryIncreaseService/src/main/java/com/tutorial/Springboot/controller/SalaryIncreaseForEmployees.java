package com.tutorial.Springboot.controller;

import com.tutorial.Springboot.model.*;
import com.tutorial.Springboot.repositories.EventRepository;
import com.tutorial.Springboot.repositories.SalaryHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("task")
public class SalaryIncreaseForEmployees {
    Timesheet timesheet;
    Condition condition;

    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private SalaryHistoryRepository salaryHistoryRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;

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


    @GetMapping("/timesheet/{id}")
    Timesheet getTimesheet(@PathVariable Long id){
        Mono<ResponseTimesheet> responseObjectMono = webClientBuilder.build().get()
                .uri("http://employee-service/timesheet/"+id).retrieve()
                .bodyToMono(ResponseTimesheet.class);
        ResponseTimesheet responseObject = responseObjectMono.share().block();
        Timesheet timesheet = (Timesheet) responseObject.getData();
        return timesheet;
    }

    @GetMapping("/condition/{id}")
    Condition getCondition(@PathVariable Long id){
        Mono<ResponseCondition> responseObjectMono = webClientBuilder.build().get()
                .uri("http://salary-service/salary/condition/"+id).retrieve()
                .bodyToMono(ResponseCondition.class);
        ResponseCondition responseObject = responseObjectMono.share().block();
        Condition condition = (Condition) responseObject.getData();
        return condition;
    }

    @GetMapping("/event/{id}")
    Event getEvent(@PathVariable Long id){
        Mono<ResponseEvent> responseObjectMono = webClientBuilder.build().get()
                .uri("http://event-service/event/1").retrieve()
                .bodyToMono(ResponseEvent.class);
        ResponseEvent responseObject = responseObjectMono.share().block();
        Event event = (Event)responseObject.getData();
        return event;
    }

    @GetMapping("/salary/{id}")
    SalaryLevel getSalaryLevel(@PathVariable Long id){

        Mono<ResponseTimesheet> responseObjectMono0 = webClientBuilder.build().get()
                .uri("http://employee-service/timesheet/"+id).retrieve()
                .bodyToMono(ResponseTimesheet.class);
        ResponseTimesheet responseObject0 = responseObjectMono0.share().block();
        timesheet = (Timesheet) responseObject0.getData();
        String lv = timesheet.getLevelName().trim();
        int lv1 = lv.charAt(lv.length()-1) - '0' ;



        Mono<ResponseSalary> responseObjectMono = webClientBuilder.build().get()
                .uri("http://salary-service/salary/" + lv1).retrieve()
                .bodyToMono(ResponseSalary.class);
        ResponseSalary responseObject = responseObjectMono.share().block();
        SalaryLevel salaryLevel = (SalaryLevel) responseObject.getData();
        return salaryLevel;
    }


    //2. có phù hợp đk tăng lương ko ?
    @GetMapping("/check/{id}")
    ResponseEntity<ResponseTask> check(@PathVariable Long id){
        boolean result = true;

        //Check event
        Mono<ResponseEvent> responseObjectMono0 = webClientBuilder.build().get()
                .uri("http://event-service/event/1").retrieve()
                .bodyToMono(ResponseEvent.class);
        ResponseEvent responseObject0 = responseObjectMono0.share().block();
        Event event = (Event)responseObject0.getData();

        java.sql.Date dateStart,dateEnd;
        dateStart =  event.getStartDate();
        dateEnd = event.getEndDate();
        long millis=System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);


        Mono<ResponseTimesheet> responseObjectMono = webClientBuilder.build().get()
                .uri("http://employee-service/timesheet/"+id).retrieve()
                .bodyToMono(ResponseTimesheet.class);
        ResponseTimesheet responseObject = responseObjectMono.share().block();
        timesheet = (Timesheet) responseObject.getData();


        //láy sô lv của bảng lương hiện tại
        String lv = timesheet.getLevelName().trim();
        int lv1 = lv.charAt(lv.length()-1) - '0' +1;

        Mono<ResponseCondition> responseObjectMono1 = webClientBuilder.build().get()
                .uri("http://salary-service/salary/condition/"+lv1).retrieve()
                .bodyToMono(ResponseCondition.class);
        ResponseCondition responseObject1 = responseObjectMono1.share().block();
        condition = (Condition) responseObject1.getData();

        if(date.before(dateEnd) && date.after(dateStart)){




            if( (timesheet.getWarnings() <= condition.getWarnings())
                    && (timesheet.getWorkingDays() >= condition.getWorkingTime())
                    && (timesheet.getWorkingTime() >= condition.getCompletedProjects())
                    && (timesheet.getCompletedProjects() >= condition.getPerformanceScore()) ){
                result = false;
            }else result = true;
        }



        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseTask(event,timesheet,condition,result)
        );


    }

    @GetMapping("/test")
    String test(){
        String s = webClientBuilder.build().get()
                .uri("http://test-service/test").retrieve()
                .bodyToMono(String.class) // convert x to instance of Timesheet
                .block();

        return s;
    }

}
