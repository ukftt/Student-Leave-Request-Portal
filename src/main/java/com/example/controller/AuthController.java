package com.example.controller;

import com.example.entity.User;
import com.example.service.UserService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
            @RequestParam String password, HttpSession session, Model model) {

        User user = userService.authenticateUser(email,
                password);

        if (user != null) {
            session.setAttribute("loggedInUser", user);
            if(user.getRole().equals("ADMIN")){
                return "redirect:/admin/home";
            }
            else{
                return "redirect:/student/home";
            }
        }

        model.addAttribute("error",
            "Invalid email or password");

        return "login";
    }

    @GetMapping("/logout")
    public String logoutUser(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
    
}