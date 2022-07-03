package com.tutorial.Springboot.controller;

import com.tutorial.Springboot.model.Event;
import com.tutorial.Springboot.model.ResponseObject;
import com.tutorial.Springboot.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "event")
public class EventController {
    @Autowired
    private EventRepository eventRepository;


    @GetMapping("/event")
    List<Event> getAllEvent(){
        return eventRepository.findAll();
    }
//    public List<Event> getAllEvent(){
//        List<Event> eventList = new ArrayList<>();
//        eventList.add(new Event(1L,"E1"));
//        return eventList;
//    }

    @GetMapping("/{id}")
    ResponseEntity<ResponseObject> getEvent(@PathVariable Long id){
        Optional<Event> foundEvent = eventRepository.findById(id);
        return foundEvent.isPresent() ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok","Query successfully", foundEvent)
                ):
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("false","Cannot find  event with id = " + id, "")
                );
    }

    @GetMapping("/testCall")
    String test(){
        return "HELLO ";
    }
}
