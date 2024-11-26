package com.example.cnExpense.DAL;

import com.example.cnExpense.entities.Income;
import java.util.List;

public interface IncomeDal {
    List<Income> findAll();
    Income findById(Integer id);
    Income save(Income income);
    List<Income> findByUserId(Integer userId);
}
