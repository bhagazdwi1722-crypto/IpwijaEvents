package com.example.ipwijaevents.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.ipwijaevents.entity.User;
import com.example.ipwijaevents.repository.UserRepository;

@Controller
public class SettingsController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/settings")
    public String settings(Authentication authentication, Model model) {

        User currentUser = userRepository
                .findByEmail(authentication.getName())
                .orElseThrow();

        model.addAttribute("currentUser", currentUser);

        return "settings";
    }

}
