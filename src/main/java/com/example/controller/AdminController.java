package com.example.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;

import com.example.dto.AdminDashboardDTO;
import com.example.entity.User;
import com.example.service.AdminService;

import jakarta.servlet.http.HttpSession;


@Controller
public class AdminController {
    
    @Autowired
    private AdminService adminService;

    @GetMapping("/admin/home")
    public String adminHome(HttpSession session , Model model){

        User user = (User) session.getAttribute("loggedInUser");

        if(user==null) return "redirect:/login";

        if(!user.getRole().equals("ADMIN")) return "redirect:/login";

        AdminDashboardDTO dashboard = adminService.getDashboardData();

        model.addAttribute("dashboard",dashboard);
        model.addAttribute("admin",user);

        return "admin-home";
    }
}
