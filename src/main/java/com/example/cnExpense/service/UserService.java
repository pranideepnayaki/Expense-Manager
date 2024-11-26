package com.example.cnExpense.service;

import com.example.cnExpense.DAL.UserDal;
import com.example.cnExpense.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public class UserService {

    @Autowired
    private UserDal userDal;

    @Transactional
    public List<User> getAllUsers() {
        return userDal.findAll();
    }

    @Transactional
    public User getUserById(Integer id) {
        return userDal.findById(id);
    }

    @Transactional
    public User findUser(User newUser) {
        List<User> users  = getAllUsers();
        Integer id = 0;
        for(User user1 : users)
        {
            if(Objects.equals(user1.getUsername(), newUser.getUsername()))
            {
                id = user1.getId();
            }
        }
        if(id == 0)
            return null;
        return getUserById(id);
    }

    @Transactional
    public User saveUser(User user) {
        return userDal.save(user);
    }

    @Transactional
    public boolean checkUserExist(User user) {
        return userDal.existsByUsername(user.getUsername());
    }

    @Transactional
    public List<User> filterUsersByCalendar(String day, String month, String year) {
        return userDal.filterUsersByCalendar(day, month, year);
    }
    
    @Transactional
    public List<User> filterUsersByType(String incomeType, String expenseType) {
        return userDal.filterUsersByType(incomeType, expenseType);
    }
}
