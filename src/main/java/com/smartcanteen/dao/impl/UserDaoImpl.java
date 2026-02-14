package com.smartcanteen.dao.impl;

import com.smartcanteen.dao.UserDao;
import com.smartcanteen.model.User;
import com.smartcanteen.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class UserDaoImpl implements UserDao {
    @Override
    public User findByEmail(String email) {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            Query<User> q = s.createQuery("from User where email = :e", User.class);
            q.setParameter("e", email);
            return q.uniqueResult();
        }
    }

    @Override
    public void save(User user) {
        Transaction tx = null;
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            tx = s.beginTransaction();
            s.saveOrUpdate(user);
            tx.commit();
        } catch (Exception ex) {
            if (tx != null) tx.rollback();
            throw ex;
        }
    }

    @Override
    public User findById(Long id) {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            return s.get(User.class, id);
        }
    }
}
