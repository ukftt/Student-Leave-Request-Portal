package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;

import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.dto.LeaveRequestDTO;
import com.example.entity.User;
import com.example.service.LeaveService;

import jakarta.servlet.http.HttpSession;

import jakarta.validation.Valid;

@Controller
public class LeaveController {

    @Autowired
    private LeaveService leaveService;

    @GetMapping("/student/apply-leave")
    public String applyLeavePage(Model model,
            HttpSession session) {

        User user = (User) session.getAttribute(
                "loggedInUser");

        if (user == null) {

            return "redirect:/login";
        }

        model.addAttribute(
                "leaveRequest",

                new LeaveRequestDTO());

        model.addAttribute("user", user);
        
        return "apply-leave";
    }

    @PostMapping("/student/apply-leave")
    public String applyLeave(

            @Valid

            @ModelAttribute("leaveRequest") LeaveRequestDTO dto,

            BindingResult result,

            HttpSession session,

            Model model) {

        User user = (User) session.getAttribute(
                "loggedInUser");

        if (user == null) {

            return "redirect:/login";
        }

        if (result.hasErrors()) {

            return "apply-leave";
        }

        leaveService.applyLeave(dto,
                user);

        return "redirect:/student/home";
    }
}