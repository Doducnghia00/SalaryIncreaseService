package com.tutorial.Springboot.repositories;

import com.tutorial.Springboot.model.Event;
import com.tutorial.Springboot.model.Timesheet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByNameEvent(String nameEvent);
    List<Event> findByEventId(Long eventId);
}
