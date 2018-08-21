package ru.chentsov.javacore3.dbService.dao;

import ru.chentsov.javacore3.dbService.dataset.GoodsDataSet;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Evgenii Chentsov
 */
public interface DAO {

    void createTable() throws SQLException;

    void dropTable() throws SQLException;

    void insertGoods(long productId, String title, long price) throws SQLException;

    void insertGoodsByBatch(int count) throws SQLException;

    List<GoodsDataSet> getByTitle(String title) throws SQLException;

    void updatePrice(String title, long price) throws SQLException;

    List<GoodsDataSet> getByPriceRange(long floorPrice, long ceilPrice) throws SQLException;

}
