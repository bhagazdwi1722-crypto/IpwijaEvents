package com.example.ipwijaevents.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.ipwijaevents.entity.Event;
import com.example.ipwijaevents.entity.Notification;
import com.example.ipwijaevents.entity.Registration;
import com.example.ipwijaevents.entity.User;
import com.example.ipwijaevents.repository.EventRepository;
import com.example.ipwijaevents.repository.NotificationRepository;
import com.example.ipwijaevents.repository.RegistrationRepository;
import com.example.ipwijaevents.repository.UserRepository;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    /**
     * Menampilkan halaman registrasi event
     */
    @GetMapping("/registration/{eventId}")
    public String showRegistration(@PathVariable Long eventId,
            Model model,
            Authentication authentication) {

        User user = userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new RuntimeException("User tidak ditemukan"));

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event tidak ditemukan"));

        if (registrationRepository.existsByUserAndEvent(user, event)) {
            return "redirect:/my-events?already";
        }

        model.addAttribute("user", user);
        model.addAttribute("event", event);

        return "registration";
    }

    /**
     * Menyimpan registrasi mahasiswa
     */
    @PostMapping("/registration")
    public String saveRegistration(@RequestParam Long eventId,
            Authentication authentication) {

        User user = userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new RuntimeException("User tidak ditemukan"));

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event tidak ditemukan"));

        if (registrationRepository.existsByUserAndEvent(user, event)) {
            return "redirect:/my-events?already";
        }

        Registration registration = new Registration();

        registration.setUser(user);
        registration.setEvent(event);

        registration.setStatus("MENUNGGU");
        registration.setTanggalDaftar(LocalDateTime.now());

        registrationRepository.save(registration);

        Notification notification = new Notification();

        notification.setUser(user);
        notification.setEvent(event);

        notification.setJudul("Pendaftaran Event Berhasil");

        notification.setPesan(
                "Anda berhasil mendaftar event \""
                + event.getNamaEvent()
                + "\". Status pendaftaran Anda saat ini MENUNGGU."
        );

        notificationRepository.save(notification);

        return "redirect:/my-events?success";
    }
}
