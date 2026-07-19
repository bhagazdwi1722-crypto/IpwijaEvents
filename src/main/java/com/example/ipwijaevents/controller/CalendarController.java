package com.example.ipwijaevents.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.ipwijaevents.repository.EventRepository;

@Controller
public class CalendarController {

    @Autowired
    private EventRepository eventRepository;

    @GetMapping("/calendar")
    public String calendar(Model model) {

        LocalDate sekarang = LocalDate.now();

        LocalDate awalBulan = sekarang.withDayOfMonth(1);
        LocalDate akhirBulan = sekarang.withDayOfMonth(sekarang.lengthOfMonth());

        model.addAttribute(
                "eventsBulanIni",
                eventRepository.findByTanggalBetweenOrderByTanggalAsc(
                        awalBulan,
                        akhirBulan));

        return "calendar";
    }
}
