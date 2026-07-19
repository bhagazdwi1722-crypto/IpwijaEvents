package com.example.ipwijaevents.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ipwijaevents.entity.Attendance;
import com.example.ipwijaevents.entity.Registration;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    List<Attendance> findByRegistrationIn(List<Registration> registrations);

    Optional<Attendance> findByRegistration(Registration registration);

}
