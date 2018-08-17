package ru.chentsov.javacore3.dbService;


import org.h2.jdbcx.JdbcDataSource;
import ru.chentsov.javacore3.dbService.dao.DAO;
import ru.chentsov.javacore3.dbService.dao.GoodsDAO;
import ru.chentsov.javacore3.dbService.dataset.GoodsDataSet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class DBService {

    private final Connection connection;

    public DBService() {
        this.connection = getH2Connection();
    }

    private static Connection getH2Connection() {
        try {
            String url = "jdbc:h2:./h2db";
            String name = "user";
            String pass = "user";

            JdbcDataSource ds = new JdbcDataSource();
            ds.setURL(url);
            ds.setUser(name);
            ds.setPassword(pass);

            return DriverManager.getConnection(url, name, pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void addGoods(long productId, String title, long price) {
        DAO dao = new GoodsDAO(connection);
        try {
            connection.setAutoCommit(false);
            dao.createTable();
            dao.insertGoods(productId, title, price);
            connection.commit();
        } catch (SQLException e) {
            try{
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void addGoodsByBatch(int batchSize) {
        DAO dao = new GoodsDAO(connection);
        try {
            connection.setAutoCommit(false);
            dao.createTable();
            dao.insertGoodsByBatch(batchSize);
            connection.commit();
        } catch (SQLException e) {
            try{
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<GoodsDataSet> get(String title) {
        try {
            return (new GoodsDAO(connection).getByTitle(title));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<GoodsDataSet> get(long floorPrice, long ceilPrice) {
        try {
            return (new GoodsDAO(connection).getByPriceRange(floorPrice, ceilPrice));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void updateGoodsPrice(String title, long price) {
        DAO dao = new GoodsDAO(connection);
        try {
            dao.updatePrice(title, price);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void cleanDB() {
        DAO dao = new GoodsDAO(connection);
        try {
            dao.dropTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
