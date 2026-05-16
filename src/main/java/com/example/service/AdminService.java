package com.example.service;

import java.time.LocalDate;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.*;
import com.example.repository.*;
import com.example.dto.*;

@Service
public class AdminService {

        @Autowired
        private LeaveRequestRepository leaveRequestRepository;

        public AdminDashboardDTO getDashboardData() {

                int totalRequests = (int) leaveRequestRepository.count();

                int pendingRequests = (int) leaveRequestRepository.countByStatus("PENDING");

                int approvedRequests = (int) leaveRequestRepository.countByStatus("APPROVED");

                int rejectedRequests = totalRequests - pendingRequests - approvedRequests;

                LocalDate threeDaysAgo = LocalDate.now().minusDays(3);

                List<LeaveRequest> recentRequests =

                                leaveRequestRepository
                                                .findByAppliedOnGreaterThanEqualOrderByIdDesc(
                                                                threeDaysAgo);

                List<ActivityDTO> activities = new ArrayList<>();

                for (LeaveRequest request : recentRequests) {

                        String message = "";

                        if (request.getStatus().equals("PENDING")) {

                                message = request.getUser().getFullName()
                                                + " applied for "
                                                + request.getLeaveType()
                                                + " Leave";
                        }

                        else if (request.getStatus().equals("APPROVED")) {

                                message = request.getUser().getFullName()
                                                + "'s leave approved";
                        }

                        else {

                                message = request.getUser().getFullName()
                                                + "'s leave rejected";
                        }

                        ActivityDTO activity = new ActivityDTO(

                                        request.getStatus(),

                                        message,

                                        "Recently");

                        activities.add(activity);
                }

                return new AdminDashboardDTO(totalRequests, pendingRequests, approvedRequests, rejectedRequests,
                                recentRequests, activities);

        }

        public void approveLeave(Long id) {

                LeaveRequest leaveRequest =

                                leaveRequestRepository
                                                .findById(id)
                                                .orElse(null);

                if (leaveRequest != null) {

                        leaveRequest.setStatus(
                                        "APPROVED");

                        leaveRequest.setUpdatedOn(
                                        LocalDate.now());

                        leaveRequestRepository
                                        .save(leaveRequest);
                }
        }

        public void rejectLeave(Long id) {

                LeaveRequest leaveRequest =

                                leaveRequestRepository
                                                .findById(id)
                                                .orElse(null);

                if (leaveRequest != null) {

                        leaveRequest.setStatus(
                                        "REJECTED");

                        leaveRequest.setUpdatedOn(
                                        LocalDate.now());

                        leaveRequestRepository
                                        .save(leaveRequest);
                }
        }

        public int getRemainingLeaves(User user) {

                List<LeaveRequest> approvedLeaves =

                                leaveRequestRepository
                                                .findByUserAndStatus(

                                                                user,

                                                                "APPROVED");

                int usedLeaves = 0;

                for (LeaveRequest leave : approvedLeaves) {

                        usedLeaves += leave.getTotalDays();
                }

                return 12 - usedLeaves;
        }
}
