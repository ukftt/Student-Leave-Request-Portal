package com.example.controller;

import com.example.dto.*;
import com.example.entity.*;
import com.example.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpHeaders;


@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/student/home")
    public String studentHome(HttpSession session, Model model){

    User user = (User) session.getAttribute("loggedInUser");

    if(user == null){
    return "redirect:/login";
    }

    StudentDashboardDTO dashboardData = studentService.getDashboardData(user);

    model.addAttribute("dashboard",dashboardData);
    return "student-home";

    }
    
}
