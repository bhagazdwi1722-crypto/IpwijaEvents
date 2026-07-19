package com.example.ipwijaevents.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ipwijaevents.entity.Attendance;
import com.example.ipwijaevents.repository.AttendanceRepository;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    public Attendance save(Attendance attendance) {
        return attendanceRepository.save(attendance);
    }

    public List<Attendance> findAll() {
        return attendanceRepository.findAll();
    }

    public Optional<Attendance> findById(Long id) {
        return attendanceRepository.findById(id);
    }

    public void delete(Long id) {
        attendanceRepository.deleteById(id);
    }

}
