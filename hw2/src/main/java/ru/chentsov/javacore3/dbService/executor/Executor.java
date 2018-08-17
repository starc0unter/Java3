package ru.chentsov.javacore3.dbService.executor;

import java.sql.*;

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
                statement.setLong(1, i);
                statement.setString(2, "good" + i);
                statement.setLong(3, (i + 1) * 10);
                statement.addBatch();
            }
            statement.executeBatch();
        }
    }

}
