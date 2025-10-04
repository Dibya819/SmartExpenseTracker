
package com.Project.ExpenseTracker.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String category;
    @NotBlank(message = "Description should not be blank")
    private String description;
    @Positive(message = "Amount should be positive")
    private double amount;
    @JsonFormat(pattern ="yyyy-MM-dd")
    @PastOrPresent(message = "Date should be in the past or present")
    private LocalDate date;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
