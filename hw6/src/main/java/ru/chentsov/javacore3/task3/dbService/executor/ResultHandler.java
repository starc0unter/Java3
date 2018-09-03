package ru.chentsov.javacore3.task3.dbService.executor;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Evgenii Chentsov
 */
public interface ResultHandler<T> {
    T handle(ResultSet resultSet) throws SQLException;
}
