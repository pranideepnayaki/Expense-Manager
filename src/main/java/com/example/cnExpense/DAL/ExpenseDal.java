package com.example.cnExpense.DAL;

import com.example.cnExpense.entities.Expense;
import java.util.List;

public interface ExpenseDal {
    List<Expense> findAll();
    Expense findById(Integer id);
    Expense save(Expense expense);
    List<Expense> findByIncomeId(Integer incomeId);
}
