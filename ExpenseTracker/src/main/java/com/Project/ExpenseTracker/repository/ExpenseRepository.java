package com.Project.ExpenseTracker.repository;

import com.Project.ExpenseTracker.model.Expense;
import com.Project.ExpenseTracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ExpenseRepository extends JpaRepository<Expense,Long> {
    @Query("SELECT e FROM Expense e WHERE " +
            "(:userId IS NULL OR e.user.id=:userId) And"+
            "(:category IS NULL OR e.category = :category) AND " +
            "(:minAmount IS NULL OR e.amount >= :minAmount) AND " +
            "(:maxAmount IS NULL OR e.amount <= :maxAmount) AND " +
            "(:startDate IS NULL OR e.date >= :startDate) AND " +
            "(:endDate IS NULL OR e.date <= :endDate) AND " +
            "(:description IS NULL OR LOWER(e.description) LIKE LOWER(CONCAT('%', :description, '%')))")
    List<Expense> filterExpenses(
            @Param("userId") Long userId,
            @Param("category") String category,
            @Param("minAmount") Double minAmount,
            @Param("maxAmount") Double maxAmount,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("description") String description
    );

    @Transactional
    @Modifying
    @Query("DELETE FROM Expense e WHERE e.user.id=:userId AND e.id=:id")
    int deleteByUserIdAndDescription(@Param("userId") Long userId,@Param("id") Long id);
    List<Expense> findByUser(User user);
}
