package better_arch.bussiness_logic;

import better_arch.db_connection.UserDao;
import better_arch.db_connection.UserDaoImpl;

import java.io.IOException;
import java.sql.SQLException;


class A {
    static {
        System.out.println("Static");
    }

    static void foo() {
        System.out.println("foo");
    }
    A() {
        System.out.println("constructor");
    }
}

/**
 * Contains business logic
 */
public class Main {
    private static UserDao userDao;

    private static void prepareDB() {
        userDao = new UserDaoImpl();
        try {
            userDao.insertUser(new User("Alex", 31));
            userDao.insertUser(new User("George", 22));
            userDao.insertUser(new User("Max", 14));

            userDao.insertUser(new User("Nikolay", 41));
            userDao.insertUser(new User("Sergey", 12));
            userDao.insertUser(new User("Vassily", 17));

            userDao.insertUser(new User("Kerserk", 28));
            userDao.insertUser(new User("Arslek", 28));
            userDao.insertUser(new User("Roman", 27));

            userDao.insertUser(new User("Lupa", 321));
            userDao.insertUser(new User("Pupa", 322));
            userDao.insertUser(new User("Egor", 14));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void buildTable() throws SQLException, IOException {
        ExcelLoader loader = new ExcelLoader(userDao);
        loader.buildUsersTable();
    }

    private static void buildChart() throws SQLException, IOException {
        ChartBuilder chartBuilder = new ChartBuilder(userDao);
        chartBuilder.createBarChart();
    }

    public static void main(String[] args) throws SQLException, IOException {
        prepareDB();
        buildTable();
        buildChart();
    }
}
