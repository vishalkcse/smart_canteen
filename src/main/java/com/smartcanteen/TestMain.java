package com.smartcanteen;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.smartcanteen.util.HibernateUtil;

public class TestMain {
    public static void main(String[] args) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        System.out.println("✅ Hibernate connected successfully!");
        session.close();
        factory.close();
    }
}
