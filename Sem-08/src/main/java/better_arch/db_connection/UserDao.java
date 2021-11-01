package better_arch.db_connection;

import better_arch.bussiness_logic.User;

import java.sql.SQLException;
import java.util.Set;

public interface UserDao {
    User getUserById(int id) throws SQLException;

    Set<User> getAllUsers() throws SQLException;

    boolean insertUser(User user) throws SQLException;
}