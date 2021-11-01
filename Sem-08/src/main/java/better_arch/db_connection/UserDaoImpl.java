package better_arch.db_connection;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;


/**
 * Implementation of UserDao that uses the database configured and managed by DbConnectionFactory
 */
public class UserDaoImpl implements UserDao {
    @Override
    public User getUserById(int id) throws SQLException {
        try (Connection connection = DbConnectionFactory.getConnection();
             Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE id=" + id);
            if (rs.next()) {
                return extractUserFromResultSet(rs);
            }
        }
        throw new RuntimeException("failed to get user");
    }

    @Override
    public Set<User> getAllUsers() throws SQLException {
        try (Connection connection = DbConnectionFactory.getConnection();
             Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM users");
            Set<User> users = new HashSet<>();
            while (rs.next()) {
                User user = extractUserFromResultSet(rs);
                users.add(user);
            }
            return users;

        }
    }

    @Override
    public boolean insertUser(User user) {
        try (Connection connection = DbConnectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement("INSERT INTO users VALUES (?, ?, ?)")) {
            ps.setInt(1, user.getId());
            ps.setString(2, user.getName());
            ps.setInt(3, user.getAge());
            return ps.executeUpdate() == 1;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }


    private User extractUserFromResultSet(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setName(rs.getString("name"));
        user.setAge(rs.getInt("age"));
        return user;
    }
}
