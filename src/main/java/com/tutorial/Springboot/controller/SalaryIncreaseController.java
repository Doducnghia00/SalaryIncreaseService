package com.tutorial.Springboot.controller;

import com.tutorial.Springboot.model.*;
import com.tutorial.Springboot.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping (path = "/salary-increase")
public class SalaryIncreaseController {
    Long eventId = 0L;
    boolean checkCondition = false;
    boolean checkDate = false;
    @Autowired
    private SalaryLevelRepository repository;
    @Autowired
    private ConditionRepository conditionRepository;
    @Autowired
    private TestRepository testRepository;
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private SalaryHistoryRepository salaryHistoryRepository;
    @Autowired
    private EmployeeTimesheetRepository employeeTimesheetRepository;

    @GetMapping("/detail")
    List<SalaryLevel> getAllSalaryScale(){
        return repository.findAll();
    }

    @GetMapping("/{id}")
    ResponseEntity<ResponseObject> findById(@PathVariable Long id){
        Optional<SalaryLevel> foundSalaryScale = repository.findById(id);
        return foundSalaryScale.isPresent() ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok","Query successfully", foundSalaryScale)
                ):
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("false","Cannot find  salary scale with id = " + id, "")
                );
    }

    @PostMapping("/add-salary-level")
    ResponseEntity<ResponseObject> addSalaryLevel(@RequestBody SalaryLevel salaryLevel){
        List<SalaryLevel> foundSalaryLevel = repository.findByLevelName(salaryLevel.getLevelName().trim());
        if(foundSalaryLevel.size() > 0){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("failed","Level name already taken","")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","Insert Salary Level successfully", repository.save(salaryLevel))
        );
    }

    //con de update trung ten loi
  @PutMapping("/{id}")
    ResponseEntity<ResponseObject> updateSalaryLevel(@RequestBody SalaryLevel newSalaryLevel, @PathVariable Long id) {
      SalaryLevel updateSalaryLevel = repository.findById(id)
              .map(salaryLevel -> {
                  salaryLevel.setLevelName(newSalaryLevel.getLevelName());
                  salaryLevel.setSalary(newSalaryLevel.getSalary());
                  return repository.save(salaryLevel);
              }).orElseGet(() -> {
                  newSalaryLevel.setId(id);
                  return repository.save(newSalaryLevel);
              });
      return ResponseEntity.status(HttpStatus.OK).body(
              new ResponseObject("ok", "Update Salary Level Successfully", updateSalaryLevel)
      );
  }

    @DeleteMapping("/{id}")
    ResponseEntity<ResponseObject> deleteSalaryLevel(@PathVariable Long id){
        boolean exists  = repository.existsById(id);
        if(exists){
            repository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok","Delete Salary Level Successfully","")
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed","Cannot find salary level to delete","")
        );
    }


//    CONDITION

    @GetMapping("/condition")
    /*ResponseEntity<ResponseObject> condition(){
        return (conditionRepository.findAll() != null) ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok","Query successfully",conditionRepository.findAll())
                ):
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("false","Cannot read database", "")
                );
    }*/
   List<Condition> getAllCondition(){
        return conditionRepository.findAll();
    }

    @PostMapping("condition")
    ResponseEntity<ResponseObject> addCondition(@RequestBody Condition condition){
        List<Condition> foundCondition = conditionRepository.findByLevelName(condition.getLevelName().trim());
        if(foundCondition.size() > 0){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("failed","Level name already taken","")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","Insert Condition successfully", conditionRepository.save(condition))
        );
    }

    @PutMapping("condition/{id}")
    ResponseEntity<ResponseObject> updateCondition(@RequestBody Condition newCondition, @PathVariable Long id){
        Condition updateCondition = conditionRepository.findById(id)
                .map(condition -> {
                    condition.setLevelName(newCondition.getLevelName());
                    condition.setWorkingTime(newCondition.getWorkingTime());
                    condition.setCompletedProjects(newCondition.getCompletedProjects());
                    condition.setWarnings(newCondition.getWarnings());
                    condition.setPerformanceScore(newCondition.getPerformanceScore());
                    return conditionRepository.save(condition);
                }).orElseGet(() -> {
                    newCondition.setId(id);
                    return  conditionRepository.save(newCondition);
                });
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","Update Condition Successfully", updateCondition)
        );
    }

    @DeleteMapping("condition/{id}")
    ResponseEntity<ResponseObject> deleteCondition(@PathVariable Long id){
        boolean exists  = conditionRepository.existsById(id);
        if(exists){
            conditionRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok","Delete Condition Successfully","")
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed","Cannot find condition to delete","")
        );
    }



    //Xet tang luon theo timesheet
    @PostMapping("")
    ResponseEntity<ResponseObject> checkCondition(@RequestBody Timesheet newTimesheet){
        java.sql.Date dateStart,dateEnd;
       String message;

        long millis=System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        List<Event> events = eventRepository.findAll();
        for (Event event : events) {

             dateStart =  event.getStartDate();
             dateEnd = event.getEndDate();
             eventId = event.getEventId();
            //System.out.println(date + " "  +dateStart + " " + dateEnd);
            if(date.before(dateEnd) && date.after(dateStart)){
                checkDate = true;
                break;
            }else checkDate = false;
        }

        //List<TestModel> testModels = testRepository.findAll();
        List<Condition> condition =  conditionRepository.findByLevelName(newTimesheet.getLevelName());
        if( (newTimesheet.getCompletedProjects() >= condition.get(0).getCompletedProjects())
                && (newTimesheet.getWorkingTime() >= condition.get(0).getWorkingTime())
                &&(newTimesheet.getWarnings() <= condition.get(0).getWarnings())
                && (newTimesheet.getPerformanceScore() >= condition.get(0).getPerformanceScore())
        ) checkCondition = true;
        if(checkCondition && checkDate){
            // Luu lai lich su
            SalaryHistory salaryHistory = new SalaryHistory();
            salaryHistory.setIdEvent(eventId);
            salaryHistory.setDayUp(date.toString());
            salaryHistory.setIdEmployees(newTimesheet.getEmployeeId());
            salaryHistory.setNameEmployees(newTimesheet.getEmployeeName());
            salaryHistory.setOldLevel(newTimesheet.getLevelName());

            String lv = newTimesheet.getLevelName().trim();
            int x = lv.charAt(lv.length()-1) - '0' + 1;

            salaryHistory.setNewLevel("Level " + x ) ;

            salaryHistoryRepository.save(salaryHistory);

            //Update level employee timesheet
            Optional<Object> updateTimesheet = employeeTimesheetRepository.findById(newTimesheet.getEmployeeId())
                    .map(timesheet -> {
                        timesheet.setLevelName("Level " + x);
                        return employeeTimesheetRepository.save(timesheet);
                    });



            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok","Salary increase",salaryHistory)
            );
        }
        else if (!checkCondition && checkDate){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("false","Not enough condition",newTimesheet.getLevelName())
            );
        }
        else if(checkCondition && !checkDate){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("false","Currently not the event time",""  )
            );
        }
        else{
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("false","Just false",""  )
            );
        }
    }

    @GetMapping("/history")
    List<SalaryHistory> getAllHistory(){
        return salaryHistoryRepository.findAll();
    }

    @GetMapping("/test")
    List<TestModel> getAll(){
        return testRepository.findAll();
    }
//    ResponseEntity<ResponseObject> getSomething(){
//       return ResponseEntity.status(HttpStatus.OK).body(
//               new ResponseObject("ok","message",  testRepository.findAll())
//       );
//    }

}
