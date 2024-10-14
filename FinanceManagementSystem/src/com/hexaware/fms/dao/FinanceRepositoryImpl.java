package com.hexaware.fms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hexaware.fms.entity.Expense;
import com.hexaware.fms.entity.ExpenseCategory;
import com.hexaware.fms.entity.User;


public class FinanceRepositoryImpl implements IFinanceDao {
	
		
	private Connection conn;
	
	public FinanceRepositoryImpl() {
		super();
		conn = DBUtil.getDBConnection();
	}


	    @Override
	    public boolean createUser(User user) {
	    	
	    	String createUserQuery = "INSERT INTO Users (username, password, email) VALUES (?, ?, ?)";
	    	
	        try (
	            PreparedStatement stmt = conn.prepareStatement(createUserQuery)) {
	            stmt.setString(1, user.getUserName());
	            stmt.setString(2, user.getPassword());
	            stmt.setString(3, user.getEmail());

	            int rowsAffected = stmt.executeUpdate();
	            return rowsAffected > 0;
	        } catch (SQLException e) {
	            // Handle the exception here (e.g., log an error message)
	            return false;
	        }
	    }

	    

	    @Override
	    public boolean createExpense(Expense expense){
	    	String createExpenseQuery = "INSERT INTO Expenses (user_id, amount, category_id, date, description) VALUES (?, ?, ?, ?, ?)";
	        try (
	             PreparedStatement stmt = conn.prepareStatement(createExpenseQuery)) {
	            stmt.setInt(1, expense.getUserId());
	            stmt.setDouble(2, expense.getAmount());
	            stmt.setInt(3, expense.getCategoryId());
	            stmt.setDate(4, new java.sql.Date(expense.getDate().getTime()));
	            stmt.setString(5, expense.getDescription());
	            int rowsAffected = stmt.executeUpdate();
	            return rowsAffected > 0;
	        } catch (SQLException e) {
	           return false;
	        }
	    }
    
   
	@Override
	public boolean deleteUser(int userId) {
		
		String deleteUserQuery = "DELETE FROM Users WHERE user_id = ?";
		try (

	            PreparedStatement stmt = conn.prepareStatement(deleteUserQuery)) {
	            stmt.setInt(1, userId);
	            int rowsAffected = stmt.executeUpdate();
	            return rowsAffected > 0;
	        } catch (SQLException e) {
	           return false;
	        }
	}

	@Override
	public boolean deleteExpense(int expenseId) {
		
		String deleteExpenseQuery = "DELETE FROM Expenses WHERE expense_id = ?";
		try (
	            PreparedStatement stmt = conn.prepareStatement(deleteExpenseQuery)) {
	            stmt.setInt(1, expenseId);
	            int rowsAffected = stmt.executeUpdate();
	            return rowsAffected > 0;
	        } catch (SQLException e) {
	            return false;
	        }
	}

	    
	@Override
	public boolean updateExpense(int userId, Expense expense) {
		
		String updateExpenseQuery = "UPDATE Expenses SET amount = ?, category_id = ?, date = ?, description = ? WHERE expense_id = ? AND user_id = ?";
		
		 try (
	            PreparedStatement stmt = conn.prepareStatement(updateExpenseQuery)) {
	            stmt.setDouble(1, expense.getAmount());
	            stmt.setInt(2, expense.getCategoryId());
	            stmt.setDate(3, new java.sql.Date(expense.getDate().getTime()));
	            stmt.setString(4, expense.getDescription());
	            stmt.setInt(5, expense.getExpenseId());
	            stmt.setInt(6, userId);
	            int rowsAffected = stmt.executeUpdate();
	            return rowsAffected > 0;
	        } catch (SQLException e) {
	         return false;
	        }
	}


	@Override
	public List<Expense> getAllExpenses(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

//    @Override
//    public List<Expense> getAllExpenses(int userId){
//    	
//    	String getAllExpensesQuery = "SELECT e.expense_id, e.amount, e.date, e.description, c.category_name FROM Expenses e JOIN ExpenseCategories c ON e.category_id = c.category_id WHERE e.user_id = ?";
//        try (
//            PreparedStatement stmt = conn.prepareStatement(getAllExpensesQuery)) {
//            stmt.setInt(1, userId);
//            ResultSet rs = stmt.executeQuery();
//            
//            List<Expense> expenses = new ArrayList<>();
//            while (rs.next()) {
//                Expense expense = new Expense();
//                expense.setExpenseId(rs.getInt("expense_id"));
//                expense.setAmount(rs.getDouble("amount"));
//                expense.setDate(rs.getDate("date"));
//
//                expense.setDescription(rs.getString("description"));
//                expense.setCategoryId(new ExpenseCategory(rs.getInt("category_id"), rs.getString("category_name")));
//                expenses.add(expense);
//            }
//            return expenses;
//        } catch (SQLException e) {
//        	e.printStackTrace();
//        }
//		return expenses;
//    }

}
	
	
	
	
	