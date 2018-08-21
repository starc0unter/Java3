package ru.chentsov.javacore3.dbService.dao;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import ru.chentsov.javacore3.dbService.dataset.GoodsDataSet;

import java.util.List;

/**
 * @author Evgenii Chentsov
 */
public interface DAO {

    @SuppressWarnings("unused")
    GoodsDataSet get(long id) throws HibernateException;

    List<GoodsDataSet> get(String title) throws HibernateException;

    List<GoodsDataSet> getInRange(long floorPrice, long ceilPrice) throws HibernateException;

    void insertGoods(long productId, String title, long price) throws HibernateException;

    void updatePrice(String title, long price) throws HibernateException;

}
