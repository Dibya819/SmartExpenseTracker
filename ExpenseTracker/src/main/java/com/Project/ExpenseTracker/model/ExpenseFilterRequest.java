package com.Project.ExpenseTracker.model;

import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(Double minAmount) {
        this.minAmount = minAmount;
    }

    public Double getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(Double maxAmount) {
        this.maxAmount = maxAmount;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}