package com.example.cnExpense.DAL;

import com.example.cnExpense.entities.Expense;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class ExpenseDalImpl implements ExpenseDal {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Expense> findAll() {
        Session session = entityManager.unwrap(Session.class);
        return session.createQuery("FROM Expense", Expense.class).getResultList();
    }

    @Override
    public Expense findById(Integer id) {
        Session session = entityManager.unwrap(Session.class);
        return session.get(Expense.class, id);
    }

    @Override
    @Transactional
    public Expense save(Expense expense) {
        Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(expense); // Save or update based on whether the ID exists
        return expense;
    }

    @Override
    public List<Expense> findByIncomeId(Integer incomeId) {
        Session session = entityManager.unwrap(Session.class);
        String query = "FROM Expense e WHERE e.income.id = :incomeId";
        return session.createQuery(query, Expense.class)
                      .setParameter("incomeId", incomeId)
                      .getResultList();
    }
}
