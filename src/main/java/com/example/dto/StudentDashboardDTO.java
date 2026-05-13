package com.example.dto;


import java.util.*;

import com.example.entity.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class StudentDashboardDTO {
    
    private User user;

    private int totalLeaves;

    private int approvedLeaves;

    private int pendingLeaves;

    private int rejectedLeaves;

    private List<LeaveRequest> recentRequests;
}
