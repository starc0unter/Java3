package ru.chentsov.javacore3.task3.dbService;

import org.h2.jdbcx.JdbcDataSource;
import ru.chentsov.javacore3.task3.dbService.dao.DAO;
import ru.chentsov.javacore3.task3.dbService.dao.StudentDAO;
import ru.chentsov.javacore3.task3.dbService.dataset.StudentDataSet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author Evgenii Chentsov
 */
public class DBService {

    private final Connection connection;

    public DBService(String url, String name, String pass) {
        this.connection = getH2Connection(url, name, pass);
    }

    @SuppressWarnings("all")
    private static Connection getH2Connection(String url, String name, String pass) {
        try {
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

    private void add(Consumer<DAO> executor) {
        DAO dao = new StudentDAO(connection);
        try {
            connection.setAutoCommit(false);
            dao.createTable();
            executor.accept(dao);
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

    public void addStudent(String lastName, int grade) {
        add(dao -> {
            try {
                dao.insertStudent(lastName, grade);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    public void addStudentByBatch(int batchSize) {
        add(dao -> {
            try {
                dao.insertStudentByBatch(batchSize);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    public List<StudentDataSet> get(String lastName) {
        try {
            return (new StudentDAO(connection).getByLastName(lastName));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<StudentDataSet> get(int floorGrade, int ceilGrade) {
        try {
            return (new StudentDAO(connection).getByGradeRange(floorGrade, ceilGrade));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void updateStudentGrade(String lastName, int grade) {
        DAO dao = new StudentDAO(connection);
        try {
            dao.updateGrade(lastName, grade);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void cleanDB() {
        DAO dao = new StudentDAO(connection);
        try {
            dao.dropTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
