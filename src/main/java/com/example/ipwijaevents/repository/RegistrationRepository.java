package com.example.ipwijaevents.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ipwijaevents.entity.Event;
import com.example.ipwijaevents.entity.Registration;
import com.example.ipwijaevents.entity.User;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {

    // ==========================
    // Data Event Saya
    // ==========================
    List<Registration> findByUser(User user);

    boolean existsByUserAndEvent(User user, Event event);

    // ==========================
    // Dashboard
    // ==========================
    // Total event yang didaftarkan
    long countByUser(User user);

    // Total berdasarkan status
    long countByUserAndStatus(User user, String status);

    // Total berdasarkan kategori
    long countByUserAndEvent_Kategori(User user, String kategori);

    // Event yang akan datang
    List<Registration> findByUserAndEvent_TanggalGreaterThanEqual(
            User user,
            LocalDate tanggal);

    // Event yang sudah lewat
    List<Registration> findByUserAndEvent_TanggalLessThan(
            User user,
            LocalDate tanggal);

    // Event hari ini
    List<Registration> findByUserAndEvent_Tanggal(
            User user,
            LocalDate tanggal);

}
