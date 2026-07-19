package com.example.ipwijaevents.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.ipwijaevents.entity.Attendance;
import com.example.ipwijaevents.entity.Certificate;
import com.example.ipwijaevents.entity.Registration;
import com.example.ipwijaevents.entity.User;
import com.example.ipwijaevents.repository.AttendanceRepository;
import com.example.ipwijaevents.repository.CertificateRepository;
import com.example.ipwijaevents.repository.RegistrationRepository;
import com.example.ipwijaevents.repository.UserRepository;

@Controller
public class AttendanceController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private CertificateRepository certificateRepository;

    @GetMapping("/attendance")
    public String attendance(Authentication authentication, Model model) {

        User user = userRepository
                .findByEmail(authentication.getName())
                .orElseThrow();

        model.addAttribute("user", user);

        List<Registration> registrations
                = registrationRepository.findByUser(user);

        List<Attendance> attendances
                = attendanceRepository.findByRegistrationIn(registrations);

        model.addAttribute("attendances", attendances);

        Map<Long, Boolean> attendanceStatus = new HashMap<>();

        for (Attendance attendance : attendances) {
            attendanceStatus.put(
                    attendance.getRegistration().getId(),
                    attendance.isHadir());
        }

        model.addAttribute("attendanceStatus", attendanceStatus);

        List<Registration> pendingRegistrations = registrations.stream()
                .filter(reg -> !attendanceStatus.getOrDefault(reg.getId(), false))
                .collect(Collectors.toList());

        model.addAttribute("pendingRegistrations", pendingRegistrations);

        return "attendance";
    }

    @PostMapping("/attendance/{id}")
    public String absen(@PathVariable Long id) {

        Registration registration = registrationRepository
                .findById(id)
                .orElseThrow();

        Attendance attendance = attendanceRepository
                .findByRegistration(registration)
                .orElse(null);

        if (attendance == null) {
            attendance = new Attendance();
            attendance.setRegistration(registration);
        }

        attendance.setHadir(true);
        attendanceRepository.save(attendance);

        registration.setStatus("SELESAI");
        registrationRepository.save(registration);

        if (!certificateRepository.existsByRegistrationId(registration.getId())) {

            Certificate certificate = new Certificate();

            certificate.setRegistration(registration);
            certificate.setNomorSertifikat("CERT-" + System.currentTimeMillis());
            certificate.setTanggalTerbit(LocalDate.now());
            certificate.setTersedia(true);

            certificateRepository.save(certificate);
        }

        return "redirect:/attendance";
    }
}
