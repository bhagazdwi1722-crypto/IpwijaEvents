package com.example.ipwijaevents.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.ipwijaevents.entity.Notification;
import com.example.ipwijaevents.entity.Registration;
import com.example.ipwijaevents.entity.User;
import com.example.ipwijaevents.repository.NotificationRepository;
import com.example.ipwijaevents.repository.RegistrationRepository;
import com.example.ipwijaevents.repository.UserRepository;
import com.example.ipwijaevents.service.AttendanceService;
import com.example.ipwijaevents.service.CertificateService;
import com.example.ipwijaevents.service.EventService;
import com.example.ipwijaevents.service.RegistrationService;

@Controller
public class DashboardController {

    @Autowired
    private EventService eventService;

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private CertificateService certificateService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    @GetMapping("/dashboard")
    public String dashboard(Authentication authentication, Model model) {

        // User login
        User user = userRepository.findByEmail(authentication.getName())
                .orElseThrow();

        // ===============================
        // Statistik Global
        // ===============================
        model.addAttribute("totalEvents",
                eventService.findAll().size());

        model.addAttribute("totalAttendance",
                attendanceService.findAll().size());

        model.addAttribute("totalCertificates",
                certificateService.findAll().size());

        // ===============================
        // Event yang diikuti mahasiswa
        // ===============================
        List<Registration> registrations
                = registrationRepository.findByUser(user);

        model.addAttribute("registrations", registrations);
        model.addAttribute("upcomingEvents", registrations);

        // ===============================
        // Ringkasan Event
        // ===============================
        model.addAttribute("totalRegistrations",
                registrations.size());

        long totalFinished = registrations.stream()
                .filter(r -> "SELESAI".equalsIgnoreCase(r.getStatus()))
                .count();

        long totalOngoing = registrations.stream()
                .filter(r -> "BERLANGSUNG".equalsIgnoreCase(r.getStatus()))
                .count();

        model.addAttribute("totalFinished", totalFinished);
        model.addAttribute("totalOngoing", totalOngoing);

        // ===============================
        // Persentase kategori
        // ===============================
        long workshop = registrations.stream()
                .filter(r -> r.getEvent().getKategori().equalsIgnoreCase("Workshop"))
                .count();

        long seminar = registrations.stream()
                .filter(r -> r.getEvent().getKategori().equalsIgnoreCase("Seminar"))
                .count();

        long organisasi = registrations.stream()
                .filter(r -> r.getEvent().getKategori().equalsIgnoreCase("Organisasi"))
                .count();

        int workshopPercent = registrations.isEmpty()
                ? 0
                : (int) (workshop * 100 / registrations.size());

        int seminarPercent = registrations.isEmpty()
                ? 0
                : (int) (seminar * 100 / registrations.size());

        int organizationPercent = registrations.isEmpty()
                ? 0
                : (int) (organisasi * 100 / registrations.size());

        model.addAttribute("workshopPercent", workshopPercent);
        model.addAttribute("seminarPercent", seminarPercent);
        model.addAttribute("organizationPercent", organizationPercent);

        // ===============================
        // Notifikasi
        // ===============================
        List<Notification> notifications
                = notificationRepository.findByUserOrderByWaktuDesc(user);

        model.addAttribute("notifications", notifications);

        model.addAttribute("notificationCount",
                notificationRepository.countByUserAndDibacaFalse(user));

        return "dashboard";
    }
}
