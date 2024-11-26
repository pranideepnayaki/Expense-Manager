package com.example.cnExpense.DAL;

import com.example.cnExpense.entities.*;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDalImpl implements UserDal {

    @Autowired
    private EntityManager entityManager;


    @Override
    public List<User> findAll() {
        Session session = entityManager.unwrap(Session.class);
        return session.createQuery("FROM User", User.class).getResultList();
    }

    @Override
    public User findById(Integer id) {
        Session session = entityManager.unwrap(Session.class);
        return session.get(User.class, id);
    }

    @Override
    @Transactional
    public User save(User user) {
        Session session = entityManager.unwrap(Session.class);
        session.save(user);
        return user;
    }

    @Override
    public boolean existsByUsername(String username) {
        Session session = entityManager.unwrap(Session.class);
        Long count = session.createQuery("SELECT COUNT(u) FROM User u WHERE u.username = :username", Long.class)
                            .setParameter("username", username)
                            .getSingleResult();
        return count > 0;
    }

    @Override
    public List<User> filterUsersByCalendar(String day, String month, String year) {
//        Session session = entityManager.unwrap(Session.class);
//        String query = "FROM User u WHERE " +
//                       "FUNCTION('DAY', u.createdDate) = :day AND " +
//                       "FUNCTION('MONTH', u.createdDate) = :month AND " +
//                       "FUNCTION('YEAR', u.createdDate) = :year";
//        return session.createQuery(query, User.class)
//                      .setParameter("day", Integer.parseInt(day))
//                      .setParameter("month", Integer.parseInt(month))
//                      .setParameter("year", Integer.parseInt(year))
//                      .getResultList();

        List<User> users = findAll();
        List<User> filteredUsers = new ArrayList<>();

        for(User user: users){
            List<Income> incomes = user.getIncomes();
            for(Income income: incomes){
                LocalDate date = income.getDate();
                if(day != null && date.getDayOfMonth() == Integer.parseInt(day)){
                    filteredUsers.add(user);
                }
                if(month != null && date.getMonth().equals(Integer.parseInt(month))){
                    filteredUsers.add(user);
                }
                if(year != null && date.getYear() == (Integer.parseInt(year))){
                    filteredUsers.add(user);
                }
            }
        }
        return filteredUsers;
    }

    @Override
    public List<User> filterUsersByType(String incomeType, String expenseType) {
//        Session session = entityManager.unwrap(Session.class);
//        String query = "FROM User u WHERE u.incomeType = :incomeType AND u.expenseType = :expenseType";
//        return session.createQuery(query, User.class)
//                      .setParameter("incomeType", incomeType)
//                      .setParameter("expenseType", expenseType)
//                      .getResultList();

        List<User> users = findAll();
        if(incomeType == null && expenseType == null)
            return users;
        List<User> filteredUsers = new ArrayList<>();

        for(User user: users){
            List<Income> incomes = user.getIncomes();
            for(Income income: incomes){
                List<IncomeType> incomeTypeList = income.getIncomeTypes();
                if(incomeTypeList.contains(incomeType)){
                    filteredUsers.add(user);
                } else {
                    List<Expense> expenses = user.getExpenses();
                    for(Expense expense: expenses){
                        List<ExpenseType> expenseTypeList = expense.getExpenseTypes();
                        if(expenseTypeList.contains(expenseType)){
                            filteredUsers.add(user);
                        }
                    }
                }
            }
        }

        return filteredUsers;
    }
}
