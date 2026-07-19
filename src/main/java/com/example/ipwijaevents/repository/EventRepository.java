package com.example.ipwijaevents.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ipwijaevents.entity.Event;

public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findByTanggalBetweenOrderByTanggalAsc(
            LocalDate awal,
            LocalDate akhir);

}
