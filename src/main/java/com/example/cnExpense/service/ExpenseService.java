package com.example.cnExpense.service;

import com.example.cnExpense.DAL.ExpenseDal;
import com.example.cnExpense.DAL.IncomeDal;
import com.example.cnExpense.entities.Expense;
import com.example.cnExpense.entities.Income;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ExpenseService {

    @Autowired
    private ExpenseDal expenseDal;

    @Autowired
    IncomeDal incomeDAL;

    @Autowired
    private IncomeService incomeService;

    public Income saveExpense(Integer incomeId, Expense expense) {
//        Income income = incomeService.getIncomeById(incomeId);
//        expense.setIncome(income);
//        return expenseDal.save(expense);
        Expense expense1 = expenseDal.save(expense);
        Income income = incomeDAL.findById(incomeId);
        income.setExpense(expense);
        incomeDAL.save(income);
        return income;
    }
}
