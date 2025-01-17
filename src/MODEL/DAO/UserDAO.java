package MODEL.DAO;

import MODEL.Patterns.singleton.DbConnectionSingleton;
import MODEL.DTO.User.UserDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAO {

    // Add a new user
    public static boolean addUser(UserDTO user) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet generatedKeys = null;
        try {
            conn = DbConnectionSingleton.getInstance().getConnection();
            String sql = "INSERT INTO user (password, email, firstname, address_id, mobile_phone, role_id, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, user.getPassword());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getFirstname());
            pstmt.setInt(4, user.getAddressId());
            pstmt.setString(5, user.getMobilePhone());
            pstmt.setInt(6, user.getRoleId());
            pstmt.setInt(7, user.getStatus());

            int result = pstmt.executeUpdate();

            if (result == 1) {
                // Retrieve the generated id
                generatedKeys = pstmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    user.setId(generatedKeys.getInt(1));  // Set the generated ID to the user object
                }
                return true;
            } else {
                return false;
            }
        } finally {
            DbConnectionSingleton.getInstance().close(conn, pstmt, generatedKeys);
        }
    }

    // Update user information
    public static boolean updateUser(UserDTO user) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DbConnectionSingleton.getInstance().getConnection();
            String sql = "UPDATE user SET password = ?, email = ?, firstname = ?, address_id = ?, mobile_phone = ?, role_id = ?, status = ? WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getPassword());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getFirstname());
            pstmt.setInt(4, user.getAddressId());
            pstmt.setString(5, user.getMobilePhone());
            pstmt.setInt(6, user.getRoleId());
            pstmt.setInt(7, user.getStatus());
            pstmt.setInt(8, user.getId());

            int result = pstmt.executeUpdate();
            return result == 1;
        } finally {
            DbConnectionSingleton.getInstance().close(conn, pstmt);
        }
    }

    // Delete a user by ID
    public static boolean deleteUser(int userId) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DbConnectionSingleton.getInstance().getConnection();
            String sql = "DELETE FROM user WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userId);

            int result = pstmt.executeUpdate();
            return result == 1;
        } finally {
            DbConnectionSingleton.getInstance().close(conn, pstmt);
        }
    }

    // Retrieve user information by ID
    public static UserDTO getUserById(int userId) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        UserDTO user = null;
        try {
            conn = DbConnectionSingleton.getInstance().getConnection();
            String sql = "SELECT * FROM user WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userId);
            rset = pstmt.executeQuery();

            if (rset.next()) {
                user = new UserDTO();
                user.setId(rset.getInt("id"));
                user.setPassword(rset.getString("password"));
                user.setEmail(rset.getString("email"));
                user.setFirstname(rset.getString("firstname"));
                user.setAddressId(rset.getInt("address_id"));
                user.setMobilePhone(rset.getString("mobile_phone"));
                user.setRoleId(rset.getInt("role_id"));
                user.setStatus(rset.getInt("status"));
            }
        } finally {
            DbConnectionSingleton.getInstance().close(conn, pstmt, rset);
        }
        return user;
    }

    // Retrieve all users
    public static ArrayList<UserDTO> getAllUsers() throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        ArrayList<UserDTO> userList = new ArrayList<>();
        try {
            conn = DbConnectionSingleton.getInstance().getConnection();
            String sql = "SELECT * FROM user";
            pstmt = conn.prepareStatement(sql);
            rset = pstmt.executeQuery();

            while (rset.next()) {
                UserDTO user = new UserDTO();
                user.setId(rset.getInt("id"));
                user.setPassword(rset.getString("password"));
                user.setEmail(rset.getString("email"));
                user.setFirstname(rset.getString("firstname"));
                user.setAddressId(rset.getInt("address_id"));
                user.setMobilePhone(rset.getString("mobile_phone"));
                user.setRoleId(rset.getInt("role_id"));
                user.setStatus(rset.getInt("status"));
                userList.add(user);
            }
        } finally {
            DbConnectionSingleton.getInstance().close(conn, pstmt, rset);
        }
        return userList;
    }

    public static ArrayList<String> getAdminEmails() throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        ArrayList<String> adminEmails = new ArrayList<>();
        try {
            conn = DbConnectionSingleton.getInstance().getConnection();
            String sql = "SELECT email FROM user WHERE role_id = 1";
            pstmt = conn.prepareStatement(sql);
            rset = pstmt.executeQuery();

            while (rset.next()) {
                adminEmails.add(rset.getString("email"));
            }
        } finally {
            DbConnectionSingleton.getInstance().close(conn, pstmt, rset);
        }
        return adminEmails;
    }

    public UserDTO getUserByEmailAndPassword(String email, String password) throws SQLException {
        // Using try-with-resources for automatic closing of resources
        String sql = "SELECT * FROM user WHERE email = ? AND password = ?";
        try (Connection conn = DbConnectionSingleton.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, email);
            pstmt.setString(2, password);

            try (ResultSet rset = pstmt.executeQuery()) {
                if (rset.next()) {
                    UserDTO user = new UserDTO();
                    user.setId(rset.getInt("id"));
                    user.setPassword(rset.getString("password"));
                    user.setEmail(rset.getString("email"));
                    user.setFirstname(rset.getString("firstname"));
                    user.setAddressId(rset.getInt("address_id"));
                    user.setMobilePhone(rset.getString("mobile_phone"));
                    user.setRoleId(rset.getInt("role_id"));
                    user.setStatus(rset.getInt("status"));
                    return user;  // Return the user if found
                }
            } catch (SQLException e) {
                // Log the SQLException or handle it as needed
                throw new SQLException("Error executing query", e);
            }
        } catch (SQLException e) {
            // Log the connection or SQL execution error
            throw new SQLException("Error getting user by email and password", e);
        }

        // Return null if no user is found or if there was an error
        return null;
    }

    public UserDTO getUserByMobilePhone(String mobilePhone) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        UserDTO user = null;
        try {
            conn = DbConnectionSingleton.getInstance().getConnection();
            String sql = "SELECT * FROM user WHERE mobile_phone = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, mobilePhone);
            rset = pstmt.executeQuery();

            if (rset.next()) {
                user = new UserDTO();
                user.setId(rset.getInt("id"));
                user.setPassword(rset.getString("password"));
                user.setEmail(rset.getString("email"));
                user.setFirstname(rset.getString("firstname"));
                user.setAddressId(rset.getInt("address_id"));
                user.setMobilePhone(rset.getString("mobile_phone"));
                user.setRoleId(rset.getInt("role_id"));
                user.setStatus(rset.getInt("status"));
            }
        } finally {
            DbConnectionSingleton.getInstance().close(conn, pstmt, rset);
        }
        return user;
    }
    public static boolean callForEventApproval(){
        return true;
    }
    }


