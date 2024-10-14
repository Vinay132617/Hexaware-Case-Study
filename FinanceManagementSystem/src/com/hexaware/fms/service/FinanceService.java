package com.hexaware.fms.service;

import com.hexaware.fms.entity.Expense;
import com.hexaware.fms.entity.User;
import java.util.List;

public interface FinanceService {
    boolean addUser(User user);
    boolean addExpense(Expense expense);
    boolean removeUser(int userId);
    boolean removeExpense(int expenseId);
    List<Expense> listExpenses(int userId);
    boolean updateExpense(int userId, Expense expense);
}