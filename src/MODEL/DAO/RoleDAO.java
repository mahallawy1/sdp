package MODEL.DAO;

import MODEL.DTO.User.RoleDTO;
import MODEL.Patterns.singleton.DbConnectionSingleton;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoleDAO {

    public static boolean addRole(RoleDTO role) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DbConnectionSingleton.getInstance().getConnection();
            pstmt = conn.prepareStatement("INSERT INTO role (type) VALUES (?)");
            pstmt.setString(1, role.getName());

            return pstmt.executeUpdate() == 1;
        } finally {
            DbConnectionSingleton.getInstance().close(conn, pstmt);
        }
    }

    public static RoleDTO getRoleById(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        try {
            conn = DbConnectionSingleton.getInstance().getConnection();
            pstmt = conn.prepareStatement("SELECT * FROM role WHERE id = ?");
            pstmt.setInt(1, id);
            rset = pstmt.executeQuery();

            if (rset.next()) {
                return new RoleDTO(rset.getInt("id"), rset.getString("type"));
            }
        } finally {
            DbConnectionSingleton.getInstance().close(conn, pstmt, rset);
        }
        return null;
    }

    public static List<RoleDTO> getAllRoles() throws SQLException {
        List<RoleDTO> roles = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rset = null;

        try {
            conn = DbConnectionSingleton.getInstance().getConnection();
            stmt = conn.createStatement();
            rset = stmt.executeQuery("SELECT * FROM role");

            while (rset.next()) {
                roles.add(new RoleDTO(rset.getInt("id"), rset.getString("type")));
            }
        } finally {
            DbConnectionSingleton.getInstance().close(conn, (PreparedStatement) stmt, rset);
        }
        return roles;
    }

    public static boolean updateRole(RoleDTO role) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DbConnectionSingleton.getInstance().getConnection();
            pstmt = conn.prepareStatement("UPDATE role SET type = ? WHERE id = ?");
            pstmt.setString(1, role.getName());
            pstmt.setInt(2, role.getId());

            return pstmt.executeUpdate() == 1;
        } finally {
            DbConnectionSingleton.getInstance().close(conn, pstmt);
        }
    }

    public static boolean deleteRole(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DbConnectionSingleton.getInstance().getConnection();
            pstmt = conn.prepareStatement("DELETE FROM role WHERE id = ?");
            pstmt.setInt(1, id);

            return pstmt.executeUpdate() == 1;
        } finally {
            DbConnectionSingleton.getInstance().close(conn, pstmt);
        }
    }
}
// helo
