package com.example.ipwijaevents.controller.api;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ipwijaevents.entity.Event;
import com.example.ipwijaevents.repository.EventRepository;

@RestController
public class EventApiController {

    @Autowired
    private EventRepository eventRepository;

    @GetMapping("/api/events")
    public List<EventCalendarDTO> getEvents() {

        return eventRepository.findAll()
                .stream()
                .map(event -> new EventCalendarDTO(
                event.getId(),
                event.getNamaEvent(),
                event.getTanggal().toString(),
                event.getLokasi()
        ))
                .collect(Collectors.toList());
    }
}
