package com.example.repository;

import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.LeaveRequest;
import com.example.entity.User;

@Repository
public interface LeaveRequestRepository
        extends JpaRepository<LeaveRequest, Long> {

    // =========================================
    // STUDENT DASHBOARD
    // =========================================

    List<LeaveRequest> findByUser(User user);

    int countByUser(User user);

    int countByUserAndStatus(
            User user,

            String status);

    // List<LeaveRequest> findTop5ByUserOrderByAppliedOnDesc(
    // User user);
    List<LeaveRequest> findTop5ByUserOrderByIdDesc(
            User user);

    // Student Leave History

    // List<LeaveRequest> findByUserOrderByAppliedOnDesc(
    // User user);

    List<LeaveRequest> findByUserOrderByIdDesc(
            User user);

    // Student Filter By Status

    List<LeaveRequest> findByUserAndStatus(
            User user,

            String status);

    // =========================================
    // ADMIN DASHBOARD
    // =========================================

    int countByStatus(String status);

    // Recent Requests (Last 3 Days)

    List<LeaveRequest> findByAppliedOnGreaterThanEqualOrderByIdDesc(
            LocalDate date);

    // Recent Pending Requests

    List<LeaveRequest> findByStatusAndAppliedOnGreaterThanEqualOrderByIdDesc(

            String status,

            LocalDate date);

    // =========================================
    // OPTIONAL FUTURE FEATURES
    // =========================================

    // Filter By Date Range

    List<LeaveRequest> findByAppliedOnBetween(

            LocalDate start,

            LocalDate end);

}