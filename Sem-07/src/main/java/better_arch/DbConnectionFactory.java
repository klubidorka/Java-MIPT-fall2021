package better_arch;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Prepares database and provides connection to DB
 */
public class DbConnectionFactory {
    private final static String pathToDb = "./sqlite/sem07testDB.sqlite";
    private static final HikariConfig config = new HikariConfig();
    private static final DataSource ds;

    static {
        config.setJdbcUrl("jdbc:sqlite:" + pathToDb);
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        ds = new HikariDataSource(config);
        try {
            prepareDB();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void prepareDB() throws SQLException {
        Connection connection = getConnection();
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("drop table if exists users");
            statement.executeUpdate("create table Users (id integer, name varchar(200), age integer)");
        }
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}