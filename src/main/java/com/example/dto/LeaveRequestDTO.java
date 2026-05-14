package com.example.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaveRequestDTO {

    @NotBlank(message = "Leave type is required")
    private String leaveType;




    @NotNull(message = "From date is required")

    @FutureOrPresent(message =
            "From date cannot be in the past")

    private LocalDate fromDate;




    @NotNull(message = "To date is required")

    @FutureOrPresent(message =
            "To date cannot be in the past")

    private LocalDate toDate;




    @NotBlank(message = "Reason is required")
    private String reason;

}