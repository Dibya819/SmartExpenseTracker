package com.Project.ExpenseTracker.service;

import com.Project.ExpenseTracker.model.Expense;
import com.Project.ExpenseTracker.model.ExpenseFilterRequest;
import com.Project.ExpenseTracker.model.User;
import com.Project.ExpenseTracker.repository.ExpenseRepository;
import com.Project.ExpenseTracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class ExpenseService {
    @Autowired
    private ExpenseRepository expenseRepository;
    @Autowired
    private UserRepository userRepository;
    public void addExpense(Expense expense, String username){
        User user=userRepository.findByUsername(username).orElseThrow(()->new RuntimeException("User not found with this username"+username));
        expense.setUser(user);
        expenseRepository.save(expense);
    }
    public List<Expense> getExpensesByUsername(String username){
        User user=userRepository.findByUsername(username).orElseThrow(()->new RuntimeException("User not found with this username"+username));
        return expenseRepository.findByUser(user);
    }
   public Expense updateExpense(Long id, Expense updatedExpense, String username){
        Expense existingExpense=expenseRepository.findById(id).orElseThrow(()->new RuntimeException("Expense not Found"));
        if(!existingExpense.getUser().getUsername().equals(username)){
            throw new RuntimeException("Unauthorized access");
        }
        existingExpense.setDescription(updatedExpense.getDescription());
        existingExpense.setAmount(updatedExpense.getAmount());
        existingExpense.setDate(updatedExpense.getDate());
        existingExpense.setCategory(updatedExpense.getCategory());
        return expenseRepository.save(existingExpense);
   }
   public List<Expense> filterExpenses(ExpenseFilterRequest request,String username){
       User user=userRepository.findByUsername(username).orElseThrow(()-> new RuntimeException("User Not Found"));
       return expenseRepository.filterExpenses(
               user.getId(),
               request.getCategory(),
               request.getMinAmount(),
               request.getMaxAmount(),
               request.getStartDate(),
               request.getEndDate(),
               request.getDescription()
       );
   }
   @Transactional
   public void deleteByUserAndId(Long id,String username){
       User user=userRepository.findByUsername(username).orElseThrow(()-> new RuntimeException("User Not Found"));
       int deletedCount=expenseRepository.deleteByUserIdAndDescription(user.getId(), id);
       if(deletedCount==0){
           throw new RuntimeException("No expense found with that description for this user");
       }
   }
}
