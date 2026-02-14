package com.smartcanteen.dao.impl;

import com.smartcanteen.dao.PreOrderDao;
import com.smartcanteen.model.PreOrder;
import com.smartcanteen.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class PreOrderDaoImpl implements PreOrderDao {

    @Override
    public void save(PreOrder po) {
        Transaction tx = null;
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            tx = s.beginTransaction();
            s.save(po);
            tx.commit();
        } catch (RuntimeException ex) {
            if (tx != null) tx.rollback();
            throw ex;
        }
    }

    @Override
    public List<PreOrder> findByUserId(Long userId) {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            Query<PreOrder> q = s.createQuery(
                "from PreOrder where user.id = :uid order by createdAt desc",
                PreOrder.class
            );
            q.setParameter("uid", userId);
            return q.list();
        }
    }

    @Override
    public List<PreOrder> findAll() {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            Query<PreOrder> q = s.createQuery(
                "from PreOrder order by createdAt desc",
                PreOrder.class
            );
            return q.list();
        }
    }
}
