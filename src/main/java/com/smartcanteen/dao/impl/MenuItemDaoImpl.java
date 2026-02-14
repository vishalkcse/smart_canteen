package com.smartcanteen.dao.impl;

import com.smartcanteen.dao.MenuItemDao;
import com.smartcanteen.model.MenuItem;
import com.smartcanteen.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class MenuItemDaoImpl implements MenuItemDao {
    @Override
    public List<MenuItem> findAll() {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            Query<MenuItem> q = s.createQuery("from MenuItem where available = true", MenuItem.class);
            return q.list();
        }
    }

    @Override
    public MenuItem findById(Long id) {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            return s.get(MenuItem.class, id);
        }
    }

    @Override
    public void save(MenuItem item) {
        Transaction tx = null;
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            tx = s.beginTransaction();
            s.saveOrUpdate(item);
            tx.commit();
        } catch (Exception ex) {
            if (tx != null) tx.rollback();
            throw ex;
        }
    }
}
