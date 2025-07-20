package com.Project.ExpenseTracker.DTO;

import com.Project.ExpenseTracker.model.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public class ExpenseRequest {
    @NotBlank(message = "Category should not be blank")
    private String category;

    @NotBlank(message = "Description should not be blank")
    private String description;

    @Positive(message = "Amount should be positive")
    private double amount;

    @PastOrPresent(message = "Date should be in the past or present")
    private LocalDate date;

    User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
