package aca98b.web3lv2;

import lombok.Getter;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author svinoczar
 * @since 10 Nov 2023
 * @version 1.0.0
 *
 */
public class HibernateUtil {
    @Getter
    private static SessionFactory sessionFactory = null;

    public static SessionFactory getSessionFactory() {
        if(sessionFactory == null) {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }
        return sessionFactory;
    }

    public static void setSessionFactory(SessionFactory sessionFactory) {
        HibernateUtil.sessionFactory = sessionFactory;
    }

}