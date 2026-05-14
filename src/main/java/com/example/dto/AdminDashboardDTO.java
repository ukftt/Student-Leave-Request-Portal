package com.example.dto;

import java.util.*;

import com.example.entity.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminDashboardDTO {
    
    private int totalRequests;
    
    private int pendingRequests;

    private int approvedRequests;

    private int rejectedRequests;

    private List<LeaveRequest> requests;

    private List<ActivityDTO> activities;

}
