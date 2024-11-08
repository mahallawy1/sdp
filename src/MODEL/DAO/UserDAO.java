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

    public UserDTO getUserByEmailAndPassword(String email, String password) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public UserDTO getUserByMobilePhone(String mobilePhone) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

