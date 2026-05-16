package com.example.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.*;
import com.example.repository.*;

@Service
public class LeaveRequestService {
    
    @Autowired
    private LeaveRequestRepository leaveRequestRepository;

    public List<LeaveRequest> getUserRequests(User user){
        return leaveRequestRepository.findByUser(user);
    }
    
    public List<LeaveRequest>
    getStudentLeaveHistory(User user) {

        return leaveRequestRepository
                .findByUserOrderByIdDesc(user);
    }
}
