package com.example.cnExpense.DAL;

import java.util.List;
import com.example.cnExpense.entities.User;

public interface UserDal {
    List<User> findAll();
    User findById(Integer id);
    User save(User user);
    boolean existsByUsername(String username);
    List<User> filterUsersByCalendar(String day, String month, String year);
    List<User> filterUsersByType(String incomeType, String expenseType);
}
