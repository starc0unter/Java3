package ru.chentsov.javacore3.task3.dbService.executor;

import java.sql.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Evgenii Chentsov
 */
public class Executor {

    private final Connection connection;

    public Executor(Connection connection) {
        this.connection = connection;
    }

    public void executeUpdate(String update) throws SQLException {
        try(Statement statement = connection.createStatement()){
            statement.execute(update);
        }
    }

    public <T> T executeQuery(String query, ResultHandler<T> handler) throws SQLException {
        try(Statement statement = connection.createStatement()){
            statement.execute(query);
            ResultSet resultSet = statement.getResultSet();
            T value = handler.handle(resultSet);
            resultSet.close();

            return value;
        }
    }

    public void executeUpdateBatch(String preparedUpdate, int batchSize) throws SQLException {
        try(PreparedStatement statement = connection.prepareStatement(preparedUpdate)) {
            for (int  i = 0; i < batchSize; i++) {
                statement.setString(1, "student" + i);
                statement.setLong(2, ThreadLocalRandom.current().nextInt(100));
                statement.addBatch();
            }
            statement.executeBatch();
        }
    }

}
