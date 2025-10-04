package com.Project.ExpenseTracker.model;

import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class ExpenseFilterRequest {
    private String username;
    private String category;
    @Positive(message = "Minimum amount must be a positive number")
    private Double minAmount;
    @Positive(message = "Maximum amount must be a positive number")
    private Double maxAmount;
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;

}