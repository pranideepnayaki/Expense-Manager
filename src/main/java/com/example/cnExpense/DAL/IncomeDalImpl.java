package com.example.cnExpense.DAL;

import com.example.cnExpense.entities.Income;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class IncomeDalImpl implements IncomeDal {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Income> findAll() {
        Session session = entityManager.unwrap(Session.class);
        return session.createQuery("FROM Income", Income.class).getResultList();
    }

    @Override
    public Income findById(Integer id) {
        Session session = entityManager.unwrap(Session.class);
        return session.get(Income.class, id);
    }

    @Override
    @Transactional
    public Income save(Income income) {
        Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(income); // Save or update depending on ID
        return income;
    }

    @Override
    public List<Income> findByUserId(Integer userId) {
        Session session = entityManager.unwrap(Session.class);
        String query = "FROM Income i WHERE i.user.id = :userId";
        return session.createQuery(query, Income.class)
                      .setParameter("userId", userId)
                      .getResultList();
    }
}
