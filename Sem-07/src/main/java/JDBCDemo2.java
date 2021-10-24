import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


/**
 * То же самое, но данные вводятся пользователем
 * 1) Создаем таблицу
 * 2) Добавляем в таблицу пользовательские данные
 * 3) Выгружаем все данные из таблицы
 * <p>
 * Что меняется? Где может возникнуть проблема?
 */
public class JDBCDemo2 {
    final static String pathToDb = "./sqlite/sem07testDB.sqlite";

    public static void main(String[] args) {

        String name;
        try (Scanner sc = new Scanner(System.in)) {
            name = sc.nextLine();
        }

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:" + pathToDb);
             Statement statement = connection.createStatement()) {

            statement.setQueryTimeout(30);
            statement.executeUpdate("drop table if exists Users");
            statement.executeUpdate("create table Users (id integer, name string)");
            statement.executeUpdate("insert into Users values(1, 'Egor')");
            statement.executeUpdate("insert into Users values(2, 'Roman')");
            statement.executeUpdate(String.format("insert into Users values(3, '%s')", name));

            ResultSet rs = statement.executeQuery("select * from Users");
            while (rs.next()) {
                System.out.println("name = " + rs.getString("name") + ", id = " + rs.getInt("id"));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}