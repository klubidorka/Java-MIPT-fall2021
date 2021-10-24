package statements;

import java.sql.*;
import java.util.Scanner;


/**
 * Вводим пользовательские данные при помощи Prepared Statement и защищаемся от инъекций
 */
public class JDBCDemo3 {
    final static String pathToDb = "./sqlite/sem07testDB.sqlite";

    public static void main(String[] args) {

        System.out.println("Enter your name");
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

            ////////////////////////////////////////////////////////////////////////////////////////////////
            try (PreparedStatement preparedStatement = connection.prepareStatement("insert into Users values(3, ?)")) {
                preparedStatement.setString(1, name);
                preparedStatement.executeUpdate();
            }
            ////////////////////////////////////////////////////////////////////////////////////////////////

            ResultSet rs = statement.executeQuery("select * from Users");
            while (rs.next()) {
                System.out.println("name = " + rs.getString("name") + ", id = " + rs.getInt("id"));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}