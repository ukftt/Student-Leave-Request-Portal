package com.example.controller;

import com.example.entity.User;
import com.example.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/signup")
    public String signupPage() {
        return "signup";
    }

    @PostMapping("/register")
    public String registerUser(User user) {
        System.out.println("FORM SUBMITTED");
        userService.registerUser(user);
        return "redirect:/login";
    }

    @PostMapping("/authenticate")
    public String authenticateUser(@RequestParam String email,
            @RequestParam String password) {

        User user = userService.authenticateUser(email,
                password);

        if (user != null) {

            return "redirect:/";
        }

        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logoutUser() {
        return "home";
    }
}