package com.example.ipwijaevents.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.ipwijaevents.entity.Certificate;
import com.example.ipwijaevents.entity.User;
import com.example.ipwijaevents.repository.CertificateRepository;
import com.example.ipwijaevents.repository.UserRepository;

@Controller
public class CertificateController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CertificateRepository certificateRepository;

    @GetMapping("/certificate")
    public String certificate(Authentication authentication,
            Model model) {

        User user = userRepository
                .findByEmail(authentication.getName())
                .orElseThrow();

        model.addAttribute("user", user);

        List<Certificate> certificates
                = certificateRepository.findByRegistration_User(user);

        model.addAttribute("certificates", certificates);

        return "certificate";
    }

}
