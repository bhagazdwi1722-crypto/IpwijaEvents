package com.example.ipwijaevents.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.ipwijaevents.entity.User;
import com.example.ipwijaevents.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    // Halaman awal langsung ke login
    @GetMapping("/")
    public String home() {
        return "redirect:/login";
    }

    // Halaman login
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // Halaman register
    @GetMapping("/register")
    public String register(Model model) {

        model.addAttribute("user", new User());

        return "register";
    }

    // Simpan user baru
    @PostMapping("/register")
    public String saveUser(
            @ModelAttribute User user) {

        user.setRole("MAHASISWA");

        userService.save(user);

        return "redirect:/login";
    }

    // Fungsi Logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {

        // Menghapus data login dari session
        session.invalidate();

        // Kembali ke halaman login
        return "redirect:/login";
    }

}
