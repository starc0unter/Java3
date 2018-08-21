package ru.chentsov.javacore3.dbService.dao;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import ru.chentsov.javacore3.dbService.dataset.GoodsDataSet;

import java.util.List;

/**
 * @author Evgenii Chentsov
 */
public class GoodsDAO implements DAO {

    private Session session;

    public GoodsDAO(Session session) {
        this.session = session;
    }

    public GoodsDataSet get(long id) throws HibernateException {
        return (GoodsDataSet) session.get(GoodsDataSet.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<GoodsDataSet> get(String title) throws HibernateException {
        Criteria criteria = session.createCriteria(GoodsDataSet.class);

        return (List<GoodsDataSet>) criteria
                .add(Restrictions.eq("title", title))
                .list();
    }

    @SuppressWarnings("unchecked")
    public List<GoodsDataSet> getInRange(long floorPrice, long ceilPrice) throws HibernateException {
        Criteria criteria = session.createCriteria(GoodsDataSet.class);

        return (List<GoodsDataSet>) criteria
                .add(Restrictions.between("price", floorPrice, ceilPrice))
                .list();
    }

    public void insertGoods(long productId, String title, long price) throws HibernateException {
        session.save(new GoodsDataSet(productId, title, price));
    }

    public void updatePrice(String title, long price) throws HibernateException {
        List<GoodsDataSet> rows = get(title);
        for (GoodsDataSet row : rows) {
            row.setPrice(price);
            session.update(row);
        }
        session.flush();
        session.clear();
    }

}
