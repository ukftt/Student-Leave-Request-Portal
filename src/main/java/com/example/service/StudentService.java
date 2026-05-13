package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.StudentDashboardDTO;
import com.example.entity.LeaveRequest;
import com.example.entity.User;
import com.example.repository.LeaveRequestRepository;

@Service
public class StudentService {

    @Autowired
    private LeaveRequestRepository leaveRequestRepository;

    public StudentDashboardDTO getDashboardData(User user) {

        int total =
                leaveRequestRepository.countByUser(user);

        int approved =
                leaveRequestRepository
                        .countByUserAndStatus(user, "APPROVED");

        int pending =
                leaveRequestRepository
                        .countByUserAndStatus(user, "PENDING");

        int rejected =
                leaveRequestRepository
                        .countByUserAndStatus(user, "REJECTED");

        List<LeaveRequest> recentRequests =
                leaveRequestRepository
                        .findTop5ByUserOrderByAppliedOnDesc(user);

        return new StudentDashboardDTO(
                user,
                total,
                approved,
                pending,
                rejected,
                recentRequests
        );
    }
}