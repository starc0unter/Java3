package ru.chentsov.javacore3.dbService.dao;


import ru.chentsov.javacore3.dbService.dataset.GoodsDataSet;
import ru.chentsov.javacore3.dbService.executor.Executor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GoodsDAO implements DAO {

    private Executor executor;

    public GoodsDAO(Connection connection) {
        this.executor = new Executor(connection);
    }

    public void createTable() throws SQLException {
        executor.executeUpdate("CREATE TABLE IF NOT EXISTS goods (id BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL, " +
                "prodId BIGINT NOT NULL, " +
                "title VARCHAR(256) NOT NULL, " +
                "price BIGINT NOT NULL)");
    }

    public void dropTable() throws SQLException {
        executor.executeUpdate("DROP TABLE goods");
    }

    public void insertGoods(long productId, String title, long price) throws SQLException {
        executor.executeUpdate("INSERT INTO goods (prodId, title, price) VALUES ("
                + productId + ", '"
                + title + "', "
                + price + ")");
    }

    public void insertGoodsByBatch(int count) throws SQLException {
        executor.executeUpdateBatch("INSERT INTO goods (prodId, title, price) VALUES (?, ?, ?)", count);
    }

    public List<GoodsDataSet> getByTitle(String title) throws SQLException {
        return executor.executeQuery("SELECT * FROM goods WHERE title='" + title + "'", GoodsDAO::getListHandler);
    }

    public void updatePrice(String title, long price) throws SQLException {
        executor.executeUpdate("UPDATE goods SET price=" + price + " WHERE title='" + title + "'");
    }

    public List<GoodsDataSet> getByPriceRange(long floorPrice, long ceilPrice) throws SQLException {
        return executor.executeQuery("SELECT * FROM goods WHERE price>=" + floorPrice + " AND price<=" + ceilPrice,
                GoodsDAO::getListHandler);
    }

    private static List<GoodsDataSet> getListHandler(ResultSet resultSet) throws SQLException {
        List<GoodsDataSet> response = new ArrayList<>();
        while (resultSet.next()) {
            response.add(new GoodsDataSet(
                    resultSet.getLong(1),
                    resultSet.getLong(2),
                    resultSet.getString(3),
                    resultSet.getLong(4)));
        }
        return response;
    }

}
