package ru.chentsov.javacore3.task3.dbService.dao;

import ru.chentsov.javacore3.task3.dbService.dataset.StudentDataSet;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Evgenii Chentsov
 */
public interface DAO {

    void createTable() throws SQLException;

    void dropTable() throws SQLException;

    void insertStudent(String lastName, int grade) throws SQLException;

    void insertStudentByBatch(int count) throws SQLException;

    List<StudentDataSet> getByLastName(String lastName) throws SQLException;

    void updateGrade(String lastName, int grade) throws SQLException;

    List<StudentDataSet> getByGradeRange(int floorGrade, int ceilGrade) throws SQLException;

}
