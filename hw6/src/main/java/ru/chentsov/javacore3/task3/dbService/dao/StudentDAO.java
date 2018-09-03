package ru.chentsov.javacore3.task3.dbService.dao;


import ru.chentsov.javacore3.task3.dbService.dataset.StudentDataSet;
import ru.chentsov.javacore3.task3.dbService.executor.Executor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Evgenii Chentsov
 */
public class StudentDAO implements DAO{

    private Executor executor;

    public StudentDAO(Connection connection) {
        this.executor = new Executor(connection);
    }

    public void createTable() throws SQLException {
        executor.executeUpdate("CREATE TABLE IF NOT EXISTS students (id BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL, " +
                "lastName VARCHAR(256) NOT NULL, " +
                "grade INT NOT NULL, " +
                "CHECK (grade >= 0 AND grade <= 100))");
    }

    public void dropTable() throws SQLException {
        executor.executeUpdate("DROP TABLE students");
    }

    public void insertStudent(String lastName, int grade) throws SQLException {
        executor.executeUpdate("INSERT INTO students (lastName, grade) VALUES ('"
                + lastName + "', "
                + grade + ")");
    }

    public void insertStudentByBatch(int count) throws SQLException {
        executor.executeUpdateBatch("INSERT INTO students (lastName, grade) VALUES (?, ?)", count);
    }

    public List<StudentDataSet> getByLastName(String lastName) throws SQLException {
        return executor.executeQuery("SELECT * FROM students WHERE lastName='" + lastName + "'", StudentDAO::getListHandler);
    }

    public void updateGrade(String lastName, int grade) throws SQLException {
        executor.executeUpdate("UPDATE students SET grade=" + grade + " WHERE lastName='" + lastName + "'");
    }

    public List<StudentDataSet> getByGradeRange(int floorGrade, int ceilGrade) throws SQLException {
        return executor.executeQuery("SELECT * FROM students WHERE grade>=" + floorGrade + " AND grade<=" + ceilGrade,
                StudentDAO::getListHandler);
    }

    private static List<StudentDataSet> getListHandler(ResultSet resultSet) throws SQLException {
        List<StudentDataSet> response = new ArrayList<>();
        while (resultSet.next()) {
            response.add(new StudentDataSet(
                    resultSet.getLong(1),
                    resultSet.getString(2),
                    resultSet.getInt(3)));
        }
        return response;
    }

}
