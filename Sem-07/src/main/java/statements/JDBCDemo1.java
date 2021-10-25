package statements;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Хотим выполнить базовые запросы к базе данных
 * 1) Создаем таблицу
 * 2) Добавляем в таблицу подготовленные заранее данные
 * 3) Выгружаем все данные из таблицы
 */
public class JDBCDemo1 {
    final static String pathToDb = "./sqlite/sem07testDB.sqlite";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:" + pathToDb);
             Statement statement = connection.createStatement()) {

            statement.setQueryTimeout(30);
            statement.executeUpdate("drop table if exists Users");
            statement.executeUpdate("create table Users (id integer, name string)");
            statement.executeUpdate("insert into Users values(1, 'Egor')");
            statement.executeUpdate("insert into Users values(2, 'Roman')");

            try (ResultSet rs = statement.executeQuery("select * from Users")) {
                while (rs.next()) {
                    System.out.println("name = " + rs.getString("name"));
                    System.out.println("id = " + rs.getInt("id"));
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}