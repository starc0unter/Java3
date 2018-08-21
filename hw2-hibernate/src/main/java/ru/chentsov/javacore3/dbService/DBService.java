package ru.chentsov.javacore3.dbService;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import ru.chentsov.javacore3.dbService.dao.DAO;
import ru.chentsov.javacore3.dbService.dao.GoodsDAO;
import ru.chentsov.javacore3.dbService.dataset.GoodsDataSet;

import java.util.List;

/**
 * @author Evgenii Chentsov
 */
public class DBService {

    private static final String hibernate_show_sql = "false";
    private static final String hibernate_hbm2ddl_auto = "create";

    private final SessionFactory sessionFactory;

    public DBService() {
        Configuration configuration = getH2Configuration();
        sessionFactory = createSessionFactory(configuration);
    }

    private Configuration getH2Configuration() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(GoodsDataSet.class);

        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        configuration.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:h2:./h2db");
        configuration.setProperty("hibernate.connection.username", "tully");
        configuration.setProperty("hibernate.connection.password", "tully");
        configuration.setProperty("hibernate.show_sql", hibernate_show_sql);
        configuration.setProperty("hibernate.hbm2ddl.auto", hibernate_hbm2ddl_auto);
        return configuration;
    }

    private static SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    public void addGoods(long productId, String title, long price) {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            DAO dao = new GoodsDAO(session);
            dao.insertGoods(productId, title, price);
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    public List<GoodsDataSet> get(String title) {
        try {
            Session session = sessionFactory.openSession();
            DAO dao = new GoodsDAO(session);
            List<GoodsDataSet> dataSet = dao.get(title);
            session.close();
            return dataSet;
        } catch (HibernateException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<GoodsDataSet> get(long floorPrice, long ceilPrice) {
        try {
            Session session = sessionFactory.openSession();
            DAO dao = new GoodsDAO(session);
            List<GoodsDataSet> dataSet = dao.getInRange(floorPrice, ceilPrice);
            session.close();
            return dataSet;
        } catch (HibernateException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void updateGoodsPrice(String title, long price) {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            DAO dao = new GoodsDAO(session);
            dao.updatePrice(title, price);
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        sessionFactory.close();
    }

}
