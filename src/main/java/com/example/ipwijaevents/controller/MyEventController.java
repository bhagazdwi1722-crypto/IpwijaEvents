package com.example.ipwijaevents.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.ipwijaevents.entity.Registration;
import com.example.ipwijaevents.entity.User;
import com.example.ipwijaevents.repository.RegistrationRepository;
import com.example.ipwijaevents.repository.UserRepository;

@Controller
public class MyEventController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RegistrationRepository registrationRepository;

    @GetMapping("/my-events")
    public String myEvents(Authentication authentication,
            Model model) {

        User user = userRepository.findByEmail(authentication.getName())
                .orElseThrow();

        List<Registration> registrations
                = registrationRepository.findByUser(user);

        model.addAttribute("registrations", registrations);

        return "my-events";
    }

}
