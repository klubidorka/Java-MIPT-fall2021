import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.*;

public class ConnectionPoolDemo {

    static long measureTimeMillis(Runnable runnable) {
        long start = System.currentTimeMillis();
        runnable.run();
        return System.currentTimeMillis() - start;
    }

    public static void main(String[] args) {
        int times = 1000;

        long simpleConnectionTime = measureTimeMillis(() -> SimpleConnection.repeatQueries(times, true));
        long pooledConnectionTime = measureTimeMillis(() -> ConnectionPool.repeatQueries(times, true));
        System.out.printf("Simple connection took %d ms\nPooled connaction took %d ms\n", simpleConnectionTime, pooledConnectionTime);

        long simpleConnectionTimeWithoutAutoCommit = measureTimeMillis(() -> SimpleConnection.repeatQueries(times, false));
        long pooledConnectionTimeWithoutAutoCommit = measureTimeMillis(() -> ConnectionPool.repeatQueries(times, false));
        System.out.println("Autocommit is off now");
        System.out.printf("Simple connection took %d ms\nPooled connaction took %d ms\n", simpleConnectionTimeWithoutAutoCommit, pooledConnectionTimeWithoutAutoCommit);

        long batch = measureTimeMillis(() -> ConnectionPool.repeatQueriesBatch(times));
        System.out.printf("Batch pooled connaction took %d ms\n", batch);
    }
}

class ConnectionPool {
    private final static String pathToDb = "./sqlite/sem07testDB.sqlite";
    private static final HikariConfig config = new HikariConfig();
    private static final DataSource ds;

    static {
        config.setJdbcUrl("jdbc:sqlite:" + pathToDb);
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        ds = new HikariDataSource(config);
    }


    public static void repeatQueries(int times, boolean autoCommit) {
        try {
            try (Connection connection = ds.getConnection();
                 Statement statement = connection.createStatement()) {
                connection.setAutoCommit(autoCommit);

                statement.executeUpdate("drop table if exists Users");
                statement.executeUpdate("create table Users (id integer, name string)");
                for (int i = 0; i < times; i++) {
                    try (PreparedStatement preparedStatement = connection.prepareStatement("insert into Users values(?, 'Test')")) {
                        preparedStatement.setInt(1, i);
                        preparedStatement.executeUpdate();
                    }
                }
                if (!autoCommit) {
                    connection.commit();
                }
            }
        } catch (SQLException ignored) {
            throw new RuntimeException("fail");
        }
    }


    public static void repeatQueriesBatch(int times) {
        try {
            try (Connection connection = ds.getConnection();
                 Statement statement = connection.createStatement()) {
                connection.setAutoCommit(false);

                statement.executeUpdate("drop table if exists Users");
                statement.executeUpdate("create table Users (id integer, name string)");

                try (PreparedStatement preparedStatement = connection.prepareStatement("insert into Users values(?, 'Test')")) {
                    for (int i = 0; i < times; i++) {
                        preparedStatement.setInt(1, i);
                        preparedStatement.addBatch();
                    }
                    preparedStatement.executeBatch();
                }

                connection.commit();
            }
        } catch (SQLException ignored) {
            throw new RuntimeException("fail");
        }
    }
}

class SimpleConnection {
    private final static String pathToDb = "./sqlite/sem07testDB.sqlite";

    public static void repeatQueries(int times, boolean autoCommit) {
        try {
            try (Connection connection = DriverManager.getConnection("jdbc:sqlite:" + pathToDb);
                 Statement statement = connection.createStatement()) {
                connection.setAutoCommit(autoCommit);
                statement.executeUpdate("drop table if exists Users");
                statement.executeUpdate("create table Users (id integer, name string)");
                for (int i = 0; i < times; i++) {
                    try (PreparedStatement preparedStatement = connection.prepareStatement("insert into Users values(?, 'Test')")) {
                        preparedStatement.setInt(1, i);
                        preparedStatement.executeUpdate();
                    }
                }
                if (!autoCommit) {
                    connection.commit();
                }
            }
        } catch (SQLException ignored) {
            throw new RuntimeException("fail");
        }
    }
}
