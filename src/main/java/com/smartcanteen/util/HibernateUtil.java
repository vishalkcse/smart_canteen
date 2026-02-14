package com.smartcanteen.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            System.out.println("➡ Loading hibernate.cfg.xml from classpath...");

            Configuration configuration = new Configuration();
            configuration.configure(); // loads from src/main/resources

            SessionFactory sf = configuration.buildSessionFactory();

            System.out.println("✅ Hibernate SessionFactory created successfully");
            return sf;

        } catch (Throwable ex) {
            System.err.println("❌ SessionFactory creation failed");
            ex.printStackTrace();
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
