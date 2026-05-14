package com.example.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.LeaveRequestDTO;
import com.example.entity.LeaveRequest;
import com.example.entity.User;
import com.example.repository.LeaveRequestRepository;

@Service
public class LeaveService {

    @Autowired
    private LeaveRequestRepository leaveRequestRepository;





    public void applyLeave(LeaveRequestDTO dto,
                           User user) {

        LeaveRequest leaveRequest =
                new LeaveRequest();




        leaveRequest.setLeaveType(
                dto.getLeaveType());

        leaveRequest.setFromDate(
                dto.getFromDate());

        leaveRequest.setToDate(
                dto.getToDate());

        leaveRequest.setReason(
                dto.getReason());




        leaveRequest.setStatus("PENDING");




        leaveRequest.setAppliedOn(
                LocalDate.now());

        leaveRequest.setUpdatedOn(
                LocalDate.now());




        leaveRequest.setUser(user);




        leaveRequestRepository.save(
                leaveRequest);
    }
}