package com.example.entity;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import jakarta.annotation.Generated;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="leave_requests")

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaveRequest {
    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    
    private String leaveType;

    private LocalDate fromDate;

    private LocalDate toDate;

    private String reason;

    private String status;

    private LocalDate appliedOn;

    private LocalDate updatedOn;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public long getTotalDays() {

        return ChronoUnit.DAYS.between(

            fromDate,

            toDate

        ) + 1;
    }
}
