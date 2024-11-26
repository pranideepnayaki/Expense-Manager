package com.example.cnExpense.service;

import com.example.cnExpense.DAL.IncomeDal;
import com.example.cnExpense.DAL.UserDal;
import com.example.cnExpense.entities.Income;
import com.example.cnExpense.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class IncomeService {

    @Autowired
    private IncomeDal incomeDal;
    
    @Autowired
    private UserService userService;

    @Autowired
    UserDal userDAL;

    @Transactional
    public Income getIncomeById(Integer id) {
        return incomeDal.findById(id);
    }

    @Transactional
    public Income saveIncome(Integer userId, Income income) {
//        User user = userService.getUserById(userId);
//        income.getUsers().add(user);
//        return incomeDal.save(income);
        User user = userDAL.findById(userId);
//        if(user == null)
//            throw new NotFoundException("No user found with id:  " + userId);
        List<Income> incomeList = user.getIncomes();
        incomeList.add(income);
        user.setIncomes(incomeList);
        userDAL.save(user);
        return income;
    }
}
