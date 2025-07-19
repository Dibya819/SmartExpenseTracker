package com.Project.ExpenseTracker.controller;

import com.Project.ExpenseTracker.model.Expense;
import com.Project.ExpenseTracker.model.ExpenseFilterRequest;
import com.Project.ExpenseTracker.model.User;
import com.Project.ExpenseTracker.repository.UserRepository;
import com.Project.ExpenseTracker.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/expense")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private UserRepository userRepository;

    private String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();  // username
    }

    @PostMapping("/add")
    public ResponseEntity<String> addExpense(@RequestBody Expense expense) {
        String username = getCurrentUsername();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        expense.setUser(user);
        expenseService.addExpense(expense, username);

        return ResponseEntity.ok("Expense added successfully");
    }

    @GetMapping("/by-username")
    public List<Expense> getExpenseByUser() {
        String username = getCurrentUsername();
        return expenseService.getExpensesByUsername(username);
    }

    @PutMapping("/update/{id}")
    public Expense updateExpense(@PathVariable Long id, @RequestBody Expense updatedExpense) {
        String username = getCurrentUsername();
        return expenseService.updateExpense(id, updatedExpense, username);
    }

    @PostMapping("/filter")
    public List<Expense> filterExpenses(@RequestBody ExpenseFilterRequest filterRequest) {
        String username = getCurrentUsername();
        return expenseService.filterExpenses(filterRequest, username);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteExpense(@PathVariable Long id) {
        String username = getCurrentUsername();
        expenseService.deleteByUserAndId(id, username);
        return ResponseEntity.ok("Expense with id " + id + " for user " + username + " deleted successfully.");
    }
}
