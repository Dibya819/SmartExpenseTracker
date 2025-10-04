package com.Project.ExpenseTracker.DTO;

import com.Project.ExpenseTracker.model.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
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

}
