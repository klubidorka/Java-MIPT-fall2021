package better_arch;

import java.sql.SQLException;
import java.util.Set;


/**
 * Contains business logic
 */
public class Main {
    public static void main(String[] args) throws SQLException {
        UserDao userDao = new UserDaoImpl();

        userDao.insertUser(new User("Alex", 31));
        userDao.insertUser(new User("George", 22));
        userDao.insertUser(new User("Max", 14));

        Set<User> users = userDao.getAllUsers();
        for (User user : users) {
            System.out.println(user);
        }
    }
}
