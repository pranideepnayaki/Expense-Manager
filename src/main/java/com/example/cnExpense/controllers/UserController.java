package com.example.cnExpense.controllers;

import com.example.cnExpense.entities.User;
import com.example.cnExpense.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/allUsers")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

    @PostMapping("/save")
    public User saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @PostMapping("/checkUserExist")
    public boolean checkUserExist(@RequestBody User user) {
        return userService.checkUserExist(user);
    }

    @PostMapping("/find")
    public User findUser(@RequestBody User newUser) {

//        return userService.getUserById(newUser.getId());
        return userService.findUser(newUser);
    }

    @GetMapping("/filteredUserListByCalendar")
    public List<User> filterUsersByCalendar(@RequestParam(value = "day", required = false) String day,
                                            @RequestParam(value = "month", required = false) String month,
                                            @RequestParam(value = "year", required = false) String year) {
        return userService.filterUsersByCalendar(day, month, year);
    }

    @GetMapping("/filteredUserListByType")
    public List<User> filterUsersByType(@RequestParam(value = "incomeType", required = false) String incomeType,
                                        @RequestParam(value = "expenseType", required = false) String expenseType) {
        return userService.filterUsersByType(incomeType, expenseType);
    }
}
