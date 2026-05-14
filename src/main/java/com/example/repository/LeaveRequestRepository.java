package com.example.repository;

import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.*;

@Repository
public interface LeaveRequestRepository extends JpaRepository<LeaveRequest,Long>{
    
    // student dashboard

    List<LeaveRequest> findByUser(User user);

    int countByUser(User user);

    int countByUserAndStatus(User user, String status);

    List<LeaveRequest> findTop5ByUserOrderByAppliedOnDesc(User user);


    // Admin Dashboard

    int countByStatus(String status);


    // Recent Requests (last 3 days)

    List<LeaveRequest>
    findByAppliedOnGreaterThanEqualOrderByAppliedOnDesc(
            LocalDate date
    );
}
